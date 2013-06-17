package com.kodehawa.util;

import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityFishHook;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.IntegratedServer;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet15Place;
import net.minecraft.src.Packet28EntityVelocity;

import com.kodehawa.CheatBase;

public class CNetClientHandler extends NetClientHandler
{
	
	/** RNG. */
	Random rand = new Random( );
	
	public CNetClientHandler( Minecraft par1Minecraft, String par2Str, int par3 ) throws IOException
	{
		super( par1Minecraft, par2Str, par3 );
	}
	
	public CNetClientHandler( Minecraft par1Minecraft, String par2Str, int par3, GuiScreen par4GuiScreen ) throws IOException
	{
		super( par1Minecraft, par2Str, par3, par4GuiScreen );
		
	}
	
	public CNetClientHandler( Minecraft par1Minecraft, IntegratedServer par2IntegratedServer ) throws IOException
	{
		super( par1Minecraft, par2IntegratedServer );
	}
	
	/**
	 * Packet handler
	 */
	@Override
	public void handleEntityVelocity( Packet28EntityVelocity par1Packet28EntityVelocity )
	{
		super.handleEntityVelocity( par1Packet28EntityVelocity );
		
		Entity var2 = getEntityByID( par1Packet28EntityVelocity.entityId );
		
		if ( var2 instanceof EntityFishHook )
		{
			if ( CheatBase.instance.mmanager.getHackByName( "AUTOFISH" ).isActive( ) ) {
				if ( var2.motionX == 0 ) {
					if ( var2.motionZ == 0 ) {
						if ( var2.motionY != 0 ) {
							for ( int i = 0; i < 2; i++ ) {
								mc.getNetHandler( ).addToSendQueue( new Packet15Place( -1, -1, -1, 255, null, i, i, i ) );
							}
						}
					}
				}
			}
		}
	}
	
}
