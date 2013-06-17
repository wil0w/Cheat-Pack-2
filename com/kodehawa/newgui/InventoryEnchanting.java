
package com.kodehawa.newgui;


import net.minecraft.src.InventoryBasic;

public class InventoryEnchanting extends InventoryBasic
{
    public InventoryEnchanting()
    {
        super("Enchanting", true, 2);
    }

    public int getSlotStackLimit()
    {
        return 1;
    }
}
