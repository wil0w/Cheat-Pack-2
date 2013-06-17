package com.kodehawa.console;

import com.kodehawa.util.Console;

public class Help implements BaseCommand {
	
	private final String name;
	
	public Help( ) {
		this.name = "help";
	}
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		// this.output( );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( "" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		String s = "";
		for ( BaseCommand e : Console.chelper.commands ) {
			s += e.getName( ) + ", ";
		}
		s.substring( 0, s.length( ) - 2 );
		return s;
	}
	
}
