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
	
    public File translationFile;
    public CheatPack init;

	public TranslationWritter( ) {
	
	   translationFile = new File( CheatBase.instance.minecraft.mcDataDir, "/CP2/gui.lang" );
	   if ( !translationFile.exists( ) ) {
		try {
			
			
			translationFile.getParentFile( ).mkdirs( );
			translationFile.createNewFile( );
			FileWriter fstream = new FileWriter( translationFile );
			BufferedWriter out = new BufferedWriter( fstream );
			
			System.out.println("[Cheat Pack 2] [INFO] Writting translation file....");
	        	
			out.write( "GuiInventory.LabelCrafting=Crafting" );
		    out.write( "GuiItemSelection.LabelItemSelection=Item selection" );
			out.write( "GuiHealthFood.LabelTitle=Health and food" );
			out.write( "GuiHealthFood.LabelFoodLevel=Food Level" );
			out.write( "GuiHealthFood.LabelSaturation=Saturation" );
			out.write( "GuiHealthFood.LabelExhaustion=Exhaustion" );
			out.write( "GuiHealthFood.ButtonSet=Set" );
			out.write( "GuiEnchanting.LabelTitle=Enchanting" );
			out.write( "GuiExperience.LabelTitle=Experience");
			out.write( "GuiExperience.LabelLevel=Level");
			out.write( "GuiExperience.LabelPercentage=Percentage" );
		    out.write( "GuiExperience.LabelScore=Score" );
			out.write( "GuiExperience.ButtonSet=Set" );
			out.write( "GuiPotionEffects.LabelTitle=Potion Effects" );
			out.write( "GuiPotionEffects.LabelLevel=Level" );
			out.write( "GuiPotionEffects.LabelMinutes=Minutes" );
			out.write( "GuiPotionEffects.LabelSeconds=Seconds" );
			out.write( "GuiPotionEffects.ButtonSet=Set" );
			out.write( "GuiPotionEffects.ButtonUnset=Unset");
			out.write( "GuiBrewing.LabelTitle=Brewing");
            out.write( "GuiBrewing.LabelLevel=Level" );
			out.write( "GuiBrewing.LabelMinutes=Minutes");
			out.write( "GuiBrewing.ButtonSet=Set");
			out.write( "GuiTeleport.LabelTitle=Teleporting" );
		    out.write( "GuiTeleport.LabelPosX=Pos X" );
			out.write( "GuiTeleport.LabelPosY=Pos Y" );
			out.write( "GuiTeleport.LabelPosZ=Pos Z" );
			out.write( "GuiTeleport.ButtonTeleport=Teleport" );
			out.write( "GuiMoreOptions.LabelTitle=More Options" );
			out.write( "GuiMoreOptions.ButtonDisableFOV=Disable FOV" );
			out.write( "GuiMoreOptions.ButtonInstantDrops=Instant drops" );
			out.write( "GuiMoreOptions.ButtonInstantExperience=Instant EXP");
			out.write( "GuiMoreOptions.ButtonGuiPausingGame=Gui pausing game");
			out.write( "GuiMoreOptions.ButtonFlying=Flying" );
			out.write( "GuiMoreOptions.ButtonInvulnerable=Invulnerable" );
			out.write( "GuiMoreOptions.ButtonKillersight=Killer sight" );
			out.write( "GuiMoreOptions.ButtonCreative=Creative mode" );
			out.write( "GuiDeathScreen.ButtonContinue=Continue");
			out.write( "GuiDeathScreen.LabelCoordinates=Coordinates = %coord%");
			
			
			out.close( );
			
		} catch ( Exception e ) { // Catch exception if any
			System.err.println( "[Cheat Pack 2.3] Error writing the Translation File!: " + e.getMessage( ) );
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
