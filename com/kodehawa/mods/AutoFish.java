package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

/**
 * 
 * The shouldWeReelInFishingRod variable (Bad name, ik) is set from
 * NetClientHandler.handleEntityVelocity( Packet28EntityVelocity
 * par1Packet28EntityVelocity ).
 * 
 * Basically, NetClientHandler checks if the fishing lure moves, and this reels
 * it in.
 * 
 */
public class AutoFish extends Mod implements Tickable {
	
	private final CheatBase cb;
	
	public AutoFish( CheatBase c, Minecraft mc ) {
		super( Mods.AUTOFISH );
		
		this.cb = c;
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEnable( ) {
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cb.removeFromTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
