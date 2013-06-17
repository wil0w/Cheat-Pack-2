package com.kodehawa.console;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class AddEnemy implements BaseCommand {
	
	private final String name;
	String endres = "";
	
	public AddEnemy( ) {
		this.name = "enemy";
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
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <username>" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output( String[ ] cmd ) {
		try {
			if ( cmd.length <= 1 ) {
				throw new NullPointerException( );
			} else {
				Vars.enemies.add( cmd [ 1 ] );
				CheatBase.instance.femanager.writeEnemies( );
				return cmd [ 1 ] + " is now an enemy!";
			}
			
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
