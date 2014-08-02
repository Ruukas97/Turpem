package dk.turpem.util;

import dk.turpem.Turpem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class TranslationManager {
	public static String translateToLocal(String forTranslation){
		return StatCollector.translateToLocal(forTranslation);
	}
	
	public static String translateActionMessage(String event, EntityPlayer player){
		return translateActionMessage(event, EnumChatFormatting.GRAY, player);
	}
	
	public static String translateActionMessage(String event, EnumChatFormatting defaultcolor, EntityPlayer player){
		ItemStack itemstack = player.inventory.mainInventory[player.inventory.currentItem];
		String translated = defaultcolor + translateToLocal(Turpem.MODID.toLowerCase() + ".action." + event + ".message");
		if (translated.contains("<") && translated.contains(">")) {
			if (translated.contains("<item.")) {
				if (translated.contains("<item.name>"))
					translated = translated.replaceAll("<item.name>", EnumChatFormatting.DARK_PURPLE + itemstack.getDisplayName() + defaultcolor);
				//if (translated.contains("<item.damage>"))
				//	translated = translated.replaceAll("<item.damage>", EnumChatFormatting.DARK_PURPLE + itemstack.getDisplayName() + defaultcolor);
			}
			if(translated.contains("<player.")){
				if (translated.contains("<player.name>"))
					translated = translated.replaceAll("<player.name>", EnumChatFormatting.BLUE + player.getDisplayName() + defaultcolor);
				if (translated.contains("<player.posx>"))
					translated = translated.replaceAll("<player.posx>", EnumChatFormatting.YELLOW + "" + (int)player.posX + defaultcolor);
				if (translated.contains("<player.posy>"))
					translated = translated.replaceAll("<player.posy>", EnumChatFormatting.YELLOW + "" + (int)player.posY + defaultcolor);
				if (translated.contains("<player.posz>"))
					translated = translated.replaceAll("<player.posz>", EnumChatFormatting.YELLOW + "" + (int)player.posZ + defaultcolor);
				if(translated.contains("<player.xp.")){
					if(translated.contains("<player.xp.level>"))
						translated = translated.replaceAll("<player.xp.level>", EnumChatFormatting.DARK_GREEN + "" + player.experienceLevel + defaultcolor); 	
				}
				if(translated.contains("<translate:")){
					if(translated.contains("<translate:item.")){}
					//	translated = translated.replaceAll("<translate.", EnumChatFormatting.DARK_PURPLE + Item.getItemById(p_150899_0_) + defaultcolor); 	
				}
			}
		}			
		return translated;
	}
}
