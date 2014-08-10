package dk.turpem.chat;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import dk.turpem.util.GrammarHelper;
import dk.turpem.util.MathHelper;

public class ChatHelper {	
	public static void sendChatFromText(EntityPlayer sender, String text){
		if(text.contains(" ")){
			EnumChat chatenum = EnumChat.fromString(text.toLowerCase().substring(0, text.indexOf(" ")));
			if(chatenum != null){
				sendChat(sender, text.substring(chatenum.getCommand().length() + 1), chatenum);
				return;
			}
		}
		if(text.contains("?")){
			sendChat(sender, text, EnumChat.ASK);
			return;
		}
		sendChat(sender, text, EnumChat.TALK);
	}
	
	public static void sendChat(EntityPlayer sender, String text, EnumChat chat){
		List<EntityPlayerMP> players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		int listeners = 0;
		for (EntityPlayerMP receiver : players){
			if(!(receiver == sender) && MathHelper.calculateDistanceEntities3D(sender, receiver) <= chat.getRange()){
				listeners++;
				//I know this format is weird
				receiver.addChatComponentMessage(
						new ChatComponentText(
								GrammarHelper.punctuateChat(
										EnumChatFormatting.BLUE + sender.getDisplayName() + chat.getColor() + " " + chat.getSaying() + " " + EnumChatFormatting.RESET + GrammarHelper.capitalize(text, chat.shouldCAPS()), chat.getColor(), chat.ending(), chat.forceEnding()
										)
								)//.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + sender.getCommandSenderName() + " "))
						);
			}
		}
		if(listeners == 0)
			sender.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED	+ "No one hears you."));
		else{
			sender.addChatComponentMessage(new ChatComponentText(GrammarHelper.punctuateChat(chat.getColor() + "You " + !chat.getSaying().endsWith("s") != null || false ? chat.getSaying().substring(0, chat.getSaying().length()-1) : chat.getCommand() + " " + EnumChatFormatting.RESET + GrammarHelper.capitalize(text, false), chat.getColor(), chat.ending(), chat.forceEnding())));
		}
	}	
}