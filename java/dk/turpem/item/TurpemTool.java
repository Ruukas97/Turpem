package dk.turpem.item;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class TurpemTool extends TurpemItem{
	public TurpemTool(String name) {
		super(name);
		setFull3D();
	}
	
	public void breakItemStack(ItemStack itemstack, EntityLivingBase wielder, Item handle, Item blade){
		if (wielder instanceof EntityPlayer && !((EntityPlayer)wielder).capabilities.isCreativeMode){
			EntityPlayer player = (EntityPlayer)wielder;
			player.renderBrokenItemStack(itemstack);
			player.dropItem(blade, 1);
			player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(handle));
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Your " + EnumChatFormatting.DARK_PURPLE + itemstack.getDisplayName() + EnumChatFormatting.GRAY + " just " + EnumChatFormatting.RED + "broke" + EnumChatFormatting.GRAY + "."));
		}
	}
	
	public void damageItemStack(ItemStack itemstack, int chance, int durability, EntityLivingBase wielder, Item handle, Item blade){
		if (wielder instanceof EntityPlayer && !((EntityPlayer)wielder).capabilities.isCreativeMode && !wielder.worldObj.isRemote){
			int randomChance = wielder.worldObj.rand.nextInt(durability);
			System.out.println(randomChance + "/" + durability + " Chance : " + chance);
			if(randomChance >= 0 && randomChance < chance)
				breakItemStack(itemstack, wielder, handle, blade);
			else if(randomChance >= chance && randomChance < (chance * 2)){
				Knife.setCondition(itemstack, -1, true);
			}
		}
	}	
}
