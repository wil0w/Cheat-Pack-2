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

import net.minecraft.src.ChatAllowedCharacters;
import net.minecraft.src.GuiScreen;

import org.lwjgl.input.Keyboard;

import com.kodehawa.console.ConsoleHelper;

public class Console extends GuiScreen
{
	protected String message;
	private int updateCounter;
	private static final String allowedCharacters;
	public static ConsoleHelper chelper;
	
	public Console( )
	{
		chelper = new ConsoleHelper( );
		message = "";
		updateCounter = 0;
	}
	
	@Override
	public void initGui( )
	{
		Keyboard.enableRepeatEvents( true );
	}
	
	@Override
	public void onGuiClosed( )
	{
		Keyboard.enableRepeatEvents( false );
	}
	
	@Override
	public void updateScreen( )
	{
		updateCounter++ ;
	}
	
	@Override
	protected void keyTyped( char c, int i )
	{
		if ( i == 1 )
		{
			mc.displayGuiScreen( null );
			return;
		}
		if ( i == 28 )
		{
			String[ ] cmd = message.split( " " );
			
			chelper.parse( cmd );
			
			mc.displayGuiScreen( null );
			return;
			
		}
		if ( ( i == 14 ) && ( message.length( ) > 0 ) )
		{
			message = message.substring( 0, message.length( ) - 1 );
		}
		if ( ( ( allowedCharacters.indexOf( c ) >= 0 ) || ( c > ' ' ) ) && ( message.length( ) < 100 ) && ( c != "`".charAt( 0 ) ) )
		{
			message += c;
		}
	}
	
	@Override
	public void drawScreen( int i, int j, float f )
	{
		drawRect( 2, height - 14, width - 2, height - 2, 0x88000088 );
		drawString( fontRenderer, ( new StringBuilder( ) ).append( "> " ).append( message ).append( ( ( updateCounter / 6 ) % 2 ) != 0 ? "" : "_" ).toString( ), 4, height - 12, 0xe0e0e0 );
		super.drawScreen( i, j, f );
	}
	
	@Override
	protected void mouseClicked( int i, int j, int k )
	{
		super.mouseClicked( i, j, k );
		if ( k != 0 )
		{
			return;
		}
		
		if ( ( message.length( ) > 0 ) && !message.endsWith( " " ) )
		{
			message += " ";
		}
		
		byte byte0 = 100;
		if ( message.length( ) > byte0 )
		{
			message = message.substring( 0, byte0 );
		}
	}
	
	static
	{
		allowedCharacters = ChatAllowedCharacters.allowedCharacters;
	}
}
