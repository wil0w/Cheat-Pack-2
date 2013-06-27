/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet10Flying;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleFly extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	private final Minecraft mc;
	public NetClientHandler sendQueue;
	public boolean onGround = true;

	
	public ModuleFly( CheatBase c, Minecraft m ) {
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
		mc.thePlayer.setAir( 280 );
		mc.thePlayer.setAir( 120 );
	
		Packet11PlayerPosition.onGround = true;
	    Packet10Flying.onGround = true;
		Packet12PlayerLook.onGround = true;
		Packet13PlayerLookMove.onGround = true;
		
	}
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.capabilities.setFlySpeed(0.2F);
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
		cheatbase.getUtils( ).addChatMessage( "Take care with the bug!" );
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
