/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.kodehawa.CheatBase;
import com.kodehawa.CheatPack;

public class TranslationWritter {
	
	//Only for en_EN locale. For other please check the post.
	
    public File translationFile;
    public CheatPack init;
    public CheatBase cb;

	public TranslationWritter( ) {
	
	   translationFile = new File( CheatBase.instance.minecraft.mcDataDir, "/CP2/gui.lang" );
	   if ( !translationFile.exists( ) ) {
		try {
			
			
			translationFile.getParentFile( ).mkdirs( );
			translationFile.createNewFile( );
			FileWriter fstream = new FileWriter( translationFile );
			BufferedWriter out = new BufferedWriter( fstream );
			
			cb.LogAgent.logInfo("Writting Translation File...");
	        	
			out.write( "GuiInventory.LabelCrafting=Crafting" + "\r\n");
		    out.write( "GuiItemSelection.LabelItemSelection=Item selection" + "\r\n" );
			out.write( "GuiHealthFood.LabelTitle=Health and food" + "\r\n" );
			out.write( "GuiHealthFood.LabelFoodLevel=Food Level" + "\r\n" );
			out.write( "GuiHealthFood.LabelSaturation=Saturation" + "\r\n" );
			out.write( "GuiHealthFood.LabelExhaustion=Exhaustion" + "\r\n" );
			out.write( "GuiHealthFood.ButtonSet=Set" + "\r\n" );
			out.write( "GuiEnchanting.LabelTitle=Enchanting" + "\r\n" );
			out.write( "GuiExperience.LabelTitle=Experience" + "\r\n" );
			out.write( "GuiExperience.LabelLevel=Level" + "\r\n" );
			out.write( "GuiExperience.LabelPercentage=Percentage" + "\r\n" );
		    out.write( "GuiExperience.LabelScore=Score" + "\r\n" );
			out.write( "GuiExperience.ButtonSet=Set" + "\r\n" );
			out.write( "GuiPotionEffects.LabelTitle=Potion Effects" + "\r\n" );
			out.write( "GuiPotionEffects.LabelLevel=Level" + "\r\n" );
			out.write( "GuiPotionEffects.LabelMinutes=Minutes" + "\r\n" );
			out.write( "GuiPotionEffects.LabelSeconds=Seconds" + "\r\n" );
			out.write( "GuiPotionEffects.ButtonSet=Set" + "\r\n" );
			out.write( "GuiPotionEffects.ButtonUnset=Unset" + "\r\n");
			out.write( "GuiBrewing.LabelTitle=Brewing" + "\r\n");
            out.write( "GuiBrewing.LabelLevel=Level" + "\r\n" );
			out.write( "GuiBrewing.LabelMinutes=Minutes" + "\r\n");
			out.write( "GuiBrewing.ButtonSet=Set" + "\r\n");
			out.write( "GuiTeleport.LabelTitle=Teleporting" + "\r\n" );
		    out.write( "GuiTeleport.LabelPosX=Pos X" + "\r\n" );
			out.write( "GuiTeleport.LabelPosY=Pos Y" + "\r\n" );
			out.write( "GuiTeleport.LabelPosZ=Pos Z" + "\r\n" );
			out.write( "GuiTeleport.ButtonTeleport=Teleport" + "\r\n" );
			out.write( "GuiMoreOptions.LabelTitle=More Options" + "\r\n" );
			out.write( "GuiMoreOptions.ButtonDisableFOV=Disable FOV" + "\r\n" );
			out.write( "GuiMoreOptions.ButtonInstantDrops=Instant drops" + "\r\n" );
			out.write( "GuiMoreOptions.ButtonInstantExperience=Instant EXP" + "\r\n");
			out.write( "GuiMoreOptions.ButtonGuiPausingGame=Gui pausing game" + "\r\n");
			out.write( "GuiMoreOptions.ButtonFlying=Flying" + "\r\n");
			out.write( "GuiMoreOptions.ButtonInvulnerable=Invulnerable"  + "\r\n" );
			out.write( "GuiMoreOptions.ButtonKillersight=Killer sight" + "\r\n" );
			out.write( "GuiMoreOptions.ButtonCreative=Creative mode" + "\r\n" );
			out.write( "GuiDeathScreen.ButtonContinue=Continue" + "\r\n");
			out.write( "GuiDeathScreen.LabelCoordinates=Coordinates = %coord%" + "\r\n");
			out.write("GuiMain.Player=Player" + "\r\n");
			out.write("GuiMain.LabelActiveCheats=Active Cheats" + "\r\n");
			out.write("GuiMain.World=World" + "\r\n");
			out.write("GuiMain.Console=Console" + "\r\n");
			out.write("GuiMain.Keybinds=Keybinds" + "\r\n");
			out.write("GuiMain.Radar=Radar" + "\r\n");
			out.write("GuiMain.Minimap=Minimap" + "\r\n");
			out.write("GuiMain.LabelPlayerInfo=PlayerInfo" + "\r\n");
			
			
			out.close( );
			
		} catch ( Exception e ) { // Catch exception if any
			cb.LogAgent.logInfo( "Error writing the Translation File!: " + e.getMessage( ) );
			
		}
		Reader( );
	} else {
		Reader( );
	}
}

     public void Reader( ) {
	// Initialize variables
	int binding;
	String str = "";
	String name;
	
	InputStream fis = null;
	BufferedReader br;
	String line;
	
	// Try to create FIS
	try {
		fis = new FileInputStream( translationFile );
	} catch ( FileNotFoundException e ) {
		// TODO Auto-generated catch block
		e.printStackTrace( );
	}
	// Create BR
	br = new BufferedReader( new InputStreamReader( fis, Charset.forName( "UTF-8" ) ) );
	

}
}
