package dk.turpem.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.Turpem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class LogItem extends TurpemItem {
	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;
	
	private static final String[] names = new String[] {"acacia", "birch", "darkoak", "jungle", "oak", "spruce"};

	public LogItem(String name) {
		super(name);
		hasSubtypes = true;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "_" + names[itemstack.getItemDamage()];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		return icons[dmg];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
	icons = new IIcon[names.length];

		for(int i = 0; i < names.length; i++) {
			icons[i] = icon.registerIcon(Turpem.MODID + ":" + getUnlocalizedName().substring(12) + "_" + names[i]);
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
}
