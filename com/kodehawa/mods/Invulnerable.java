package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.CheatPack;
import com.kodehawa.util.Tickable;

public class Invulnerable extends Mod implements Tickable {

	public Invulnerable( CheatBase c, Minecraft m ) {
		super( Mods.INVULNERABLE );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		//mc.thePlayer.capabilities.disableDamage = true;
		cp.getBase(mc).setInvulnerable(mc.thePlayer, invulnerable);
		//mc.thePlayer.capabilities.allowFlying = true;
	}

	@Override
	public void onEnable() {
		cheatbase.addToTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		cheatbase.removeFromTick( this );
		mc.thePlayer.capabilities.disableDamage = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
		
	}
	
	public static boolean invulnerable;
    public CheatBase cheatbase;
    public Minecraft mc;
    public CheatPack cp;
}
