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

import net.minecraft.src.Direction;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.MathHelper;
import net.minecraft.src.TcpConnection;
import net.minecraft.src.Timer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;
import com.kodehawa.gui.api.render.ModGuiUtils;
import com.kodehawa.gui.api.testing.TestEntityRenderer;
import com.kodehawa.mods.Mod;

public class TestGui extends GuiScreen {
	
	public ArrayList<Frame> frames;
	
	public TestGui( ) {
		frames = new ArrayList<Frame>( );
		initFrames( );
		for ( Frame e : frames ) {
			e.minimized = true;
		}
	}
	
	@Override
	public boolean doesGuiPauseGame( ) {
		return false;
	}
	
	@Override
	public void initGui( ) {
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	public void onGuiClosed( ) {
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	protected void keyTyped( char c, int i ) {
		if ( i == 1 ) {
			mc.displayGuiScreen( null );
			return;
		}
		
		for ( Frame e : this.frames ) {
			for ( Item t : e.children ) {
				if ( t instanceof TextArea ) {
					( (TextArea) t ).keyPressed( i );
				}
			}
		}
	}
	
	@Override
	public void drawScreen( int i, int j, float f ) {
		for ( Frame e : frames ) {
			e.update( );
			if ( e.toBeAdded.size( ) > 0 ) {
				for ( Item q : e.toBeAdded ) {
					e.addChild( q );
				}
				e.toBeAdded.clear( );
			}
			if ( e.toBeRemoved.size( ) > 0 ) {
				for ( Item q : e.toBeRemoved ) {
					e.removeChild( q );
				}
				e.toBeRemoved.clear( );
			}
		}
		
		this.mouseDragged( i, j );
	}
	
	@Override
	protected void mouseClicked( int i, int j, int k ) {
		for ( Frame e : frames ) {
			e.onClick( i, j );
		}
	}
	
	public void mouseDragged( int i, int j )
	{
		for ( Frame e : frames ) {
			int q = ( e.x - i ) / 2;
			int r = ( e.y - j ) / 2;
			q += i - ( e.width / 4 );
			r += j - ( e.oldHeight / 4 );
			e.drag( q, r );
		}
	}
	
	@Override
	protected void mouseMovedOrUp( int par1, int par2, int par3 ) {
		for ( Frame e : frames ) {
			e.mouseUp( );
		}
	}
	
	public void makeWorldFrame( ) {
		Frame wFrame = new Frame( CheatBase.instance, 10, 10, 120, 20, 0xff550055, 0xaa000000, "World" );
		for ( Mod m : CheatBase.instance.mmanager.worldMods ) {
			Button b = new Button( m.name, 0xff000077, 0xffffff, m );
			b.setWidth( wFrame.width - 6 );
			b.setHeight( wFrame.oldHeight - 8 );
			wFrame.addChild( b );
		}
		wFrame.setPinnable( false );
		addFrame( wFrame );
	}
	
	public void makePlayerFrame( ) {
		Frame pFrame = new Frame( CheatBase.instance, 130, 10, 120, 20, 0xff550055, 0xaa000000, "Player" );
		for ( Mod m : CheatBase.instance.mmanager.playerMods ) {
			Button b = new Button( m.name, 0xff000077, 0xffffff, m );
			b.setWidth( pFrame.width - 6 );
			b.setHeight( pFrame.oldHeight - 8 );
			pFrame.addChild( b );
		}
		addFrame( pFrame );
	}
	
	public void makeKeybindsFrame( ) {
		Frame kFrame = new Frame( CheatBase.instance, 250, 10, 120, 20, 0xff550055, 0xaa000000, "Keybinds" ) {
			@Override
			public void update( ) {
				this.draw( );
				this.children.clear( );
				for ( Mod m : CheatBase.instance.mmanager.mods ) {
					Label l = new Label( m.name + " - " + Keyboard.getKeyName( m.keyBind ), 0xffffff );
					l.setParent( this, ( x ) + 3, ( y ) - 21 );
					addChild( l );
				}
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
				// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2,
				// y, 0x87b5ff );
				
				if ( minimized ) {
					this.height = oldHeight;
					
				} else {
					this.height = (int) ( oldHeight + ( ( oldHeight * this.children.size( ) ) / 1.4 ) + 5 ) / 2;
				}
				if ( !minimized ) {
					GL11.glPushMatrix( );
					GL11.glScaled( 0.5, 0.5, 0.5 );
					for ( Item e : children ) {
						e.x = ( ( this.x ) + 3 ) * 2;
						int offset = oldHeight;
						offset /= 8;
						offset += 4;
						e.y = ( ( this.y ) + ( offset * ( this.children.indexOf( e ) + 1 ) ) + 15 ) * 2;
						
						e.update( );
						
					}
					GL11.glScaled( 1, 1, 1 );
					GL11.glPopMatrix( );
				}
			}
		};
		
		kFrame.setPinnable( true );
		addFrame( kFrame );
	}
	
	public void makeMinimapFrame( ) {
		final TestMap map = new TestMap( );
		
		Frame mFrame = new Frame( CheatBase.instance, 380, 10, 134, 20, 0xff550055, 0xaa000000, "Minimap" ) {
			
			@Override
			public void update( ) {
				this.draw( );
				this.oldHeight = 20;
				map.posX = this.x + 3 + 48;
				map.posY = this.y + this.oldHeight + 3 + 40;
				map.tick( );
				if ( !this.minimized ) {
					this.height = 137;
					map.render( );
					ModGuiUtils.drawTriangle( CheatBase.instance.minecraft.thePlayer, ( x + 64 + 2 ) * 2, ( y + 64 + 13 ) * 2, 0xffff0000 );
				} else {
					this.height = this.oldHeight;
				}
			}
			
		};
		
		mFrame.setPinnable( true );
		addFrame( mFrame );
	}
	
	public void makeRadarFrame( ) {
		final Radar r = new Radar( );
		Frame rFrame = new Frame( CheatBase.instance, 10, 30, 120, 20, 0xff550055, 0xaa000000, "Radar" ) {
			@Override
			public void update( ) {
				this.draw( );
				if ( !this.minimized ) {
					r.drawRadar( this.x + 60, this.y + 70 );
					this.height = 125;
				} else {
					this.height = 20;
				}
			}
		};
		
		rFrame.setPinnable( true );
		addFrame( rFrame );
	}
	
	public void makeActivesFrame( ) {
		Frame aFrame = new Frame( CheatBase.instance, 130, 30, 120, 20, 0xff550055, 0xaa000000, "Active Cheats" ) {
			@Override
			public void update( ) {
				this.draw( );
				this.children.clear( );
				for ( String s : CheatBase.instance.enabledMods ) {
					Label l = new Label( s, 0xffffff );
					l.setParent( this, ( x ) + 3, ( y ) - 21 );
					addChild( l );
				}
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
				// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2,
				// y, 0x87b5ff );
				
				if ( minimized ) {
					this.height = oldHeight;
					
				} else {
					this.height = ( oldHeight + ( ( oldHeight * this.children.size( ) ) / 2 ) );
				}
				if ( !minimized ) {
					for ( Item e : children ) {
						e.x = ( this.x ) + 3;
						int offset = oldHeight;
						offset /= 4;
						offset += 4;
						e.y = ( this.y ) + ( offset * ( this.children.indexOf( e ) + 1 ) ) + 10;
						e.update( );
					}
				}
			}
		};
		
		aFrame.setPinnable( true );
		addFrame( aFrame );
	}
	
	public void makeInfoFrame( ) {
		final Frame iFrame = new Frame( CheatBase.instance, 250, 30, 120, 20, 0xff550055, 0xaa000000, "Player Info" ) {
			@Override
			public void update( ) {
				this.draw( );
				String dim = "Overworld";
				String dir = "NORTH";
				int d = mc.thePlayer.dimension;
				
				if ( d == -1 ) {
					dim = "Nether";
				}
				if ( d == 0 ) {
					dim = "Overworld";
				}
				if ( d == 1 ) {
					dim = "The End";
				}
				
				int var24 = MathHelper.floor_double( ( ( mc.thePlayer.rotationYaw * 4.0F ) / 360.0F ) + 0.5D ) & 3;
				dir = Direction.directions [ var24 ];
				try {
					children.clear( );
					addChild( new Label( "User: " + CheatBase.instance.minecraft.thePlayer.username, 0xffffff ) );
					addChild( new Label( "FPS: " + mc.debugFPS, 0xffffff ) );
					addChild( new Label( "Lag: " + TcpConnection.field_74490_x, 0xffffff ) );
					addChild( new Label( "X: " + (int) CheatBase.instance.minecraft.thePlayer.posX, 0xffffff ) );
					addChild( new Label( "Y: " + (int) CheatBase.instance.minecraft.thePlayer.posY, 0xffffff ) );
					addChild( new Label( "Z: " + (int) CheatBase.instance.minecraft.thePlayer.posZ, 0xffffff ) );
					addChild( new Label( "Dimension: " + dim, 0xffffff ) );
					addChild( new Label( "Facing " + dir, 0xffffff ) );
					addChild( new Label( "Timer: " + Timer.timerSpeed, 0xffffff ) );
					
					addChild( new Label( "", 0xffffff ) );
				} catch ( Exception e ) {
					e.printStackTrace( );
				}
				
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
				// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2,
				// y, 0x87b5ff );
				
				if ( minimized ) {
					this.height = oldHeight;
				} else {
					this.height = (int) ( oldHeight + ( ( oldHeight * this.children.size( ) ) / 1.4 ) + 5 ) / 2;
				}
				if ( !minimized ) {
					GL11.glPushMatrix( );
					GL11.glScaled( 0.5, 0.5, 0.5 );
					for ( Item e : children ) {
						e.x = ( ( this.x ) + 3 ) * 2;
						int offset = oldHeight;
						offset /= 8;
						offset += 4;
						e.y = ( ( this.y ) + ( offset * ( this.children.indexOf( e ) + 1 ) ) + 15 ) * 2;
						
						e.update( );
						
					}
					GL11.glScaled( 1, 1, 1 );
					GL11.glPopMatrix( );
				}
			}
		};
		iFrame.setPinnable( true );
		addFrame( iFrame );
	}
	
	public void makeSpyFrame( ) {
		final TestEntityRenderer ter = new TestEntityRenderer( CheatBase.instance.minecraft );
		Frame sFrame = new Frame( CheatBase.instance, 370, 30, 320, 20, 0xff550055, 0xaa000000, "TestingFrame" ) {
			@Override
			public void update( ) {
				this.draw( );
				if ( !this.minimized ) {
					//ter.updateCameraAndRender( 1, this.x, this.y, this.width, this.height );
				}
			}
		};
		
		sFrame.setPinnable( true );
		
		addFrame( sFrame );
	}
	
	public void makeTestFrame( ) {
		Frame tFrame = new Frame( CheatBase.instance, 370, 30, 120, 20, 0xff550000, 0xaa000055, "Console" ) {
			@Override
			public void update( ) {
				this.draw( );
				if ( this.children.size( ) > 10 ) {
					this.removeLater( this.children.get( 1 ) );
				}
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
				
				// TTFRenderer.drawTTFString( Colony.guiFont, this.text, x + 2,
				// y,
				// 0x87b5ff );
				
				if ( minimized ) {
					this.height = oldHeight;
					
				} else {
					GL11.glPushMatrix( );
					GL11.glScaled( 0.5, 0.5, 0.5 );
					CheatBase.instance.minecraft.fontRenderer.drawString( "Type \'clear\' to clear history. ", ( this.x * 2 ) + 6, ( ( this.y + this.oldHeight ) * 2 ) - 4, 0xffffff );
					GL11.glScaled( 1, 1, 1 );
					GL11.glPopMatrix( );
					int diff = 0;
					if ( this.children.size( ) > 0 ) {
						diff = this.children.size( ) * 2;
					}
					this.height = (int) ( ( oldHeight + ( ( oldHeight * this.children.size( ) ) / 1.4 ) + 5 + diff ) / 1.1 );
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
		};
		
		tFrame.addChild( new TextArea( "", tFrame.x, tFrame.y + ( tFrame.children.size( ) * 12 ), 100, 10, 0x77770077 ) );
		
		addFrame( tFrame );
	}
	
	public void addFrame( Frame e ) {
		frames.add( e );
	}
	
	public void initFrames( ) {
		makeWorldFrame( );
		makePlayerFrame( );
		makeKeybindsFrame( );
		makeMinimapFrame( );
		makeRadarFrame( );
		makeActivesFrame( );
		makeInfoFrame( );
		makeTestFrame( );
		// makeSpyFrame( );
	}
}
