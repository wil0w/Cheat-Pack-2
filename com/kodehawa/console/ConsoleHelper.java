

package com.kodehawa.console;

import java.util.ArrayList;

import net.minecraft.src.Minecraft;

public class ConsoleHelper {
	public static ArrayList<BaseCommand> commands;
	
	public ConsoleHelper( ) {
		commands = new ArrayList<BaseCommand>( );
		addCommand( new Help( ) );
		addCommand( new Enchant( ) );
		addCommand( new Speed( ) );
		//It's buggy D:
		//addCommand( new Bind( ) );
		addCommand( new List( ) );
		addCommand( new AddFriend( ) );
		addCommand( new AddEnemy( ) );
		//Reload the whole mod.
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
