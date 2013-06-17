package com.kodehawa.console;

import net.minecraft.client.Minecraft;

import com.kodehawa.util.ChatColour;

public class Flood implements BaseCommand {
	String endres = "";
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "flood";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <message>" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output( String[ ] cmd ) {
		try {
			if ( cmd.length == 1 ) {
				throw new NullPointerException( );
			}
			StringBuilder result = new StringBuilder( );
			for ( int i = 0; i < ( cmd.length - 1 ); i++ ) {
				result.append( cmd [ i + 1 ] ).append( " " );
			}
			for ( int j = 0; j < 25; j++ ) {
				Minecraft.getMinecraft( ).thePlayer.sendChatMessage( result.toString( ) );
			}
			return "Spam successful!";
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
