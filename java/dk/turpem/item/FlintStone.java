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
		//0-3 (4)
		//Nothing Happens
		if(randomInt < 4){
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocalFormatted("turpem.action.flintsplinter.message", EnumChatFormatting.GRAY, EnumChatFormatting.DARK_PURPLE, itemstack.getDisplayName(), EnumChatFormatting.GRAY)));
		}
		//4-6 (3)
		//Successfully chips into #SharpFlint
		else if(randomInt < 7){
			
		}
		//7-8 (2)
		else if(randomInt < 9){
			
		}
		//9-9 (1)
		else{
			
		}
		return true;	
	}
}
