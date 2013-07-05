
package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleFly extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	private final Minecraft mc;
	public NetClientHandler sendQueue;
	public EntityPlayerMP playerEntity;
	
	
	
	public ModuleFly( CheatBase c, Minecraft m ) {
		super( Mods.Fly );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
		if( !CheatBase.getInstance( ).getWrapper.getMinecraft( ).thePlayer.capabilities.isFlying ) {
            CheatBase.getInstance( ).getWrapper.getMinecraft( ).thePlayer.capabilities.isFlying = true;
        }
        EntityClientPlayerMP ep = CheatBase.getInstance( ).getWrapper.getMinecraft( ).thePlayer;
        ep.sendQueue.addToSendQueue( new Packet13PlayerLookMove( ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround ) );
    }
	

	@Override
	public void onEnable( ) {
		cheatbase.addToTick( this );
		mc.thePlayer.capabilities.isFlying = true;
		cheatbase.getUtils( ).addChatMessage(getActive());
		cheatbase.getUtils( ).addChatMessage("I believe I can fly :)!");
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		mc.thePlayer.capabilities.isFlying = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
