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

package com.kodehawa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Achievement;
import net.minecraft.src.Enchantment;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FoodStats;
import net.minecraft.src.Item;
import net.minecraft.src.ItemPotion;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.PlayerControllerMP;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.StatFileWriter;

public class CBOriginal {
	
	    public static final int MODE_SURVIVAL = 0;
	    public static final int MODE_CREATIVE = 1;

	    public CBOriginal()
	    {
	    }

	    public void setItemSelectionSlot(ItemStack itemstack, int i)
	    {
	    }

	    public void unlockAchievement(Minecraft minecraft, Achievement achievement)
	    {
	        minecraft.guiAchievement.queueTakenAchievement(achievement);
	        minecraft.statFileWriter.readStat(achievement, 1);
	    }

	    public void lockAchievement(StatFileWriter statfilewriter, Achievement achievement)
	    {
	        try
	        {
	            HashMap hashmap = (HashMap)CheatPack.getPrivateValue(net.minecraft.src.StatFileWriter.class, statfilewriter, 0);
	            HashMap hashmap1 = (HashMap)CheatPack.getPrivateValue(net.minecraft.src.StatFileWriter.class, statfilewriter, 1);
	            hashmap.remove(achievement);
	            hashmap1.remove(achievement);
	            CheatPack.setPrivateValue(net.minecraft.src.StatFileWriter.class, statfilewriter, 0, hashmap);
	            CheatPack.setPrivateValue(net.minecraft.src.StatFileWriter.class, statfilewriter, 1, hashmap1);
	        }
	        catch (Throwable throwable)
	        {
	            CheatPack.coreBase.throwException("Error with locking achievement!", throwable);
	        }
	    }

	    public void setHealth(EntityLiving entityliving, int i)
	    {
	        try
	        {
	            CheatPack.setPrivateValue(net.minecraft.src.EntityLiving.class, entityliving, 23, Integer.valueOf(i));
	        }
	        catch (Throwable throwable)
	        {
	            CheatPack.coreBase.throwException("Error with setting health!", throwable);
	        }
	    }

	    public void setFoodStatValues(EntityPlayer entityplayer, int i, float f, float f1)
	    {
	        try
	        {
	            int j = CheatPack.hasZMod ? 1 : 0;
	            int k = 3 + j;
	            FoodStats foodstats = (FoodStats)CheatPack.getPrivateValue(net.minecraft.src.EntityPlayer.class, entityplayer, k);
	            foodstats.setFoodLevel(i);
	            foodstats.setFoodSaturationLevel(f);
	            CheatPack.setPrivateValue(net.minecraft.src.FoodStats.class, foodstats, 2, Float.valueOf(f1));
	            CheatPack.setPrivateValue(net.minecraft.src.EntityPlayer.class, entityplayer, k, foodstats);
	        }
	        catch (Throwable throwable)
	        {
	            CheatPack.coreBase.throwException("Error with setting food stats!", throwable);
	        }
	    }

	    public void setExperience(EntityPlayer entityplayer, int i, float f, int j)
	    {
	        entityplayer.experienceLevel = i;
	        entityplayer.experience = f;
	        entityplayer.experienceTotal = (int) (entityplayer.experience = j);
	    }

	    public void setPotionEffect(EntityLiving entityliving, PotionEffect potioneffect)
	    {
	        entityliving.removePotionEffect(potioneffect.getPotionID());
	        entityliving.addPotionEffect(potioneffect);
	    }

	    public void unsetPotionEffect(EntityLiving entityliving, int i)
	    {
	        entityliving.removePotionEffect(i);
	        entityliving.addPotionEffect(new PotionEffect(i, 0, 0));
	    }

	    public void setEnchantment(ItemStack itemstack, Enchantment enchantment, int i)
	    {
	        if (itemstack.itemID > 255 && Item.itemsList[itemstack.itemID].getItemStackLimit() == 1)
	        {
	            itemstack.addEnchantment(enchantment, i);
	        }
	    }

	    public void unsetEnchantment(ItemStack itemstack, int i)
	    {
	        if (itemstack.stackTagCompound == null)
	        {
	            return;
	        }

	        NBTTagList nbttaglist = (NBTTagList)itemstack.stackTagCompound.getTag("ench");

	        if (nbttaglist == null)
	        {
	            return;
	        }

	        try
	        {
	            List list = (List)CheatPack.getPrivateValue(net.minecraft.src.NBTTagList.class, nbttaglist, 0);
	            ArrayList arraylist = new ArrayList();
	            Iterator iterator = list.iterator();

	            do
	            {
	                if (!iterator.hasNext())
	                {
	                    break;
	                }

	                NBTTagCompound nbttagcompound = (NBTTagCompound)iterator.next();

	                if (nbttagcompound.getShort("id") == i)
	                {
	                    arraylist.add(nbttagcompound);
	                }
	            }
	            while (true);

	            list.removeAll(arraylist);

	            if (list.isEmpty())
	            {
	                itemstack.stackTagCompound = new NBTTagCompound();
	            }
	            else
	            {
	                CheatPack.setPrivateValue(net.minecraft.src.NBTTagList.class, nbttaglist, 0, list);
	            }
	        }
	        catch (Throwable throwable)
	        {
	            CheatPack.coreBase.throwException("Error with unsetting enchantments!", throwable);
	        }
	    }

	    public void setPotionBrewing(ItemStack itemstack, PotionEffect potioneffect)
	    {
	        if (itemstack.itemID > 255 && (Item.itemsList[itemstack.itemID] instanceof ItemPotion))
	        {
	            Item item = Item.itemsList[itemstack.itemID];

	            try
	            {
	                HashMap hashmap = (HashMap)CheatPack.getPrivateValue(net.minecraft.src.ItemPotion.class, item, 0);
	                Object obj = (List)hashmap.get(Integer.valueOf(itemstack.getItemDamage()));

	                if (obj == null)
	                {
	                    obj = new ArrayList();
	                }

	                ((List)(obj)).add(potioneffect);
	                hashmap.put(Integer.valueOf(itemstack.getItemDamage()), obj);
	                CheatPack.setPrivateValue(net.minecraft.src.ItemPotion.class, item, 0, hashmap);
	            }
	            catch (Throwable throwable)
	            {
	                CheatPack.coreBase.throwException("Error with setting potion effects!", throwable);
	            }
	        }
	    }

	    public void unsetPotionBrewing(ItemStack itemstack, int i)
	    {
	        if (itemstack.itemID > 255 && (Item.itemsList[itemstack.itemID] instanceof ItemPotion))
	        {
	            Item item = Item.itemsList[itemstack.itemID];
	            Integer integer = Integer.valueOf(itemstack.getItemDamage());

	            try
	            {
	                HashMap hashmap = (HashMap)CheatPack.getPrivateValue(net.minecraft.src.ItemPotion.class, item, 0);
	                List list = (List)hashmap.get(integer);

	                if (list == null)
	                {
	                    return;
	                }

	                ArrayList arraylist = new ArrayList();
	                Iterator iterator = list.iterator();

	                do
	                {
	                    if (!iterator.hasNext())
	                    {
	                        break;
	                    }

	                    PotionEffect potioneffect = (PotionEffect)iterator.next();

	                    if (potioneffect.getPotionID() == i)
	                    {
	                        arraylist.add(potioneffect);
	                    }
	                }
	                while (true);

	                list.removeAll(arraylist);
	                hashmap.put(integer, list);
	                CheatPack.setPrivateValue(net.minecraft.src.ItemPotion.class, item, 0, hashmap);
	            }
	            catch (Throwable throwable)
	            {
	                CheatPack.coreBase.throwException("Error with unsetting potion effects!", throwable);
	            }
	        }
	    }

	    public void revive(EntityLiving entityliving, int i)
	    {
	        entityliving.isDead = false;
	        entityliving.deathTime = 0;
	        entityliving.hurtTime = 0;
	        setHealth(entityliving, i);

	        if (entityliving instanceof EntityPlayer)
	        {
	            ((EntityPlayer)entityliving).yOffset = 1.62F;
	        }
	    }

	    public void setFlying(EntityPlayer entityplayer, boolean flag)
	    {
	        entityplayer.capabilities.allowFlying = flag;

	        if (!flag)
	        {
	            entityplayer.capabilities.isFlying = false;
	        }
	    }

	    public void setInvulnerable(EntityPlayer entityplayer, boolean flag)
	    {
	        entityplayer.capabilities.disableDamage = flag;
	    }

	    public void teleport(Entity entity, double d, double d1, double d2)
	    {
	        entity.setPosition(d, d1, d2);
	    }

	    public void warp(EntityPlayer entityplayer, int i)
	    {
	       CheatPack.coreBase.getMinecraft().usePortal(i);
	    }

	    public void setGamemode(Minecraft minecraft, int i)
	    {
	        if (i == 0)
	        {
	            minecraft.playerController = new PlayerControllerMP(minecraft, null);
	        }

	        if (i == 1)
	        {
	            minecraft.playerController = new PlayerControllerMP(minecraft, null);
	        }
	    }
	}


