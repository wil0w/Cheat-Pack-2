package com.kodehawa.console;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class AddFriend implements BaseCommand {
	
	private final String name;
	String endres = "";
	
	public AddFriend( ) {
		this.name = "friend";
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
	
	public String output( String[ ] cmd ) {
		try {
			if ( cmd.length <= 1 ) {
				throw new NullPointerException( );
			} else {
				Vars.friends.add( cmd [ 1 ] );
				CheatBase.instance.femanager.writeFriends( );
				return cmd [ 1 ] + " is now a friend!";
			}
			
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
