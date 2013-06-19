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

package com.kodehawa.console;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class ConsoleHelper {
	public static ArrayList<BaseCommand> commands;
	
	public ConsoleHelper( ) {
		commands = new ArrayList<BaseCommand>( );
		addCommand( new Help( ) );
		addCommand( new Spam( ) );
		addCommand( new Flood( ) );
		addCommand( new Enchant( ) );
		addCommand( new Speed( ) );
		addCommand( new Bind( ) );
		addCommand( new List( ) );
		addCommand( new AddFriend( ) );
		addCommand( new AddEnemy( ) );
		addCommand( new TimerPlus( ) );
		addCommand( new Reloader( ) );
		addCommand( new Spy( ) );
		addCommand( new SpyList( ) );
		// Making GUI for this.
		//addCommand( new XrayAdd( ) );
	}
	
	public void addCommand( BaseCommand cmd ) {
		commands.add( cmd );
	}
	
	/**
	 * Runs a command
	 * 
	 * @param cmd
	 */
	public static String parse( String[ ] cmd ) {
		for ( int i = 0; i < commands.size( ); i++ ) {
			if ( cmd [ 0 ].equalsIgnoreCase( commands.get( i ).getName( ) ) ) {
				commands.get( i ).onRun( cmd );
				return commands.get( i ).output( );
			}
		}
		return null;
	}
	
	public ArrayList getCommands( ) {
		return this.commands;
	}
	
	public static void addMessage( String msg ) {
		Minecraft.getMinecraft( ).thePlayer.addChatMessage( msg );
	}
}
