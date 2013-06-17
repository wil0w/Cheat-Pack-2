package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import com.kodehawa.CheatBase;
import com.kodehawa.util.GLHelper;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.WaypointManager;
import com.kodehawa.util.waypoints.Waypoint;

public class Waypoints extends Mod implements Tickable {
	
	private final CheatBase cb;
	
	public Waypoints( CheatBase cheatbase, Minecraft mc ) {
		super( Mods.WAYPOINTS );
		
		this.cb = cheatbase;
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		for ( Waypoint w : WaypointManager.wayPts ) {
			w.update( );
			GL11.glDisable( 2896 /*GL_LIGHTING*/);
			GLHelper.drawESP( w.dX, w.dY, w.dZ, w.red, w.green, w.blue );
			GLHelper.drawTag( w.getName( ), w.dX, w.dY, w.dZ );
			GL11.glEnable( 2896 /*GL_LIGHTING*/);
			GLHelper.drawWayPointTracer( w );
		}
	}
	
	@Override
	public void onEnable( ) {
		if ( cb.instance.wmanager == null ) {
			cb.instance.wmanager = new WaypointManager( CheatBase.instance.minecraft );
		}
		cb.instance.wmanager.loadWaypoints( );
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cb.removeFromTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
