package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.Packet12PlayerLook;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleNoFall extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	
	
	public ModuleNoFall( CheatBase cb, Minecraft m ) {
		super( Mods.NOFALL );
		cheatbase = cb;
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		EntityClientPlayerMP ep = CheatBase.getInstance().getWrapper.getMinecraft( ).thePlayer;
        ep.sendQueue.addToSendQueue( new Packet13PlayerLookMove( ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround ) );
    }
	
	
	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
	    cheatbase.getUtils( ).addChatMessage( getActive( ) );
	    cheatbase.getUtils().addChatMessage("Fall damage? What fall damage?");
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
