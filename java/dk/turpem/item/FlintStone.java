package dk.turpem.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class FlintStone extends TurpemItem{
	public FlintStone(String name) {
		super(name);		
		setMaxStackSize(16);
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player,	World world, int x, int y, int z, int par7, float par8, float par9,	float par10) {
		int randomInt = world.rand.nextInt(10);
		if(randomInt < 4){
			ItemStack newStone = new ItemStack(AllItems.sharpFlint);
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("turpem.action.flintsplinter.message", EnumChatFormatting.GRAY, EnumChatFormatting.DARK_PURPLE, itemstack.getDisplayName(), EnumChatFormatting.GRAY)));
		}
		else if(randomInt < 6){
			
		}
		else if(randomInt < 9){
			
		}
		else{
			
		}
		super.onItemUse(itemstack, player, world, x, y, z, par7, par8, par9, par10);
		return true;		
	}
}
