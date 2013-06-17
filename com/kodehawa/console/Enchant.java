package com.kodehawa.console;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet;
import net.minecraft.src.StringTranslate;

import com.kodehawa.util.ChatColour;

public class Enchant implements BaseCommand {
	String endres = "";
	
	@Override
	public void onRun( String[ ] cmd ) {
		// TODO Auto-generated method stub
		endres = output( cmd );
	}
	
	@Override
	public String getName( ) {
		// TODO Auto-generated method stub
		return "enchant";
	}
	
	@Override
	public String showHelp( ) {
		// TODO Auto-generated method stub
		return new String( ChatColour.RED + "Usage: " + ChatColour.AQUA + this.getName( ) + " <enchantment> <level>" );
	}
	
	private void enchantStack( ItemStack i, String ench, int level ) {
		for ( Enchantment e : Enchantment.enchantmentsList ) {
			if ( e == null ) {
				continue;
			}
			
			String en = e.getName( );
			
			if ( en != null ) {
				String ename = StringTranslate.getInstance( ).translateKey( en );
				if ( ename.replaceAll( " ", "" ).equalsIgnoreCase( ench ) ) {
					i.addEnchantment( e, level );
				}
			}
		}
		
		this.writeStack( i );
	}
	
	private void writeStack( ItemStack i ) {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream( );
		DataOutputStream datastream = new DataOutputStream( bytestream );
		
		try {
			Packet.writeItemStack( i, datastream );
		} catch ( Exception e ) {
			Minecraft.getMinecraft( ).thePlayer.addChatMessage( e.toString( ) );
		}
	}
	
	@Override
	public String output( ) {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output( String[ ] cmd ) {
		try {
			EntityClientPlayerMP thePlayer = Minecraft.getMinecraft( ).thePlayer;
			{
				ItemStack stack = thePlayer.inventory.getCurrentItem( );
				enchantStack( stack, cmd [ 1 ], Integer.parseInt( cmd [ 2 ] ) );
				return "Item enchanted successfully!";
			}
		}
	      catch ( Exception e ) {
			return showHelp( );
		}
	}
}

