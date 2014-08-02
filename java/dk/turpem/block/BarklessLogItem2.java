package dk.turpem.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BarklessLogItem2 extends ItemBlock{

	public BarklessLogItem2(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return super.getUnlocalizedName(itemstack) + "_" + BarklessLog2.types[itemstack.getItemDamage()];
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
}
