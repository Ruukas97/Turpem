package dk.turpem.item;

import java.util.List;
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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.turpem.Turpem;
import dk.turpem.creativetab.TurpemTabs;

public class TurpemItem extends Item{
	
	public TurpemItem(String name){
		setUnlocalizedName(name);
		setTextureName(Turpem.MODID + ":" + name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(TurpemTabs.tabMaterials);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		list.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal(getUnlocalizedName(itemstack) + ".info"));
	}	
	
	@Override
	public String getUnlocalizedName() {
        return "turpem." + super.getUnlocalizedName();
	}
}
