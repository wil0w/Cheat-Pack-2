package com.kodehawa.newgui;


import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;

public class GuiCheckBox extends GuiButton
{
    public String onText;
    public String offText;
    private boolean checked;
    private String text;

    public GuiCheckBox(int i, int j, int k, int l, int i1, String s, boolean flag)
    {
        super(i, j, k, l, i1, null);
        onText = "On";
        offText = "Off";
        checked = flag;
        setText(s);
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        boolean flag = super.mousePressed(minecraft, i, j);

        if (flag)
        {
            checked = !checked;
            update();
        }

        return flag;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setText(String s)
    {
        text = s;
        update();
    }

    public String getText()
    {
        return text;
    }

    private void update()
    {
        displayString = (new StringBuilder()).append(text).append(": ").append(checked ? onText : offText).toString();
    }
}
