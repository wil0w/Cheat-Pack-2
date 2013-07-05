package com.kodehawa.mods;

import net.minecraft.src.Minecraft;
import net.minecraft.src.PotionEffect;

import com.kodehawa.CheatBase;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public class ModuleFasterWalk extends Mod implements Tickable {

	public ModuleFasterWalk( CheatBase c, Minecraft m ) {
		super( Mods.Fasterwalk );
		cb = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		mc.thePlayer.capabilities.setPlayerWalkSpeed(0.05F);
}

	@Override
	public void onEnable() {
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
		cb.getUtils().addChatMessage(ChatColour.DARK_GRAY + "You now can walk faster");
	}

	@Override
	public void onDisable() {
		cb.removeFromTick( this );
		mc.thePlayer.capabilities.setPlayerWalkSpeed(0.1F);
        cb.getUtils( ).addChatMessage( getActive( ) );
	}

	public Minecraft mc;
	public CheatBase cb;

}
