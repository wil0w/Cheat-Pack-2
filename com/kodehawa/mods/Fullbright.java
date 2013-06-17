package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.PotionEffect;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class Fullbright extends Mod implements Tickable {
	
	public Fullbright( CheatBase rc, Minecraft mc ) {
		super( Mods.FULLBRIGHT );
		cb = rc;
		minecraft = mc;
		//oldGamma = minecraft.gameSettings.gammaSetting;
	}
	
	@Override
	public void onEnable( ) {
		cb.getUtils( ).addChatMessage( getActive( ) );
		cb.addToTick( this );
	}
	
	@Override
	public void onDisable( ) {
		cb.getUtils( ).addChatMessage( getActive( ) );
		//minecraft.thePlayer.removePotionEffect( 16 );
		minecraft.gameSettings.gammaSetting = 0.2F;
		cb.removeFromTick( this );
	}
	
	@Override
	public void tick( ) {
		//minecraft.thePlayer.addPotionEffect( new PotionEffect( 16, 99999999, 255, true ) );
		minecraft.gameSettings.gammaSetting = 782;
	}
	
	private final CheatBase cb;
	private final Minecraft minecraft;
	public float oldGamma;
	
}
