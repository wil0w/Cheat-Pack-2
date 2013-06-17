package com.kodehawa.console;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityOtherPlayerMP;
import net.minecraft.src.EntityPlayer;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;

public class Spy implements BaseCommand {
	
	private final String name;
	
	String endres = "";
	
	public Spy( ) {
		this.name = "spy";
	}
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <username>" );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output( String[ ] cmd ) {
		try {
			if ( cmd.length <= 1 ) {
				CheatBase.instance.minecraft.renderViewEntity = CheatBase.instance.minecraft.thePlayer;
				return new String( ChatColour.RED + "Done spying!" );
			} else {
				Entity e;
				for ( int i = 0; i < CheatBase.instance.minecraft.theWorld.loadedEntityList.size( ); i++ ) {
					e = (Entity) CheatBase.instance.minecraft.theWorld.loadedEntityList.get( i );
					if ( e instanceof EntityOtherPlayerMP ) {
						if ( ( (EntityPlayer) e ).username.equalsIgnoreCase( cmd [ 1 ] ) || ( (EntityPlayer) e ).username.toLowerCase( ).contains( cmd [ 1 ].toLowerCase( ) ) ) {
							CheatBase.instance.minecraft.renderViewEntity = (EntityLiving) e;
							// Vars.spiedOn = (EntityPlayer) e;
							String u = ( (EntityPlayer) e ).username;
							return new String( ChatColour.RED + "Now spying on: " + u );
						}
					}
				}
				return new String( ChatColour.RED + "Player could not be found!" );
				
			}
			
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
}
