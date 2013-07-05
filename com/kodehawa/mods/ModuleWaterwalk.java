package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleWaterwalk extends Mod implements Tickable {
	
	private final CheatBase cb;
	private final Minecraft mc;
	
	public ModuleWaterwalk( CheatBase co, Minecraft m ) {
		super( Mods.Waterwalk );
		cb = co;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		if ( getPlayer( ).isWet( ) )
		{
			getPlayer( ).setSprinting( false );
			getPlayer( ).jump();
			getPlayer( ).motionY /= 2;
		}
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
	
	public EntityClientPlayerMP getPlayer( ) {
		return this.mc.thePlayer;
	}
	
	public boolean isMoving( )
	{
		return Math.sqrt( ( getPlayer( ).motionX * getPlayer( ).motionX ) + ( getPlayer( ).motionZ * getPlayer( ).motionZ ) ) >= 0.04;
	}
}
