package com.kodehawa.util.waypoints;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.RenderManager;

import com.kodehawa.CheatBase;

public class Waypoint {
	public static ArrayList<Waypoint> wayPoints = new ArrayList<Waypoint>( );
	
	public final String name;
	private final double posX;
	private final double posY;
	private final double posZ;
	public double dX;
	public double dY;
	public double dZ;
	
	public float red, green, blue;
	
	public Waypoint( String name, double x, double y, double z ) {
		this.name = name;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.red = ( new Random( ).nextInt( 255 ) ) / 255F;
		this.green = ( new Random( ).nextInt( 255 ) ) / 255F;
		this.blue = ( new Random( ).nextInt( 255 ) ) / 255F;
		System.out.println( red + " " + green + " " + blue );
		update( );
		CheatBase.instance.wmanager.addWaypoint( this );
	}
	
	public void update( ) {
		dX = (int) posX - RenderManager.renderPosX;
		dY = (int) posY - RenderManager.renderPosY;
		dZ = (int) posZ - RenderManager.renderPosZ;
	}
	
	public String getName( ) {
		return name;
	}
	
	public double getX( ) {
		return posX;
	}
	
	public double getY( ) {
		return posY;
	}
	
	public double getZ( ) {
		return posZ;
	}
	
	public static Waypoint load( String var0 )
	{
		try
		{
			String[ ] var1 = var0.split( ":" );
			String var2 = var1 [ 0 ];
			int var3 = Integer.parseInt( var1 [ 1 ] );
			int var4 = Integer.parseInt( var1 [ 2 ] );
			int var5 = Integer.parseInt( var1 [ 3 ] );
			
			return new Waypoint( var2, var3, var4, var5 );
		} catch ( RuntimeException var12 )
		{
			var12.printStackTrace( );
			return null;
		}
	}
	
	@Override
	public String toString( )
	{
		int var1 = (int) ( this.red * 255.0F ) & 255;
		int var2 = (int) ( this.green * 255.0F ) & 255;
		int var3 = (int) ( this.blue * 255.0F ) & 255;
		int var4 = ( var1 << 16 ) | ( var2 << 8 ) | var3;
		return new String( this.name + this.posX + this.posY + this.posZ + Integer.valueOf( var4 ) + 1 );
	}
}
