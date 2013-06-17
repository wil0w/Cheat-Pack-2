package com.kodehawa.console;

import com.kodehawa.mods.Vars;
import com.kodehawa.util.ChatColour;

public class Speed implements BaseCommand {
	String endres = "";
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "speed";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <0-9>" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output( String[ ] cmd ) {
		try {
			double result = Double.parseDouble( cmd [ 1 ] );
			Vars.speedbonus = result;
			return "Speed set to " + Vars.speedbonus + "!";
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
