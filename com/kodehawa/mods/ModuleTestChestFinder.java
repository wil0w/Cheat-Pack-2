package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleTestChestFinder extends Mod implements Tickable {
	
	protected Minecraft mc;
	protected CheatBase cb;

	/**
	 * I need OpenGL for this. Dammit!
	 * @param mod
	 * @param m
	 * @param c
	 */
	
	public ModuleTestChestFinder(Mods mod, Minecraft m, CheatBase c) {
		super( Mods.ChestESP );
		mc = m;
		cb = c;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}



}
