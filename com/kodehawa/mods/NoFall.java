package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class NoFall extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	
	public NoFall( CheatBase cb, Minecraft m ) {
		super( Mods.NOFALL );
		cheatbase = cb;
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		Packet11PlayerPosition.onGround = true;
		Packet12PlayerLook.onGround = true;
		Packet13PlayerLookMove.onGround = true;
	}
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		Packet11PlayerPosition.onGround = true;
		Packet12PlayerLook.onGround = true;
		Packet13PlayerLookMove.onGround = true;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		Packet11PlayerPosition.onGround = false;
		Packet12PlayerLook.onGround = false;
		Packet13PlayerLookMove.onGround = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
