package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.PotionEffect;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class AutoHealth extends Mod implements Tickable {

	public AutoHealth( CheatBase c, Minecraft m ) {
		super( Mods.AUTOHEALTH );
		cb = c;
		minecraft = m;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		minecraft.thePlayer.addPotionEffect( new PotionEffect( 10, 9999999, 2, true ) );
		minecraft.thePlayer.addPotionEffect( new PotionEffect( 11, 9999999, 4, true ) );
	}

	@Override
	public void onEnable() {
		cb.addToTick( this );		
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		cb.removeFromTick( this );
		minecraft.thePlayer.removePotionEffect( 10 );
		minecraft.thePlayer.removePotionEffect( 11 );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	public Minecraft minecraft;
	public CheatBase cb;
	
}
