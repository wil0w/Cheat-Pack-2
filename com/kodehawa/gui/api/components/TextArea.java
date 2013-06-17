package com.kodehawa.gui.api.components;

import net.minecraft.src.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.gui.api.render.ModGuiUtils;

/**
 * Lool, this is only getting used for the console
 * 
 * @author godshawk
 * 
 */
public class TextArea extends Item {
	public boolean focused = false;
	private static final String allowedCharacters;
	
	public TextArea( String text, int x, int y, int w, int h, int color ) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = color;
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	public void draw( ) {
		// TODO Auto-generated method stub
		ModGuiUtils.drawRect( x, y, x + width, y + height, color );
		GL11.glPushMatrix( );
		GL11.glScaled( 0.5, 0.5, 0.5 );
		// TTFRenderer.drawTTFString( Colony.instance.guiFont, text, x * 2, y *
		// 2, 0xffffffff );
		CheatBase.instance.minecraft.fontRenderer.drawString( text, ( x * 2 ) + 2, ( y * 2 ) + 6, 0xffffff );
		GL11.glScaled( 1, 1, 1 );
		GL11.glPopMatrix( );
	}
	
	@Override
	public void drag( int x, int y ) {
		// TODO Auto-generated method stub
		return;
	}
	
	@Override
	public void onClick( int x, int y ) {
		// TODO Auto-generated method stub
		if ( this.mouseOver( x, y ) ) {
			this.focused = true;
		} else {
			this.focused = false;
		}
	}
	
	@Override
	public boolean mouseOver( int x, int y ) {
		if ( x >= this.x ) {
			if ( x <= ( this.x + this.width ) ) {
				if ( y >= this.y ) {
					if ( y <= ( this.y + this.height ) ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Please remember to get rid of the parameter; it's unneeded. TODO REMOVE
	 * PARAMS
	 * 
	 * @param key
	 */
	public void keyPressed( int key ) {
		
		char c = Keyboard.getEventCharacter( );
		
		if ( this.focused ) {
			if ( ( key == 14 ) && ( text.length( ) > 0 ) )
			{
				text = text.substring( 0, text.length( ) - 1 );
				return;
			}
			if ( ( ( allowedCharacters.indexOf( c ) >= 0 ) || ( c > ' ' ) ) && ( text.length( ) < 100 ) && ( c != "`".charAt( 0 ) ) && ( key != 14 ) )
			{
				text += c;
				return;
			}
			if ( Keyboard.getEventKey( ) == 28 ) {
				if ( this.text.equalsIgnoreCase( "clear" ) ) {
					for ( Item e : this.parent.children ) {
						if ( !( e instanceof TextArea ) ) {
							parent.removeLater( e );
						}
					}
					this.text = "";
					return;
				}
				String[ ] cmd = this.text.split( " " );
				final boolean isList = cmd [ 0 ].equalsIgnoreCase( "list" ) ? true : false;
				ConsoleHelper chelp = com.kodehawa.util.Console.chelper;
				final String add = !isList ? chelp.parse( cmd ) : chelp.parse( cmd ).substring( 0, chelp.parse( cmd ).length( ) / 2 );
				final String add2 = !isList ? "" : chelp.parse( cmd ).substring( chelp.parse( cmd ).length( ) / 2 );
				this.parent.addLater( new Label( this.text, 0xffffffff ) {
					@Override
					public void draw( ) {
						GL11.glPushMatrix( );
						GL11.glScaled( 0.5, 0.5, 0.5 );
						if ( add != null ) {
							// TTFRenderer.drawTTFString( Colony.guiFont, add, (
							// x * 2 ) + 6, ( y * 2 ) - 6, color );
							/* String, x, y, width, color */
							CheatBase.instance.minecraft.fontRenderer.drawSplitString( add, x * 2, y * 2, 236, 0xffffff );
						} else {
							// TTFRenderer.drawTTFString( Colony.guiFont,
							// "ERROR PROCESSING COMMAND", ( x * 2 ) + 6, ( y *
							// 2 ) - 6, color );
							CheatBase.instance.minecraft.fontRenderer.drawSplitString( "COMMAND COULD NOT BE PROCESSED", ( x * 2 ) - 2, y * 2, 100, 0xffffff );
						}
						GL11.glPopMatrix( );
					}
				} );
				if ( !add2.equalsIgnoreCase( "" ) ) {
					this.parent.addLater( new Label( this.text, 0xffffffff ) {
						@Override
						public void draw( ) {
							GL11.glPushMatrix( );
							GL11.glScaled( 0.5, 0.5, 0.5 );
							if ( add != null ) {
								// TTFRenderer.drawTTFString( Colony.guiFont,
								// add, (
								// x * 2 ) + 6, ( y * 2 ) - 6, color );
								/* String, x, y, width, color */
								CheatBase.instance.minecraft.fontRenderer.drawSplitString( add2, x * 2, y * 2, 236, 0xffffff );
							} else {
								// TTFRenderer.drawTTFString( Colony.guiFont,
								// "ERROR PROCESSING COMMAND", ( x * 2 ) + 6, (
								// y *
								// 2 ) - 6, color );
								CheatBase.instance.minecraft.fontRenderer.drawSplitString( "COMMAND COULD NOT BE PROCESSED", ( x * 2 ) - 2, y * 2, 100, 0xffffff );
							}
							GL11.glPopMatrix( );
						}
					} );
				}
				this.text = "";
			}
		}
	}
	
	static
	{
		allowedCharacters = ChatAllowedCharacters.allowedCharacters;
	}
}
