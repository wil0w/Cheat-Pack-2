package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleFastPlace extends Mod implements Tickable {
	
	CheatBase cb;
	Minecraft mc;
	
	public ModuleFastPlace( CheatBase cb, Minecraft mc ) {
		super( Mods.FASTPLACE );
		// TODO Auto-generated constructor stub
		this.cb = cb;
		this.mc = mc;
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		mc.rightClickDelayTimer = 0;
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
