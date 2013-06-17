package com.kodehawa.console;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityOtherPlayerMP;
import net.minecraft.src.EntityPlayer;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;

public class SpyList implements BaseCommand {
	
	private final String name;
	
	public SpyList( ) {
		this.name = "spylist";
	}
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) );
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		try {
			String res = "";
			Entity e;
			for ( int i = 0; i < CheatBase.instance.minecraft.theWorld.loadedEntityList.size( ); i++ ) {
				e = (Entity) CheatBase.instance.minecraft.theWorld.loadedEntityList.get( i );
				if ( e instanceof EntityOtherPlayerMP ) {
					String u = ( (EntityPlayer) e ).username;
					res += u + ", ";
				}
			}
			return res;
			
		} catch ( Exception e ) {
			return showHelp( );
		}
	}
	
}
