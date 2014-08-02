package dk.turpem.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BarklessLogItem extends ItemBlock{

	public BarklessLogItem(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return super.getUnlocalizedName(itemstack) + "_" + BarklessLog.types[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
}
