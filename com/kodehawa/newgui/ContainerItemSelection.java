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


package com.kodehawa.newgui;

import java.util.*;
import net.minecraft.src.*;

class ContainerItemSelection extends Container
{
    public List a;

    public ContainerItemSelection(EntityPlayer entityplayer)
    {
        a = new ArrayList();

        for (int i = 0; i < Block.blocksList.length; i++)
        {
            if (Block.blocksList[i] == null)
            {
                continue;
            }

            for (int k = 0; k < getSubTypes(Block.blocksList[i].blockID); k++)
            {
                a.add(new ItemStack(Block.blocksList[i], 1, k));
            }
        }

        for (int j = 0; j < Item.itemsList.length; j++)
        {
            if (Item.itemsList[j] == null)
            {
                continue;
            }

            for (int l = 0; l < getSubTypes(Item.itemsList[j].itemID); l++)
            {
                a.add(new ItemStack(Item.itemsList[j], 1, l));
            }
        }

        int ai[] =
        {
            16, 32, 64, 8192, 8193, 8257, 8225, 8194, 8258, 8226,
            8195, 8259, 8197, 8229, 8201, 8265, 8233, 8196, 8260, 8228,
            8200, 8264, 8202, 8266, 8204, 8236, 16384, 16385, 16449, 16417,
            16386, 16450, 16418, 16387, 16451, 16389, 16421, 16393, 16457, 16425,
            16388, 16452, 16420, 16392, 16456, 16394, 16458, 16396, 16428
        };

        for (int i1 = 0; i1 < ai.length; i1++)
        {
            a.add(new ItemStack(Item.potion, 1, ai[i1]));
        }

        for (int j1 = 0; j1 < 9; j1++)
        {
            for (int l1 = 0; l1 < 8; l1++)
            {
            	addSlotToContainer(new Slot(GuiItemSelection.g(), l1 + j1 * 8, 8 + l1 * 18, 18 + j1 * 18));
            }
        }

        for (int k1 = 0; k1 < 9; k1++)
        {
        	addSlotToContainer(new Slot(entityplayer.inventory, k1, 8 + k1 * 18, 184));
        }

        Integer integer;

        for (Iterator iterator = EntityList.entityEggs.keySet().iterator(); iterator.hasNext(); a.add(new ItemStack(Item.monsterPlacer.itemID, 1, integer.intValue())))
        {
            integer = (Integer)iterator.next();
        }

        a(0.0F);
    }

    private int getSubTypes(int i)
    {
        if (i < Block.blocksList.length)
        {
            if (i == Block.cloth.blockID)
            {
                return 17;
            }


            if (i == Block.planks.blockID)
            {
                return 3;
            }

            if (i == Block.sapling.blockID)
            {
                return 3;
            }

            if (i == Block.stoneBrick.blockID)
            {
                return 3;
            }

            if (i == Block.web.blockID)
            {
                return 2;
            }

            if (i == Block.leaves.blockID)
            {
                return 3;
            }

            if (i == Block.wood.blockID)
            {
                return 4;
            }
        }
        else if (i == Item.dyePowder.itemID)
        {
            return 16;
        }

        return 1;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    public void a(float f)
    {
        int i = (a.size() / 8 - 8) + 1;
        int j = (int)((double)(f * (float)i) + 0.5D);

        if (j < 0)
        {
            j = 0;
        }

        for (int k = 0; k < 9; k++)
        {
            for (int l = 0; l < 8; l++)
            {
                int i1 = l + (k + j) * 8;

                if (i1 >= 0 && i1 < a.size())
                {
                    GuiItemSelection.g().setInventorySlotContents(l + k * 8, (ItemStack)a.get(i1));
                }
                else
                {
                    GuiItemSelection.g().setInventorySlotContents(l + k * 8, null);
                }
            }
        }
    }
}
