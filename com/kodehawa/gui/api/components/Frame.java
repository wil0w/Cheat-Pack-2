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

import java.util.ArrayList;

import com.kodehawa.CheatBase;
import com.kodehawa.gui.api.render.ModGuiUtils;

public class Frame extends Item {
	
	
	public ArrayList<Item> toBeAdded = new ArrayList<Item>( );
	public ArrayList<Item> toBeRemoved = new ArrayList<Item>( );
	public ArrayList<Item> children = new ArrayList<Item>( );
	boolean minimized = false;
	public boolean pinnable = false;
	public boolean pinned = false;
	
	/**
	 * Should we clear labels? Only used for the console
	 */
	public boolean shouldClear = false;
	int oldHeight = 0;
	
	public Frame( CheatBase cb, int x, int y, int w, int h, String s ) {
		this( cb, x, y, w, h, 0xff666666, s );
	}
	
	public Frame( CheatBase cb, int x, int y, int w, int h, int color, String s ) {
		this( cb, x, y, w, h, color, -1, s );
	}
	
	public Frame( CheatBase cb, int x, int y, int w, int h, int color, int color2, String s ) {
		this.c = cb;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.oldHeight = h;
		this.color = color;
		this.color2 = color2;
		this.text = s;
		this.setDraggable( true );
	}
	
	@Override
	public void update( ) {
		this.draw( );
	}
	
	@Override
	public void draw( ) {
		// TODO Auto-generated method stub
		
		if ( color2 > -1 ) {
			ModGuiUtils.drawRect( x, y, x + width, y + oldHeight, color );
			ModGuiUtils.drawRect( x, y, x + width, y + oldHeight, (int) ( color * 1.1 ) );
		} else {
			ModGuiUtils.drawGradientRect( x, y, x + width, y + oldHeight, color, color2 );
			ModGuiUtils.drawRect( x, y + oldHeight, x + width, y + height, color2 );
		}
		
		/**
		 * Minimize button
		 */
		ModGuiUtils.drawFilledCircle( ( x + width ) - 8, y + 7, 2.5, 0xff00dd66 );
		if ( minimized ) {
			ModGuiUtils.drawFilledCircle( ( x + width ) - 8, y + 7, 2.5, 0xaa000000 );
		}
		
		if ( pinnable ) {
			ModGuiUtils.drawFilledCircle( ( x + width ) - 16, y + 7, 2.5, 0xff72a9dc );
			if ( pinned ) {
				ModGuiUtils.drawFilledCircle( ( x + width ) - 16, y + 7, 2.5, 0xaa000000 );
			}
		}
		
		ModGuiUtils.drawHorizontalLine( this.x + 2, ( this.x + this.width ) - 2, ( this.y + this.oldHeight ) - 6, 2, 0xff550055 );
		
		CheatBase.instance.minecraft.fontRenderer.drawString( this.text, this.x + 3, this.y + 3, 0xff87b5ff );
		
		// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2, y,
		// 0x87b5ff );
		
		if ( minimized ) {
			this.height = oldHeight;
			
		} else {
			this.height = (int) ( oldHeight + ( ( oldHeight * this.children.size( ) ) / 1.4 ) + 5 );
		}
		if ( !minimized ) {
			for ( Item e : children ) {
				e.x = ( this.x ) + 3;
				int offset = oldHeight;
				offset /= 2;
				offset += 4;
				e.y = ( this.y ) + ( offset * ( this.children.indexOf( e ) + 1 ) ) + 10;
				e.update( );
			}
		}
	}
	
	@Override
	public void drag( int x, int y ) {
		// TODO Auto-generated method stub
		if ( !this.isDraggable( ) || !this.isDragging( ) ) {
			return;
		}
		
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void onClick( int x, int y ) {
		// TODO Auto-generated method stub
		// ModGuiUtils.drawFilledCircle( ( x + width ) - 8, y + 7, 2.5,
		// 0xffaa0033 );
		
		if ( this.clickedInside( x, y ) ) {
			if ( x >= ( ( this.x + this.width ) - 8 - 2.5 ) ) {
				if ( x <= ( ( ( this.x + this.width ) - 8 ) + 2.5 ) ) {
					if ( y >= ( ( this.y + 7 ) - 2.5 ) ) {
						if ( y <= ( this.y + 7 + 2.5 ) ) {
							this.minimized = !this.minimized;
						}
					}
				}
			} else if ( x >= ( ( this.x + this.width ) - 16 - 2.5 ) ) {
				if ( x <= ( ( ( this.x + this.width ) - 16 ) + 2.5 ) ) {
					if ( y >= ( ( this.y + 7 ) - 2.5 ) ) {
						if ( y <= ( this.y + 7 + 2.5 ) ) {
							this.pinned = !this.pinned;
						}
					}
				}
				
			} else {
				this.setDragging( true );
			}
		}
		if ( !this.minimized ) {
			for ( Item e : children ) {
				e.onClick( x, y );
			}
		}
	}
	
	public void mouseUp( ) {
		this.setDragging( false );
	}
	
	public void addChild( Item e ) {
		children.add( e );
		e.setParent( this, 0, 0 );
	}
	
	public void removeChild( Item e ) {
		children.remove( e );
	}
	
	public void addLater( Item e ) {
		toBeAdded.add( e );
	}
	
	public void removeLater( Item e ) {
		toBeRemoved.add( e );
	}
	
	@Override
	public boolean clickedInside( int x, int y ) {
		if ( x > this.x ) {
			if ( y > this.y ) {
				if ( x < ( this.x + this.width ) ) {
					if ( y < ( this.y + this.oldHeight ) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean mouseOver( int x, int y ) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setPinnable( boolean state ) {
		this.pinnable = state;
	}
}
