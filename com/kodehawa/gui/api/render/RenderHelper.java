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

package com.kodehawa.gui.api.render;

import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

public final class RenderHelper
{
	public static void bindTexture( String string )
	{
		GL11.glBindTexture( 3553, CheatBase.instance.minecraft.renderEngine.getTexture( string ) );
	}
	
	public static void drawSprite( int x, int y, int spriteX, int spriteY, int spriteWidth, int spriteHeight ) {
		drawSprite( x, y, spriteX, spriteY, spriteWidth, spriteHeight, 0 );
	}
	
	public static void drawSprite( int x, int y, int spriteX, int spriteY, int spriteWidth, int spriteHeight, int zLevel ) {
		drawSpriteUV( x, y, spriteWidth, spriteHeight, spriteX / 256.0F, spriteY / 256.0F, ( spriteX + spriteWidth ) / 256.0F, ( spriteY + spriteHeight ) / 256.0F, zLevel );
	}
	
	public static void drawSprite( int x, int y, int spriteX, int spriteY, int spriteWidth, int spriteHeight, int texWidth, int texHeight, int zLevel ) {
		drawSpriteUV( x, y, spriteWidth, spriteHeight, spriteX / texWidth, spriteY / texHeight, ( spriteX + spriteWidth ) / texWidth, ( spriteY + spriteHeight ) / texHeight, zLevel );
	}
	
	public static void drawSpriteUV( int x, int y, int width, int height, float u1, float v1, float u2, float v2, int zLevel ) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads( );
		tessellator.addVertexWithUV( x, y + height, zLevel, u1, v2 );
		tessellator.addVertexWithUV( x + width, y + height, zLevel, u2, v2 );
		tessellator.addVertexWithUV( x + width, y, zLevel, u2, v1 );
		tessellator.addVertexWithUV( x, y, zLevel, u1, v1 );
		tessellator.draw( );
	}
	
	public static void drawRectangle( int x, int y, int width, int height, int color ) {
		drawRectangle( x, y, width, height, color, color, false );
	}
	
	public static void drawRectangle( int x, int y, int width, int height, int color, int color2, boolean fadeVertical ) {
		float colorR = ( ( color2 >> 24 ) & 0xFF ) / 255.0F;
		float colorG = ( ( color2 >> 16 ) & 0xFF ) / 255.0F;
		float colorB = ( ( color2 >> 8 ) & 0xFF ) / 255.0F;
		float colorA = ( color2 & 0xFF ) / 255.0F;
		
		float color2R = ( ( color >> 24 ) & 0xFF ) / 255.0F;
		float color2G = ( ( color >> 16 ) & 0xFF ) / 255.0F;
		float color2B = ( ( color >> 8 ) & 0xFF ) / 255.0F;
		float color2A = ( color & 0xFF ) / 255.0F;
		
		GL11.glDisable( 3553 );
		GL11.glShadeModel( 7425 );
		
		Tessellator tessellator = Tessellator.instance;
		
		tessellator.startDrawingQuads( );
		
		tessellator.setColorRGBA_F( colorR, colorG, colorB, colorA );
		tessellator.addVertex( x, y + height, 0.0D );
		
		if ( !fadeVertical ) {
			tessellator.setColorRGBA_F( color2R, color2G, color2B, color2A );
		}
		tessellator.addVertex( x + width, y + height, 0.0D );
		
		if ( fadeVertical ) {
			tessellator.setColorRGBA_F( color2R, color2G, color2B, color2A );
		}
		tessellator.addVertex( x + width, y, 0.0D );
		
		if ( !fadeVertical ) {
			tessellator.setColorRGBA_F( colorR, colorG, colorB, colorA );
		}
		tessellator.addVertex( x, y, 0.0D );
		
		tessellator.draw( );
		
		GL11.glEnable( 3553 );
		GL11.glShadeModel( 7424 );
	}
}
