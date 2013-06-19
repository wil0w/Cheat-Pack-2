/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.gui.api.components;

import java.awt.image.BufferedImage;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.MapColor;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

public class TestMap {
	protected int mapTexture;
	protected int maskTexture;
	protected int[ ] buffer;
	protected final int mapSize = 128;
	protected final int center = mapSize / 2;
	protected int colorOffset;
	protected int prevheight = 0;
	protected int heightDiff;
	public int posX = 100;
	public int posY = 100;
	
	static String DEFAULT_PACK = "/com/godshawk/colony/tex/";
	static String pack = DEFAULT_PACK;
	
	public TestMap( )
	{
		// read( "roundmap_mask.png" );
		this.mapTexture = CheatBase.instance.minecraft.renderEngine.allocateAndSetupTexture( new BufferedImage( 128, 128, 2 ) );
		// this.maskTexture =
		// Colony.instance.minecraft.renderEngine.allocateAndSetupTexture( mask
		// );
		this.buffer = new int[ 16384 ];
	}
	
	public void tick( )
	{
		int playerX = (int) CheatBase.instance.minecraft.thePlayer.posX - 64;
		int playerZ = (int) CheatBase.instance.minecraft.thePlayer.posZ - 64;
		
		for ( int x = 8; x < 120; x++ ) {
			for ( int y = 8; y < 120; y++ ) {
				int blockWorldX = playerX + x;
				int blockWorldZ = playerZ + y;
				
				this.buffer [ ( x + ( y * 128 ) ) ] = ( 0xFF000000 | getMapColorXZ( blockWorldX, blockWorldZ ) );
			}
		}
		this.buffer [ 8256 ] = -1;
	}
	
	public void render( )
	{
		int width = getWidth( );
		int height = getHeight( );
		
		CheatBase.instance.minecraft.renderEngine.createTextureFromBytes( this.buffer, 128, 128, this.mapTexture );
		
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glColor4d( 1, 1, 1, 1 );
		
		GL11.glPushMatrix( );
		
		GL11.glBindTexture( 3553, this.mapTexture );
		tessellator.startDrawingQuads( );
		tessellator.addVertexWithUV( this.posX - 50, ( this.posY - 50 ) + height, 0.0D, 0.0D, 1.0D );
		tessellator.addVertexWithUV( ( this.posX - 50 ) + width, ( this.posY - 50 ) + height, 0.0D, 1.0D, 1.0D );
		tessellator.addVertexWithUV( ( this.posX - 50 ) + width, this.posY - 50, 0.0D, 1.0D, 0.0D );
		tessellator.addVertexWithUV( this.posX - 50, this.posY - 50, 0.0D, 0.0D, 0.0D );
		tessellator.draw( );
		
		GL11.glPopMatrix( );
		
	}
	
	protected int getMapColorXZ( int blockWorldX, int blockWorldZ ) {
		Chunk chunk = CheatBase.instance.minecraft.theWorld.getChunkFromBlockCoords( blockWorldX, blockWorldZ );
		
		int blockChunkX = wrapValue( blockWorldX );
		int blockChunkZ = wrapValue( blockWorldZ );
		
		BiomeGenBase b = chunk.getBiomeGenForWorldCoords( blockChunkX, blockChunkZ, CheatBase.instance.minecraft.theWorld.getWorldChunkManager( ) );
		
		int blockY = chunk.getHeightValue( blockChunkX % 16, blockChunkZ % 16 ) + 1;
		if ( blockY > 0 ) {
			int blockId = chunk.getBlockID( blockChunkX, blockY, blockChunkZ );
			boolean validBlock;
			do {
				validBlock = true;
				
				if ( blockId == 0 ) {
					validBlock = false;
				} else if ( ( blockY > 0 ) && ( blockId > 0 ) && ( Block.blocksList [ blockId ].blockMaterial.materialMapColor == MapColor.airColor ) ) {
					validBlock = false;
				}
				
				if ( !validBlock ) {
					blockY-- ;
					blockId = chunk.getBlockID( blockChunkX, blockY, blockChunkZ );
				}
			} while ( ( blockY > 0 ) && ( !validBlock ) );
			
			if ( ( blockY > 0 ) && ( Block.blocksList [ blockId ].blockMaterial.isLiquid( ) ) ) {
				while ( ( blockY > 0 ) && ( blockId > 0 ) && ( Block.blocksList [ blockId ].blockMaterial.isLiquid( ) ) ) {
					blockY-- ;
					blockId = chunk.getBlockID( blockChunkX, blockY, blockChunkZ );
				}
				blockY++ ;
				blockId = chunk.getBlockID( blockChunkX, blockY, blockChunkZ );
			}
			
			int color = MapColor.airColor.colorValue;
			Block block = Block.blocksList [ blockId ];
			if ( block == null ) {
				return 0;
			}
			
			color = block.blockMaterial.materialMapColor.colorValue;
			
			if ( block.blockMaterial.isLiquid( ) ) {
				this.heightDiff = ( ( ( blockY - 64 ) / 5 ) + 1 );
			} else {
				this.heightDiff = ( blockY - this.prevheight );
				this.prevheight = blockY;
			}
			
			this.colorOffset = 220;
			if ( this.heightDiff > 0 ) {
				this.colorOffset = 185 - MathHelper.clamp_int( heightDiff, 1, 70 );
			} else if ( this.heightDiff < 0 ) {
				this.colorOffset = 185;
			}
			
			if ( ( b.topBlock == block.blockID ) || ( block.blockID == 18 ) || block.blockMaterial.isLiquid( ) ) {
				color += Math.abs( b.temperature );
			}
			
			if ( block.blockMaterial.isLiquid( ) ) {
				color += b.waterColorMultiplier;
			}
			if ( block.blockID == 18 ) {
				int bc = b.color;
				while ( bc <= 1000000 ) {
					bc = bc << 1;
				}
				color %= bc;
			}
			
			if ( ( block.blockID == 78 ) || ( block.blockID == 80 ) ) {
				return block.blockMaterial.materialMapColor.colorValue;
			}
			
			int R = ( ( ( color >> 16 ) & 0xFF ) * this.colorOffset ) / 255;
			int G = ( ( ( color >> 8 ) & 0xFF ) * this.colorOffset ) / 255;
			int B = ( ( color & 0xFF ) * this.colorOffset ) / 255;
			
			return ( R << 16 ) | ( G << 8 ) | B;
		}
		return 0;
	}
	
	protected int wrapValue( int val )
	{
		val %= 16;
		if ( val < 0 ) {
			val = 16 + val;
		}
		return val;
	}
	
	public int getWidth( )
	{
		return 128;
	}
	
	public int getHeight( )
	{
		return 128;
	}
	
}
