package com.kodehawa.console;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatBase;
import com.kodehawa.mods.Mod;

public class List implements BaseCommand {
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "list";
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
		for ( Mod m : CheatBase.instance.mmanager.mods ) {
			s += m.name + " - " + Keyboard.getKeyName( m.keyBind ) + ", ";
		}
		return s;
	}
}
