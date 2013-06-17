package com.kodehawa.console;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Mod;
import com.kodehawa.util.ChatColour;

public class Bind implements BaseCommand {
	
	private final String name;
	String endres = "";
	
	public Bind( ) {
		this.name = "bind";
	}
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <hack> <key>" );
		// ConsoleHelper.addMessage( ChatColour.RED + "Hint: " + ChatColour.AQUA
		// + "To remove a keybind, do \'bind <hack> none\'!" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	public String output( String[ ] cmd ) {
		try {
			if ( cmd.length <= 1 ) {
				throw new NullPointerException( );
			} else if ( cmd.length == 2 ) {
				Mod m = CheatBase.mmanager.getHackByName( cmd [ 1 ] );
				CheatBase.instance.kmanager.writeNewKeybinds( );
				return new String( ChatColour.AQUA + m.name + " unbound!" );
			} else if ( cmd.length == 3 ) {
				Mod m = CheatBase.mmanager.getHackByName( cmd [ 1 ] );
				m.keyBind = Keyboard.getKeyIndex( cmd [ 2 ].toUpperCase( ) );
				CheatBase.instance.kmanager.writeNewKeybinds( );
				return new String( ChatColour.AQUA + m.name + " bound to " + cmd [ 2 ] + "!" );
			}
			return showHelp( );
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
