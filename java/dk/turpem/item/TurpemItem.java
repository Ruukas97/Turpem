package dk.turpem.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
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
