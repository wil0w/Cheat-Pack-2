/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.util;

import java.io.File;
import java.io.PrintWriter;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;

import com.kodehawa.CheatBase;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.util.waypoints.Waypoint;

public class WaypointManager {
	File directory;
	String currentLevelName;
	int waypointDimension;
	int currentDimension;
	public static ArrayList<Waypoint> wayPts;
	
	public WaypointManager( Minecraft mc ) {
		directory = new File( mc.mcDataDir, "/CP2/waypoints.cfg" );
		if ( !directory.exists( ) ) {
			directory.mkdirs( );
		}
		SocketAddress var67 = this.getRemoteSocketAddress( mc.thePlayer );
		String var70;
		var70 = var67.toString( ).replaceAll( "[\r\n]", "" );
		Pattern.compile( "(.*)/(.*):([0-9]+)" ).matcher( var70 );
		
		String var63 = var67.toString( );
		
		/*
		var63 = var75.group( 1 );
		
		if ( var63.isEmpty( ) )
		{
			var63 = var75.group( 2 );
		}
		
		if ( !var75.group( 3 ).equals( "25565" ) )
		{
			var63 = var63 + "[" + var75.group( 3 ) + "]";
		}*/
		
		this.currentLevelName = var63;
	}
	
	public void tick( ) {
		SocketAddress var67 = this.getRemoteSocketAddress( CheatBase.instance.minecraft.thePlayer );
		String var70;
		var70 = var67.toString( ).replaceAll( "[\r\n]", "" );
		Matcher var75 = Pattern.compile( "(.*)/(.*):([0-9]+)" ).matcher( var70 );
		
		String var63;
		
		var63 = var75.group( 1 );
		
		if ( var63.isEmpty( ) )
		{
			var63 = var75.group( 2 );
		}
		
		if ( !var75.group( 3 ).equals( "25565" ) )
		{
			var63 = var63 + "[" + var75.group( 3 ) + "]";
		}
		
		this.currentLevelName = var63;
	}
	
	private static SocketAddress getRemoteSocketAddress( EntityPlayer var0 )
	{
		NetClientHandler var1 = ( (EntityClientPlayerMP) var0 ).sendQueue;
		INetworkManager var2 = var1.getNetManager( );
		return var2 == null ? null : var2.getSocketAddress( );
	}
	
	public void saveWaypoints( )
	{
		File var1 = new File( directory, this.currentLevelName + ".DIM" + this.waypointDimension + ".points" );
		
		if ( var1.isDirectory( ) )
		{
			ConsoleHelper.addMessage( "\u00a7E[Colony Waypoint Manager] Error Saving Waypoints: File is dir" );
			// error("[Rei\'s Minimap] Error Saving Waypoints: (" + var1 +
			// ") is directory.");
		}
		else
		{
			try
			{
				PrintWriter var2 = new PrintWriter( var1, "UTF-8" );
				Iterator var3 = this.wayPts.iterator( );
				
				while ( var3.hasNext( ) )
				{
					Waypoint var4 = (Waypoint) var3.next( );
					var2.println( var4 );
				}
				
				var2.flush( );
				var2.close( );
				ConsoleHelper.addMessage( "\u00a7E[Waypoint Manager] Waypoints saved!" );
			} catch ( Exception var5 )
			{
				ConsoleHelper.addMessage( "\u00a7E[Waypoint Manager] Error Saving Waypoints" );
				var5.printStackTrace( );
			}
		}
	}
	
	public void loadWaypoints( )
	{
		wayPts = null;
		Pattern var1 = Pattern.compile( Pattern.quote( this.currentLevelName ) + "\\.DIM(-?[0-9])\\.points" );
		int var2 = 0;
		String[ ] var4 = directory.list( );
		int var5;
		if ( ( var4.length > 0 ) || ( (Integer) var4.length != null ) ) {
			var5 = var4.length;
		} else {
			var5 = 0;
		}
		
		for ( int var6 = 0; var6 < var5; ++var6 )
		{
			String var7 = var4 [ var6 ];
			Matcher var8 = var1.matcher( var7 );
			
			if ( var8.matches( ) )
			{
				int var9 = Integer.parseInt( var8.group( 1 ) );
				ArrayList<Waypoint> var10 = new ArrayList<Waypoint>( );
				Scanner var11 = null;
				
				try
				{
					var11 = new Scanner( new File( directory, var7 ), "UTF-8" );
					
					while ( var11.hasNextLine( ) )
					{
						Waypoint var12 = Waypoint.load( var11.nextLine( ) );
						
						if ( var12 != null )
						{
							var10.add( var12 );
							++var2;
						}
					}
				} catch ( Exception var16 )
				{
					;
				} finally
				{
					if ( var11 != null )
					{
						var11.close( );
					}
				}
				
				// this.wayPtsMap.put( Integer.valueOf( var9 ), var10 );
				
				if ( var9 == this.currentDimension )
				{
					this.wayPts = var10;
				}
			}
		}
		
		if ( this.wayPts == null )
		{
			this.wayPts = new ArrayList<Waypoint>( );
		}
		
		if ( var2 != 0 )
		{
			ConsoleHelper.addMessage( "\u00a7E[Colony Waypoint Manager] " + var2 + " Waypoints loaded for " + this.currentLevelName );
		}
	}
	
	public void addWaypoint( Waypoint e ) {
		this.wayPts.add( e );
	}
	
	public void removeWaypoint( String s ) {
		this.wayPts.remove( this.getWaypointByName( s ) );
	}
	
	public Waypoint getWaypointByName( String s ) {
		for ( Waypoint e : this.wayPts ) {
			if ( e.name.equalsIgnoreCase( s ) ) {
				return e;
			}
		}
		return null;
	}
}
