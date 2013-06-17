package com.kodehawa.console;

public interface BaseCommand {
	public void onRun( String[ ] cmd );
	
	public String getName( );
	
	public String showHelp( );
	
	public String output( );
}
