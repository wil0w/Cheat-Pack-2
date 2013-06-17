package com.kodehawa.console;

import com.kodehawa.util.ChatColour;

public class Ubu implements BaseCommand{
	
	String endres = "";
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output( );
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Publicidad";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Visit: " + ChatColour.AQUA + this.getName( ) + "Pagoru Mod - Link Thread" );
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return showHelp();
	}

}
