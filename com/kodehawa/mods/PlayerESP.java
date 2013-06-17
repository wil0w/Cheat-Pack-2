package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class PlayerESP extends Mod implements Tickable {
	
	private CheatBase cheatbase;
	private Minecraft mc;
	
	public PlayerESP( CheatBase cb, Minecraft m ) {
		super( Mods.PLAYERESP );
		cheatbase = cb;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		Vars.pesp = true;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		Vars.pesp = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
