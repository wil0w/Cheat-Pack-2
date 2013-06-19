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

import net.minecraft.src.Entity;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;

public class ModGuiUtils {
	/**
	 * Basic quad
	 * 
	 * @param g
	 * @param h
	 * @param i
	 * @param j
	 * @param col1
	 */
	public static void drawRect( float g, float h, float i, float j, int col1 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glBegin( GL11.GL_QUADS );
		GL11.glVertex2d( i, h );
		GL11.glVertex2d( g, h );
		GL11.glVertex2d( g, j );
		GL11.glVertex2d( i, j );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Gradient quad
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param col1
	 * @param col2
	 */
	public static void drawGradientRect( int x, int y, int x2, int y2, int col1, int col2 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		float f4 = ( ( col2 >> 24 ) & 0xFF ) / 255F;
		float f5 = ( ( col2 >> 16 ) & 0xFF ) / 255F;
		float f6 = ( ( col2 >> 8 ) & 0xFF ) / 255F;
		float f7 = ( col2 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glBegin( GL11.GL_QUADS );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		
		GL11.glColor4f( f5, f6, f7, f4 );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_FLAT );
	}
	
	/**
	 * Sideways gradient quad
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param col1
	 * @param col2
	 */
	public static void drawSideGradientRect( float x, float y, float x2, float y2, int col1, int col2 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		float f4 = ( ( col2 >> 24 ) & 0xFF ) / 255F;
		float f5 = ( ( col2 >> 16 ) & 0xFF ) / 255F;
		float f6 = ( ( col2 >> 8 ) & 0xFF ) / 255F;
		float f7 = ( col2 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glBegin( GL11.GL_QUADS );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glVertex2d( x2, y );
		
		GL11.glColor4f( f5, f6, f7, f4 );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_FLAT );
	}
	
	/**
	 * Bordered quad
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param l1
	 * @param col1
	 * @param col2
	 */
	public static void drawBorderedRect( int x, int y, int x2, int y2, float l1, int col1, int col2 ) {
		drawRect( x, y, x2, y2, col2 );
		
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Border of a quad
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param l1
	 * @param col1
	 */
	public static void drawHollowBorderedRect( int x, int y, int x2, int y2, float l1, int col1 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Gradient rect with a border
	 * 
	 * @param x
	 * @param y
	 * @param x2
	 * @param y2
	 * @param l1
	 * @param col1
	 * @param col2
	 * @param col3
	 */
	public static void drawGradientBorderedRect( int x, int y, int x2, int y2, float l1, int col1, int col2, int col3 ) {
		drawGradientRect( x, y, x2, y2, col2, col3 );
		
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Vertical line
	 * 
	 * @param x
	 * @param y
	 * @param y2
	 * @param l1
	 * @param col1
	 */
	public static void drawVerticalLine( int x, int y, int y2, float l1, int col1 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Horizontal line
	 * 
	 * @param x
	 * @param x2
	 * @param y
	 * @param l1
	 * @param col1
	 */
	public static void drawHorizontalLine( int x, int x2, int y, float l1, int col1 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x2, y );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * Diagonal line?
	 * 
	 * @param x
	 * @param x2
	 * @param y
	 * @param l1
	 * @param col1
	 */
	public static void drawDiagonalLine( int x, int x2, int y, float l1, int col1 ) {
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( l1 );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( y, x2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
	
	/**
	 * SCALED width
	 * 
	 * @return
	 */
	public static int getWidth( ) {
		ScaledResolution sr = new ScaledResolution( CheatBase.minecraft.gameSettings, CheatBase.minecraft.displayWidth, CheatBase.minecraft.displayHeight );
		return sr.getScaledWidth( );
	}
	
	/**
	 * SCALED height
	 * 
	 * @return
	 */
	public static int getHeight( ) {
		ScaledResolution sr = new ScaledResolution( CheatBase.minecraft.gameSettings, CheatBase.minecraft.displayWidth, CheatBase.minecraft.displayHeight );
		return sr.getScaledHeight( );
	}
	
	/**
	 * Quad drawn with Tessellator.java
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @param i1
	 */
	public static void dr( double i, double j, double k, double l, int i1 ) {
		if ( i < k ) {
			double j1 = i;
			i = k;
			k = j1;
		}
		if ( j < l ) {
			double k1 = j;
			j = l;
			l = k1;
		}
		float f = ( ( i1 >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( i1 >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( i1 >> 8 ) & 0xff ) / 255F;
		float f3 = ( i1 & 0xff ) / 255F;
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable( 3042 );
		GL11.glDisable( 3553 );
		GL11.glBlendFunc( 770, 771 );
		GL11.glColor4f( f1, f2, f3, f );
		tessellator.startDrawingQuads( );
		tessellator.addVertex( i, l, 0.0D );
		tessellator.addVertex( k, l, 0.0D );
		tessellator.addVertex( k, j, 0.0D );
		tessellator.addVertex( i, j, 0.0D );
		tessellator.draw( );
		GL11.glEnable( 3553 );
		GL11.glDisable( 3042 );
	}
	
	/**
	 * Border of a circle
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param c
	 */
	public static void drawCircle( int x, int y, double r, int c ) {
		float f = ( ( c >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		float f3 = ( c & 0xff ) / 255F;
		GL11.glEnable( 3042 /* GL_BLEND */);
		GL11.glDisable( 3553 /* GL_TEXTURE_2D */);
		GL11.glEnable( 2848 /* GL_LINE_SMOOTH */);
		GL11.glBlendFunc( 770, 771 );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glBegin( 2 /* GL_LINE_LOOP */);
		for ( int i = 0; i <= 360; i++ ) {
			double x2 = Math.sin( ( ( i * 3.141526D ) / 180 ) ) * r;
			double y2 = Math.cos( ( ( i * 3.141526D ) / 180 ) ) * r;
			GL11.glVertex2d( x + x2, y + y2 );
		}
		GL11.glEnd( );
		GL11.glDisable( 2848 /* GL_LINE_SMOOTH */);
		GL11.glEnable( 3553 /* GL_TEXTURE_2D */);
		GL11.glDisable( 3042 /* GL_BLEND */);
	}
	
	/**
	 * Circle
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param c
	 */
	public static void drawFilledCircle( int x, int y, double r, int c ) {
		float f = ( ( c >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		float f3 = ( c & 0xff ) / 255F;
		GL11.glEnable( 3042 /* GL_BLEND */);
		GL11.glDisable( 3553 /* GL_TEXTURE_2D */);
		GL11.glEnable( 2848 /* GL_LINE_SMOOTH */);
		GL11.glBlendFunc( 770, 771 );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glBegin( 6 /* GL_TRIANGLE_FAN */);
		for ( int i = 0; i <= 360; i++ ) {
			double x2 = Math.sin( ( ( i * 3.141526D ) / 180 ) ) * r;
			double y2 = Math.cos( ( ( i * 3.141526D ) / 180 ) ) * r;
			GL11.glVertex2d( x + x2, y + y2 );
		}
		GL11.glEnd( );
		GL11.glDisable( 2848 /* GL_LINE_SMOOTH */);
		GL11.glEnable( 3553 /* GL_TEXTURE_2D */);
		GL11.glDisable( 3042 /* GL_BLEND */);
	}
	
	/**
	 * Triangle
	 * 
	 * @param cx
	 * @param cy
	 * @param c
	 */
	public static void drawTri( int cx, int cy, int c ) {
		GL11.glRotatef( 180, 0F, 0F, 1.0F );
		float f = ( ( c >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		float f3 = ( c & 0xff ) / 255F;
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glEnable( 3042 );
		GL11.glDisable( 3553 );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glBegin( GL11.GL_TRIANGLES );
		GL11.glRotatef( 180, 0F, 0F, 1.0F );
		GL11.glVertex2d( cx, cy + 2 );
		GL11.glVertex2d( cx + 2, cy - 2 );
		GL11.glVertex2d( cx - 2, cy - 2 );
		
		GL11.glEnd( );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( 3553 );
		GL11.glDisable( 3042 );
		GL11.glRotatef( -180, 0F, 0F, 1.0F );
	}
	
	/**
	 * Triangle. Used for radars
	 * 
	 * @param e
	 * @param cx
	 * @param cy
	 * @param c
	 */
	public static void drawTriangle( Entity e, double cx, double cy, int c ) {
		GL11.glPushMatrix( );
		GL11.glScaled( 0.5, 0.5, 0.5 );
		GL11.glTranslated( cx, cy, 0 );
		if ( e instanceof EntityClientPlayerMP ) {
			GL11.glRotatef( e.rotationYaw, 0F, 0F, 1.0F );
		} else {
			GL11.glRotatef( -e.rotationYaw, 0F, 0F, 1.0F );
		}
		float f = ( ( c >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		float f3 = ( c & 0xff ) / 255F;
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glEnable( 3042 );
		GL11.glDisable( 3553 );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glBlendFunc( 770, 771 );
		GL11.glBegin( GL11.GL_TRIANGLES );
		
		GL11.glVertex2d( 0, 0 + 6 );
		GL11.glVertex2d( 0 + 3, 0 - 2 );
		GL11.glVertex2d( 0 - 3, 0 - 2 );
		
		GL11.glEnd( );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( 3553 );
		GL11.glDisable( 3042 );
		GL11.glRotatef( e.rotationYaw, 0F, 0F, 1.0F );
		
		GL11.glPopMatrix( );
	}
	
	/**
	 * Half Circle
	 * 
	 * Modes: 0 Left to right, bottom to top 1 Top to bottom, left to right 2
	 * Right to left, top to bottom 3 Bottom to top, right to left
	 * 
	 * @param x
	 * @param y
	 * @param r
	 * @param c
	 */
	public static void drawFilledHalfCircle( int x, int y, double r, int c, int mode ) {
		float f = ( ( c >> 24 ) & 0xff ) / 255F;
		float f1 = ( ( c >> 16 ) & 0xff ) / 255F;
		float f2 = ( ( c >> 8 ) & 0xff ) / 255F;
		float f3 = ( c & 0xff ) / 255F;
		GL11.glDisable( 3553 /* GL_TEXTURE_2D */);
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glBlendFunc( 770, 771 );
		
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glEnable( GL11.GL_POLYGON_SMOOTH );
		GL11.glEnable( GL11.GL_POINT_SMOOTH );
		GL11.glHint( GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		GL11.glHint( GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST );
		
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glBegin( 6 /* GL_TRIANGLE_FAN */);
		
		int startang = 0;
		int endang = 0;
		
		if ( mode == 0 ) {
			startang = 90;
			endang = 270;
		}
		if ( mode == 1 ) {
			startang = 360;
			endang = 540;
		}
		if ( mode == 2 ) {
			startang = 270;
			endang = 450;
		}
		if ( mode == 3 ) {
			startang = 180;
			endang = 360;
		}
		
		for ( int i = startang; i <= endang; i++ ) {
			double x2 = Math.sin( ( ( i * 3.141526D ) / 180 ) ) * r;
			double y2 = Math.cos( ( ( i * 3.141526D ) / 180 ) ) * r;
			GL11.glVertex2d( x + x2, y + y2 );
		}
		GL11.glEnd( );
		GL11.glDisable( GL11.GL_POLYGON_SMOOTH );
		GL11.glDisable( GL11.GL_POINT_SMOOTH );
		GL11.glDisable( 2848 /* GL_LINE_SMOOTH */);
		GL11.glEnable( 3553 /* GL_TEXTURE_2D */);
		GL11.glDisable( 3042 /* GL_BLEND */);
	}
	
	/**
	 * Draws a rounded rect. Passing in an h value is pointless; h is determined
	 * by r
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param r
	 * @param c
	 */
	public static void drawRoundedRect( int x, int y, int w, int h, int r, int c ) {
		GL11.glPushMatrix( );
		
		h = r * 2;
		
		drawFilledHalfCircle( x + r, y + r, r, c, 3 );
		
		drawFilledHalfCircle( ( x + w ) - r, y + r, r, c, 1 );
		
		drawRect( x + r, y, ( x + w ) - r, ( y + h ), c );
		
		GL11.glPopMatrix( );
	}
	
	public static void drawHLine( float par1, float par2, float par3, int par4 )
	{
		if ( par2 < par1 )
		{
			float var5 = par1;
			par1 = par2;
			par2 = var5;
		}
		
		drawRect( par1, par3, par2 + 1, par3 + 1, par4 );
	}
	
	public static void drawVLine( float par1, float par2, float par3, int par4 )
	{
		if ( par3 < par2 )
		{
			float var5 = par2;
			par2 = par3;
			par3 = var5;
		}
		
		drawRect( par1, par2 + 1, par1 + 1, par3, par4 );
	}
	
	public static void drawGradientRect( double x, double y, double x2, double y2, int col1, int col2 )
	{
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		float f4 = ( ( col2 >> 24 ) & 0xFF ) / 255F;
		float f5 = ( ( col2 >> 16 ) & 0xFF ) / 255F;
		float f6 = ( ( col2 >> 8 ) & 0xFF ) / 255F;
		float f7 = ( col2 & 0xFF ) / 255F;
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_SMOOTH );
		
		GL11.glPushMatrix( );
		GL11.glBegin( GL11.GL_QUADS );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		
		GL11.glColor4f( f5, f6, f7, f4 );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
		GL11.glShadeModel( GL11.GL_FLAT );
	}
	
	public static void drawGBRect( double x, double y, double x2, double y2, float l1, int col1, int col2, int col3 )
	{
		float f = ( ( col1 >> 24 ) & 0xFF ) / 255F;
		float f1 = ( ( col1 >> 16 ) & 0xFF ) / 255F;
		float f2 = ( ( col1 >> 8 ) & 0xFF ) / 255F;
		float f3 = ( col1 & 0xFF ) / 255F;
		
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glEnable( GL11.GL_LINE_SMOOTH );
		GL11.glDisable( GL11.GL_BLEND );
		
		GL11.glPushMatrix( );
		GL11.glColor4f( f1, f2, f3, f );
		GL11.glLineWidth( 1F );
		GL11.glBegin( GL11.GL_LINES );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y );
		GL11.glVertex2d( x2, y );
		GL11.glVertex2d( x, y2 );
		GL11.glVertex2d( x2, y2 );
		GL11.glEnd( );
		GL11.glPopMatrix( );
		
		drawGradientRect( x, y, x2, y2, col2, col3 );
		
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_LINE_SMOOTH );
	}
}
