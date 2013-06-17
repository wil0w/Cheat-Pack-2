package com.kodehawa.gui.api.testing;

public class GuiHackTabs {
	
	public static GuiHackTab player, testings;
	
	public static void createTabs( ) {
		// new GuiHackTab(posX, posY, width, title, toptextcolor, textcolor,
		// bgcolor, bgtopcolor, draggable);
		player = new GuiHackTab( 1, 1, 100, "Player", 0xFF00FFFF, 0xFFFFFFFF, 0x78000000, 0xA8000000, 0xFFCCCCCC, true );
		player.setColors( 0xFF00FF00, 0xFFFF0000 );
		player.addItem( "testing", true ); // addItem("text of item", enabled)
		player.addItem( "SomeMoreTesting", true );
		// player.setDraggable(false); //disables the dragging of this tab (use
		// true to enable)
		// player.resetPosition(); // use this to reset this tab's position to
		// the created position
		
		testings = new GuiHackTab( 2 + player.posX + player.width, 1, 120, "Testings", 0xFF0000FF, 0xFFFFFFFF, 0x78000000, 0xA8000000, 0xFFCCCCCC, true );
		testings.setColors( 0xFF00FFFF, 0xFF000000 );
		testings.addItem( "222", true );
		testings.addItem( "eeeee", false );
	}
}
