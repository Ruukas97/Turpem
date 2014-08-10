package dk.turpem.event;

import net.minecraftforge.event.ServerChatEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dk.turpem.chat.ChatHelper;

public class ServerEvents 
{
	@SubscribeEvent
	public void onServerChatEvent(ServerChatEvent event)
	{
		event.setCanceled(true);
		System.out.println(event.player.getDisplayName() + " wrote : " + event.message);
		ChatHelper.sendChatFromText(event.player, event.message);
	}
}