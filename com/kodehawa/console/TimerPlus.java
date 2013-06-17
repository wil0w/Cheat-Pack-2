package com.kodehawa.console;

import net.minecraft.src.Timer;

import com.kodehawa.util.ChatColour;

public class TimerPlus implements BaseCommand {
	String endres = "";
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "timer";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <speed>" );
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
			} else if ( cmd.length == 2 ) {
				Timer.timerSpeed = Float.parseFloat( cmd [ 1 ] );
				return new String( ChatColour.RED + "Timer speed set to " + cmd [ 1 ] + "!" );
			} else {
				throw new NullPointerException( );
			}
		} catch ( Exception e ) {
			// ConsoleHelper.addMessage( ChatColour.DARK_RED +
			// e.printStackTrace( ) );
			return showHelp( );
		}
	}
}
