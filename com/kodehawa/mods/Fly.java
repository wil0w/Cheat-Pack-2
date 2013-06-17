package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.CheatPack;
import com.kodehawa.util.Tickable;

public class Fly extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	private final Minecraft mc;
	public CheatPack cp;
	public static boolean flying;
	
	public Fly( CheatBase c, Minecraft m ) {
		super( Mods.FLY );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.isAirBorne = true;
		mc.thePlayer.isJumping = true;
		mc.thePlayer.setAir( 100 );
		mc.thePlayer.setAir( 10 );
		
		Packet11PlayerPosition.onGround = true;
		Packet12PlayerLook.onGround = true;
		Packet13PlayerLookMove.onGround = true;
		cp.getBase(mc).setFlying(mc.thePlayer, flying);
	}
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.capabilities.flySpeed = 0.1F;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		mc.thePlayer.capabilities.isFlying = false;
		Packet11PlayerPosition.onGround = false;
		Packet12PlayerLook.onGround = false;
		Packet13PlayerLookMove.onGround = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
