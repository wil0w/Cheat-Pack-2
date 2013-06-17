package com.kodehawa.console;

import com.kodehawa.gui.CGuiIngame;
import com.kodehawa.util.ChatColour;

public class Reloader implements BaseCommand {
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		try {
			CGuiIngame.cb.instance.minecraft.thePlayer.addChatMessage( ChatColour.RED + "Reloading. Please hold still." );
			CGuiIngame.cb.reload( );
			CGuiIngame.cb.instance.minecraft.renderGlobal.loadRenderers( );
			CGuiIngame.cb.instance.minecraft.thePlayer.addChatMessage( ChatColour.RED + "Reload complete!" );
		} catch ( Exception e ) {
			showHelp( );
		}
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "reload";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return "Reloaded!";
	}
	
}
