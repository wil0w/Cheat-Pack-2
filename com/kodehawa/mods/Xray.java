package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class Xray extends Mod implements Tickable {
	
	private static CheatBase cheatbase;
	private static Minecraft mc;
	
	public Xray( CheatBase cb, Minecraft m ) {
		super( Mods.XRAY );
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
		Vars.xray = true;
		mc.renderGlobal.loadRenderers( );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		Vars.xray = false;
		mc.renderGlobal.loadRenderers( );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
