package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class Sprint extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	private final Minecraft mc;
	
	public Sprint( CheatBase cb, Minecraft m ) {
		super( Mods.SPRINT );
		cheatbase = cb;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		double speedmul = 1 + ( Vars.speedbonus / 10 );
		// TODO Auto-generated method stub
		if ( mc.thePlayer.movementInput.moveForward > 0F ) {
			mc.thePlayer.setSprinting( true );
			if ( Vars.doublespeed ) {
				if ( mc.thePlayer.onGround ) {
					mc.thePlayer.motionX *= speedmul;
					mc.thePlayer.motionZ *= speedmul;
				}
			}
		}
	}
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
