package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;

import com.kodehawa.CheatBase;
import com.kodehawa.util.EntityUtils;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Watcher;

public class KillAura extends Mod implements Tickable {
	
	private final CheatBase cheatbase;
	
	public KillAura( CheatBase rc, Minecraft mc ) {
		super( Mods.KILLAURA );
		cheatbase = rc;
		minecraft = mc;
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
	
	@Override
	public void tick( ) {
		
		for ( int i = 0; i < minecraft.theWorld.loadedEntityList.size( ); i++ ) {
			Entity ent = (Entity) minecraft.theWorld.loadedEntityList.get( i );
			
			int id = ent.entityId;
			long now = System.currentTimeMillis( );
			Watcher tracked = EntityUtils.getLastAffected( id );
			if ( tracked != null ) {
				if ( tracked.matches( ent, now ) ) {
					continue;
				}
			}
			
			EntityUtils.setLastAffected( id, ent );
			
			if ( ( ent == minecraft.thePlayer ) || !( ent instanceof EntityLiving ) || ent.isDead ) {
				continue;
			}
			
			if ( ( minecraft.thePlayer.getDistanceSqToEntity( ent ) <= 36D ) && !ent.isDead && minecraft.thePlayer.canEntityBeSeen( ent ) ) {
				minecraft.thePlayer.faceEntity( ent, 100F, 100F );
				minecraft.playerController.attackEntity( minecraft.thePlayer, ent );
				minecraft.thePlayer.swingItem( );
			}
		}
	}
	
	
	private final Minecraft minecraft;
	
}
