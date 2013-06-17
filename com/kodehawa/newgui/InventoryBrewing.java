package com.kodehawa.newgui;

import net.minecraft.src.InventoryBasic;

public class InventoryBrewing extends InventoryBasic
{
    public InventoryBrewing()
    {
        super("Potion", true, 4);
    }

    public int getSlotStackLimit()
    {
        return 1;
    }
}
