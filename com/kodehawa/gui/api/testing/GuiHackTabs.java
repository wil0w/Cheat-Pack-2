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
