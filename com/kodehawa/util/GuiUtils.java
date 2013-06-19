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

package com.kodehawa.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

public class GuiUtils extends Gui {
	private static Minecraft mc = GuiIngame.mc;
	
	public static void drawBorderedRect( int x, int y, int x1, int y1, int size, int borderC, int insideC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawRect( x + size, y + size, x1 - size, y1 - size, insideC );
	}
	
	public static void drawWBorderedRect( int x, int y, int x1, int y1, int size, int borderC, int insideC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y1 - 1, borderC );
		drawRect( x + size, y + size, x1 - size, y1 - size, insideC );
	}
	
	public static void drawWHollowBorderedRect( int x, int y, int x1, int y1, int size, int borderC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y1 - 1, borderC );
	}
	
	public static void drawBorderedGradientRect( int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2 ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
	}
	
	public static void drawWBorderedGradientRect( int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2 ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y1 - 1, borderC );
		drawGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
	}
	
	public static void drawBorderedNTGradientRect( int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2 ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawNTGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
	}
	
	public static void drawWBorderedNTGradientRect( int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2 ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y1 - 1, borderC );
		drawNTGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
	}
	
	public static void drawNTGradientRect( int x, int y, int x1, int y1, int insideC1, int insideC2 ) {
		for ( int Loop = -5; Loop < 40; Loop++ ) {
			drawGradientRect( x, y, x1, y1, insideC1, insideC2 );
		}
	}
	
	public static void drawBorderedRectWithString( String s, int x, int y, int x1, int y1, int size, int borderC, int insideC, int SC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawRect( x + size, y + size, x1 - size, y1 - size, insideC );
		
		int S1 = ( ( x1 - x ) / 2 );
		int S2 = ( ( x1 - S1 ) - mc.fontRenderer.getStringWidth( s ) / 2 );
		int S3 = ( ( y1 - y ) / 2 );
		int S4 = ( y1 - S3 );
		mc.fontRenderer.drawString( s, S2, S4 - 4, SC );
	}
	
	public static void drawBorderedGradientRectWithString( String s, int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2, int SC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
		
		int S1 = ( ( x1 - x ) / 2 );
		int S2 = ( ( x1 - S1 ) - mc.fontRenderer.getStringWidth( s ) / 2 );
		int S3 = ( ( y1 - y ) / 2 );
		int S4 = ( y1 - S3 );
		mc.fontRenderer.drawString( s, S2, S4 - 4, SC );
	}
	
	public static void drawBorderedNTGradientRectWithString( String s, int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2, int SC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x, x1 - 1, y, borderC );
		drawHorizontalLine( x, x1 - 1, y1 - 1, borderC );
		drawNTGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
		
		int S1 = ( ( x1 - x ) / 2 );
		int S2 = ( ( x1 - S1 ) - mc.fontRenderer.getStringWidth( s ) / 2 );
		int S3 = ( ( y1 - y ) / 2 );
		int S4 = ( y1 - S3 );
		mc.fontRenderer.drawString( s, S2, S4 - 4, SC );
	}
	
	public static void drawWBorderedNTGradientRectWithString( String s, int x, int y, int x1, int y1, int size, int borderC, int insideC1, int insideC2, int SC ) {
		drawVerticalLine( x, y, y1 - 1, borderC );
		drawVerticalLine( x1 - 1, y, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y, borderC );
		drawHorizontalLine( x + 1, x1 - 2, y1 - 1, borderC );
		drawNTGradientRect( x + size, y + size, x1 - size, y1 - size, insideC1, insideC2 );
		
		int S1 = ( ( x1 - x ) / 2 );
		int S2 = ( ( x1 - S1 ) - mc.fontRenderer.getStringWidth( s ) / 2 );
		int S3 = ( ( y1 - y ) / 2 );
		int S4 = ( y1 - S3 );
		mc.fontRenderer.drawString( s, S2, S4 - 4, SC );
	}
	
	public static void drawRectGui( String s, int X, int Y, boolean F ) {
		drawRect( X, Y, X + 80, Y + 12, 0x43000000 );
		drawRect( X + 80, Y, X + 86, Y + 12, F ? 0xF900FF00 : 0xF9FF0000 );
		mc.fontRenderer.drawString( s, X + 3, Y + 2, 0xFFFFFF );
	}
	
	public static void drawBorderedRectGui( String s, int X, int Y, boolean F ) {
		drawBorderedRect( X, Y, X + 80, Y + 12, 1, 0xFF000000, 0x43000000 );
		drawRect( X + 80, Y, X + 86, Y + 12, F ? 0xF900FF00 : 0xF9FF0000 );
		mc.fontRenderer.drawString( s, X + 3, Y + 2, 0xFFFFFF );
	}
	
	public static void drawBorderedRectGuiArraylist( String s, int X, int Y ) {
		drawBorderedRect( X, Y, X + 80, Y + 12, 1, 0xFF000000, 0x43000000 );
		mc.fontRenderer.drawString( s, X + 3, Y + 2, 0xFFFFFF );
	}
	
	public static void drawBorderedNTGradientRectGuiArraylist( String s, int X, int Y, int X1 ) {
		drawWBorderedNTGradientRect( X, Y, X1 + 4, Y + 12, 1, 0xFF3D3D3D, 0x21218359, 0xFF093604 );
		
		int S1 = ( ( X1 - X ) / 2 );
		int S2 = ( ( X1 - S1 ) - mc.fontRenderer.getStringWidth( s ) / 2 );
		int S3 = ( ( Y + 12 - Y ) / 2 );
		int S4 = ( Y + 12 - S3 );
		mc.fontRenderer.drawString( s, S2, S4 - 4, 0xD9D9D9 );
	}
	
	/*MORE STUFF*/
	
	public static void drawMoreRoundedBorderedRect( int x, int y, int x1, int y1, int size, int borderC, int insideC ) {
		drawVerticalLine( x, y + 1, y1 - 2, borderC );
		drawVerticalLine( x1 - 1, y + 1, y1 - 2, borderC );
		drawHorizontalLine( x + 2, x1 - 3, y, borderC );
		drawHorizontalLine( x + 2, x1 - 3, y1 - 1, borderC );
		drawHorizontalLine( x + 1, x + 1, y + 1, borderC );
		drawHorizontalLine( x1 - 2, x1 - 2, y + 1, borderC );
		drawHorizontalLine( x1 - 2, x1 - 2, y1 - 2, borderC );
		drawHorizontalLine( x + 1, x + 1, y1 - 2, borderC );
		drawRect( x + size, y + size, x1 - size, y1 - size, insideC );
	}
	
	/*Credits to AceOfDiamonds21 for this one*/
	
	public static void drawSideGradientRect( int i, int j, int k, int l, int i1, int j1 ) {
		float f = (float) ( i1 >> 24 & 0xff ) / 255F;
		float f1 = (float) ( i1 >> 16 & 0xff ) / 255F;
		float f2 = (float) ( i1 >> 8 & 0xff ) / 255F;
		float f3 = (float) ( i1 & 0xff ) / 255F;
		float f4 = (float) ( j1 >> 24 & 0xff ) / 255F;
		float f5 = (float) ( j1 >> 16 & 0xff ) / 255F;
		float f6 = (float) ( j1 >> 8 & 0xff ) / 255F;
		float f7 = (float) ( j1 & 0xff ) / 255F;
		GL11.glDisable( 3553 /*GL_TEXTURE_2D*/);
		GL11.glEnable( 3042 /*GL_BLEND*/);
		GL11.glDisable( 3008 /*GL_ALPHA_TEST*/);
		GL11.glBlendFunc( 770, 771 );
		GL11.glShadeModel( 7425 /*GL_SMOOTH*/);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads( );
		tessellator.setColorRGBA_F( f1, f2, f3, f );
		tessellator.addVertex( k, j, zLevel2 );
		tessellator.setColorRGBA_F( f5, f6, f7, f4 );
		tessellator.addVertex( i, j, zLevel2 );
		tessellator.addVertex( i, l, zLevel2 );
		tessellator.setColorRGBA_F( f1, f2, f3, f );
		tessellator.addVertex( k, l, zLevel2 );
		tessellator.draw( );
		GL11.glShadeModel( 7424 /*GL_FLAT*/);
		GL11.glDisable( 3042 /*GL_BLEND*/);
		GL11.glEnable( 3008 /*GL_ALPHA_TEST*/);
		GL11.glEnable( 3553 /*GL_TEXTURE_2D*/);
	}
}
