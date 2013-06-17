package com.kodehawa.newgui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.src.GuiButton;
import net.minecraft.src.SoundManager;

public class GuiListControl
{
    public String prev;
    public String next;
    public GuiButton buttons[];
    public int index;
    public int width2;
    public int margin;
    public int height;
    public boolean focused;

    public GuiListControl()
    {
        prev = "<<<";
        next = ">>>";
        buttons = new GuiButton[3];
        width2 = 37;
        margin = 3;
        height = 20;
        focused = false;
    }

    public GuiListControl(int i)
    {
        prev = "<<<";
        next = ">>>";
        buttons = new GuiButton[3];
        width2 = 37;
        margin = 3;
        height = 20;
        focused = false;
        index = i;
    }

    public GuiListControl(Object aobj[])
    {
        prev = "<<<";
        next = ">>>";
        buttons = new GuiButton[3];
        width2 = 37;
        margin = 3;
        height = 20;
        focused = false;

        if (aobj == null || aobj.length == 0)
        {
            throw new IllegalArgumentException("Array must have content");
        }
        else
        {
            index = aobj.length - 1;
            toNeighboorElement(true, aobj);
            return;
        }
    }

    public List initGui(int i, int j, int k, String s, int l, int i1, int j1)
    {
        buttons[0] = new GuiButton(i, l, i1, width2, height, prev);
        buttons[1] = new GuiButton(j, l + width2 + margin, i1, j1 - 2 * (width2 + margin), height, s);
        buttons[1].enabled = false;
        buttons[2] = new GuiButton(k, (l + j1) - width2, i1, width2, height, next);
        ArrayList arraylist = new ArrayList();

        for (int k1 = 0; k1 < buttons.length; k1++)
        {
            arraylist.add(buttons[k1]);
        }

        return arraylist;
    }

    public void setInfo(String s)
    {
        buttons[1].displayString = s;
    }

    public void actionPerformed(GuiButton guibutton, Object aobj[])
    {
        if (guibutton.id == buttons[0].id)
        {
            toNeighboorElement(false, aobj);
        }

        if (guibutton.id == buttons[2].id)
        {
            toNeighboorElement(true, aobj);
        }
    }

    public void keyTyped(char c, int i, Object aobj[], int j, int k, SoundManager soundmanager)
    {
        if (focused)
        {
            if (i == j)
            {
                toNeighboorElement(false, aobj);
            }

            if (i == k)
            {
                toNeighboorElement(true, aobj);
            }

            soundmanager.playSoundFX("random.click", 1.0F, 1.0F);
        }
    }

    public void a(int i, int j, int k)
    {
        focused = i >= buttons[0].xPosition && i < buttons[2].xPosition + width2 && j >= buttons[0].yPosition && j < buttons[0].yPosition + height;
    }

    public void toNeighboorElement(boolean flag, Object aobj[])
    {
        do
        {
            if (flag)
            {
                if (index == aobj.length - 1)
                {
                    index = 0;
                }
                else
                {
                    index++;
                }
            }
            else if (index == 0)
            {
                index = aobj.length - 1;
            }
            else
            {
                index--;
            }
        }
        while (aobj[index] == null);
    }
}
