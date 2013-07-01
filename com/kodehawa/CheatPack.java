
package com.kodehawa;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Properties;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.Minecraft;
import net.minecraft.src.World;

import org.lwjgl.input.Keyboard;

import com.kodehawa.core.UnexpectedThrowable;

public final class CheatPack
{
    public static final String copyright = "Cheat Pack 2.3 #4 by Binkan Salaryman";
    public static CheatPack coreBase;
    public static CBOriginal sspBase = new CBOriginal();
    public static CBOriginal smpBase = new CBOriginal();
    public static Integer dimensions[];
    public static boolean hasModLoader;
    public static boolean hasZMod;
    public static boolean pausegame;
    public static boolean disablefov;
    public static boolean instantdrop;
    public File file;
    public static boolean instantxp;
    public static boolean flying;
    public static boolean invulnerable;
    public static boolean showgui;
    public static boolean killersight;
    public static boolean creative;
    public static boolean enableSingleplayer = true;
    public static boolean enableMultiplayer;
    public static int prevKey = Keyboard.KEY_DOWN;
    public static int nextKey = Keyboard.KEY_NEXT;
    public static int escapeKey = Keyboard.KEY_ESCAPE;
    public static int setKey = 28;
    public static int unsetKey = 211;
    public final File settings = new File(file, "/CheatPackSettings.dat");
    public final Properties transTable = new Properties();
    public final Properties cfgTable = new Properties();
    

    public CheatPack instance;
    public Minecraft mc;
    
    public static void settings(File par6File){
    	
    }
    
    protected static void checkEnvironment()
    {
        hasModLoader = coreBase.classExists("ModLoader");

        if (hasModLoader)
        {
            System.out.println("Cheat Pack 2: ModLoader Initialization Complete. All mods OK.");
        }}
    
      

    public static void loadDimensions()
    {
        try
        {
            DataInputStream datainputstream = new DataInputStream((com.kodehawa.CheatPack.class).getResourceAsStream("/CP2/dimensions.cfg"));
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(datainputstream));
            ArrayList arraylist = new ArrayList();
            arraylist.add(Integer.valueOf(-1));
            arraylist.add(Integer.valueOf(0));
            arraylist.add(Integer.valueOf(1));

            do
            {
                String s;

                if ((s = bufferedreader.readLine()) == null)
                {
                    break;
                }

                if (!s.startsWith("#"))
                {
                    try
                    {
                        arraylist.add(Integer.valueOf(s));
                    }
                    catch (Exception exception1)
                    {
                        System.out.println("Cheat Pack 2 - Dimension line skipped");
                    }
                }
            }
            while (true);

            datainputstream.close();
            dimensions = (Integer[])arraylist.toArray(new Integer[0]);
        }
        catch (Exception exception)
        {
            coreBase.throwException("Cheat Pack 2 content missing!", exception);
        }
    }

    public static boolean[] unpackByteToBools(byte byte0)
    {
        boolean aflag[] = new boolean[8];

        for (int i = 0; i < 8; i++)
        {
            aflag[i] = (byte0 & 128 >> i) != 0;
        }

        return aflag;
    }

    protected static void loadConfig()
    {
        prevKey = coreBase.cfgInt("key.prev");
        nextKey = coreBase.cfgInt("key.next");
        escapeKey = coreBase.cfgInt("key.escape");
        setKey = coreBase.cfgInt("key.set");
        unsetKey = coreBase.cfgInt("key.unset");
    }

    protected static void loadSettings()
    {
        byte byte0 = 64;
        byte byte1 = 0;

        if (coreBase.settings.exists())
        {
            try
            {
                FileInputStream fileinputstream = new FileInputStream(coreBase.settings);
                byte0 = (byte)fileinputstream.read();
                byte1 = (byte)fileinputstream.read();
                fileinputstream.close();
            }
            catch (Exception exception)
            {
                System.out.println("Cheat Pack 2 - Failed to load settings; Assigning default values...");
            }
        }

        boolean aflag[] = unpackByteToBools(byte0);
        killersight = aflag[0];
        showgui = aflag[1];
        invulnerable = aflag[2];
        flying = aflag[3];
        pausegame = aflag[4];
        disablefov = aflag[5];
        instantdrop = aflag[6];
        instantxp = aflag[7];
        aflag = unpackByteToBools(byte1);
        creative = aflag[0];
    }

    public static byte packBoolsToByte(boolean aflag[])
    {
        if (aflag.length != 8)
        {
            throw new IllegalArgumentException();
        }

        byte byte0 = 0;

        for (int i = 0; i < 8; i++)
        {
            byte0 |= aflag[i] ? ((byte)(128 >> i)) : 0;
        }

        return byte0;
    }

    public static void saveSettings()
    {
        byte byte0 = packBoolsToByte(new boolean[]
                {
                    killersight, showgui, invulnerable, flying, pausegame, disablefov, instantdrop, instantxp
                });
        byte byte1 = packBoolsToByte(new boolean[]
                {
                    creative, false, false, false, false, false, false, false
                });

        try
        {
            FileOutputStream fileoutputstream = new FileOutputStream(coreBase.settings);
            fileoutputstream.write(byte0);
            fileoutputstream.write(byte1);
            fileoutputstream.close();
        }
        catch (Exception exception)
        {
            coreBase.throwException("Failed to save Settings!", exception);
        }
    }

    public static Object getPrivateValue(Class class1, Object obj, String s) throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            coreBase.throwException("Failed to get a private value!", illegalaccessexception);
        }

        return null;
    }

    public static Object getPrivateValue(Class class1, Object obj, int i) throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            coreBase.throwException("Failed to get a private value!", illegalaccessexception);
        }

        return null;
    }

    public static void setPrivateValue(Class class1, Object obj, int i, Object obj1) throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = (java.lang.reflect.Field.class).getDeclaredField("modifiers");
            Field field1 = class1.getDeclaredFields()[i];
            field1.setAccessible(true);
            field1.set(obj, obj1);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            coreBase.throwException("Failed to set a private value!", illegalaccessexception);
        }
    }

    public static CBOriginal getBase(Minecraft minecraft)
    {
        return minecraft.theWorld.isRemote ? smpBase : sspBase;
        
    }

    public static String translate(String s)
    {
        return coreBase.transTable.getProperty(s, s);
    }

    public static void loopCheats(Minecraft minecraft)
    {
       getBase(minecraft).setInvulnerable(minecraft.thePlayer, invulnerable);
       getBase(minecraft).setFlying(minecraft.thePlayer, flying);

    }
    
    public boolean showModGui(Minecraft minecraft, int i, GuiScreen guiscreen)
    {
       if (i == minecraft.gameSettings.keyBindInventory.keyCode)
       {
    	   
       minecraft.displayGuiScreen(guiscreen);
       return true;
       
       }
    
    if (i == escapeKey)
       {
    	
        minecraft.displayGuiScreen(null);
        return true;
        
       }
    else
       {
    	
        return false;
        
       }
    }

    

    public static boolean escapeGui(Minecraft minecraft, int i, GuiScreen guiscreen)
    {
        if (i == minecraft.gameSettings.keyBindInventory.keyCode)
        {
            minecraft.displayGuiScreen(guiscreen);
            return true;
        }

        if (i == escapeKey)
        {
            minecraft.displayGuiScreen(null);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static int[] ticksToTime(int i)
    {
        int j = (int)((float)i * 0.05F);
        int k = (int)((float)j * 0.01666667F);
        j %= 60;
        return (new int[]
                {
                    k, j
                });
    }

    public static int timeToTicks(int i, int j)
    {
        return 20 * (60 * i + j);
    }

    public static boolean isEnabled(boolean flag)
    {
        return enableSingleplayer && !flag || enableMultiplayer && flag;
    }

    protected CheatPack()
    {
    }

    public int cfgInt(String s)
    {
        try
        {
            return Integer.parseInt(cfgTable.getProperty(s));
        }
        catch (Exception exception)
        {
            //throwException((new StringBuilder()).append("Failed to load config integer: ").append(s).toString(), exception);
        }

        return 0;
    }

    public boolean cfgBool(String s)
    {
        try
        {
            return Boolean.parseBoolean(cfgTable.getProperty(s));
        }
        catch (Exception exception)
        {
            throwException((new StringBuilder()).append("Failed to load config boolean: ").append(s).toString(), exception);
        }

        return false;
    }

    public String cfgString(String s)
    {
        String s1 = cfgTable.getProperty(s);

        if (s1 != null)
        {
            return s1;
        }
        else
        {
            throwException((new StringBuilder()).append("Failed to load config string: ").append(s).toString(), new NullPointerException());
            return null;
        }
    }

    public boolean classExists(String s)
    {
        try
        {
            Class.forName(s, false, ClassLoader.getSystemClassLoader());
            return true;
        }
        catch (Throwable throwable)
        {
            return false;
        }
    }

    public Minecraft getMinecraft()
    {
        Minecraft minecraft = null;

        try
        {
            ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
            int i = threadgroup.activeCount();
            Thread athread[] = new Thread[i];
            threadgroup.enumerate(athread);
            int j = 0;

            do
            {
                if (j >= athread.length)
                {
                    break;
                }

                if (athread[j].getName().equals("Minecraft main thread"))
                {
                    minecraft = (Minecraft)getPrivateValue(java.lang.Thread.class, athread[j], "target");
                    break;
                }

                j++;
            }
            while (true);
        }
        catch (SecurityException securityexception)
        {
            throw new RuntimeException(securityexception);
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            throw new RuntimeException(nosuchfieldexception);
        }

        return minecraft;
    }
      
    
    public void throwException(String s, Throwable throwable)
    {
        //System.out.println((new StringBuilder()).append("Cheat Pack 2 - ").append(s).append(" -//- ").append(throwable.toString()).toString());
        Minecraft minecraft = getMinecraft();

        if (minecraft == null)
        {
            throw new RuntimeException(throwable);
        }
        else
        {
            minecraft.displayUnexpectedThrowable(new UnexpectedThrowable((new StringBuilder()).append("Cheat Pack - ").append(s).toString(), throwable));
            return;
        }
    }
    

    public World getWorld()
    {
        return getMinecraft().theWorld;
    }

    public EntityClientPlayerMP getPlayer()
    {
        return getMinecraft().thePlayer;
    }

    
 
   public static void init()
    {
        coreBase = new CheatPack();
        //No more needed
       // System.out.println("<<< Cheat Pack 2 - Init System >>>");
       // System.out.println("<<< Cheat Pack 2 GUI Init System >>>");
        checkEnvironment();
        loadDimensions();
        loadSettings();
       

        try
        {
            coreBase.transTable.load((net.minecraft.src.Gui.class).getResourceAsStream("/CP2/gui.lang"));
            coreBase.transTable.load((net.minecraft.src.Gui.class).getResourceAsStream("/CP2/dimensions.lang"));
            coreBase.cfgTable.load((net.minecraft.src.Gui.class).getResourceAsStream("/CP2/global.cfg"));
            coreBase.cfgTable.load((net.minecraft.src.Gui.class).getResourceAsStream("/CP2/keys.cfg"));
        }
        catch (Exception exception)
        {
            //coreBase.throwException("Cheat Pack 2 content missing!", exception);
        }

        loadConfig();
    }
    
 
}