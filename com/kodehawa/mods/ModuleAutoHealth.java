package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.PotionEffect;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleAutoHealth extends Mod implements Tickable {

	
	private EntityClientPlayerMP entityplayer;
	
	
	public ModuleAutoHealth( CheatBase c, Minecraft m ) {
		super( Mods.Autohealth);
		cb = c;
		minecraft = m;
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * How simple can be this? Here it's a example
	 */

	@Override
	public void tick() {
		minecraft.thePlayer.setHealth(20.0F);
	}

	@Override
	public void onEnable() {
		cb.addToTick( this );		
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		cb.removeFromTick( this );
		minecraft.thePlayer.setHealth(20.0F);
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	public Minecraft minecraft;
	public CheatBase cb;
	
}
