package com.kodehawa.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiContainerCreative;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class InvEdit extends Mod implements Tickable{
	
	private final Minecraft mc;
	private final CheatBase cheatbase;

	public InvEdit( CheatBase c, Minecraft m ) {
		super( Mods.CREATIVEINVENTORY );
		cheatbase = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.mc.displayGuiScreen(new GuiContainerCreative(this.mc.thePlayer));
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		cheatbase.addToTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		cheatbase.removeFromTick( this );
		cheatbase.getUtils( ).addChatMessage( getActive( ) );
	}

}
