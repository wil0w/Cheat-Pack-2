package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ChestESP extends Mod implements Tickable {
	
	private final CheatBase cb;
	private final Minecraft mc;
	
	public ChestESP( CheatBase c, Minecraft m ) {
		super( Mods.CHESTESP );
		cb = c;
		mc = m;
		
		
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEnable( ) {
		cb.addToTick( this );
		Vars.cesp = true;
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cb.removeFromTick( this );
		Vars.cesp = false;
		cb.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
