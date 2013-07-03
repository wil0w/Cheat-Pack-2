package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleDayTime extends Mod implements Tickable {

	private CheatBase cb;
	private Minecraft mc;
	
	public ModuleDayTime(CheatBase c, Minecraft m) {
		super( Mods.DAYTIME );
	
		// TODO Auto-generated constructor stub
		cb = c;
		mc = m;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if( CheatBase.getInstance( ).getWrapper.getMinecraft( ).theWorld.getWorldInfo( ).getWorldTime( ) > 12000L ) {
            CheatBase.getInstance( ).getWrapper.getMinecraft( ).theWorld.getWorldInfo( ).setWorldTime( 0L );
        }
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		cb.addToTick( this );
		cb.getUtils().addChatMessage("Time ajusted to Day!");
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		cb.getUtils().addChatMessage("Disabled, but it's still day :)");
	}

}
