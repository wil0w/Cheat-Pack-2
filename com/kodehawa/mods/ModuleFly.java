
package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Minecraft;
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
	public EntityPlayerMP playerEntity;
	public Packet11PlayerPosition p11;
	public Packet10Flying p10;
	public Packet12PlayerLook p12;
	public Packet13PlayerLookMove p13;
	
	
	public ModuleFly( CheatBase c, Minecraft m ) {
		super( Mods.FLY );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		/*mc.thePlayer.capabilities.isFlying = true;
		mc.thePlayer.isAirBorne = true;
		mc.thePlayer.setJumping(true);
		mc.thePlayer.setAir( 280 );
		mc.thePlayer.setAir( 120 );
	
		Packet11PlayerPosition.onGround = true;
		Packet10Flying.onGround = true;
		Packet12PlayerLook.onGround = true;
		Packet13PlayerLookMove.onGround = true;
		*/
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
		cheatbase.getUtils( ).addChatMessage("Take care with the bug!");
	}
	
	@Override
	public void onDisable( ) {
		cheatbase.removeFromTick( this );
		mc.thePlayer.capabilities.isFlying = false;
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}
	
}
