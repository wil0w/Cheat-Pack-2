package com.kodehawa.console;

import net.minecraft.src.EntityClientPlayerMP;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.waypoints.Waypoint;

public class WaypointCommand implements BaseCommand {
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		EntityClientPlayerMP player = CheatBase.instance.minecraft.thePlayer;
		double x = player.posX;
		double y = player.posY;
		double z = player.posZ;
		
		try {
			if ( cmd [ 1 ].equalsIgnoreCase( "add" ) ) {
				CheatBase.instance.wmanager.addWaypoint( new Waypoint( cmd [ 2 ], x, y, z ) );
				CheatBase.instance.wmanager.saveWaypoints( );
			} else if ( cmd [ 1 ].equalsIgnoreCase( "remove" ) ) {
				CheatBase.instance.wmanager.removeWaypoint( cmd [ 2 ] );
				CheatBase.instance.wmanager.saveWaypoints( );
			} else {
				throw new NullPointerException( );
			}
		} catch ( Exception e ) {
			showHelp( );
			ConsoleHelper.addMessage( cmd [ 0 ] + " " + cmd [ 1 ] + " " + cmd [ 2 ] );
			e.printStackTrace( );
		}
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "waypoint";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <add/remove> <name>" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
