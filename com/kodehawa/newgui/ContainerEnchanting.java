package com.kodehawa.newgui;

import java.util.List;
import net.minecraft.src.*;

public class ContainerEnchanting extends Container
{
    public InventoryEnchanting invEnch;

    public ContainerEnchanting(IInventory iinventory)
    {
        invEnch = new InventoryEnchanting();

        for (int i = 0; i < 3; i++)
        {
            for (int l = 0; l < 9; l++)
            {
            	addSlotToContainer(new Slot(iinventory, l + i * 9 + 9, 48 + l * 18, 10 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++)
        {
        	addSlotToContainer(new Slot(iinventory, j, 48 + j * 18, 68));
        }

        for (int k = 0; k < 4; k++)
        {
        	addSlotToContainer(new Slot(invEnch, k, 16, 12 + 18 * k));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i < 9)
            {
                if (!mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(null, itemstack1);
            }
            else
            {
                return null;
            }
        }

        return itemstack;
    }
}
