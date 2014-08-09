package dk.turpem.event;

import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import dk.turpem.Turpem;
import dk.turpem.packet.SendDisplayName;

public class ConnectionManager {
    @SubscribeEvent
    public void clientConnection(PlayerLoggedInEvent event)
    {
    	EntityPlayerMP player = (EntityPlayerMP) event.player;
    	Turpem.snw.sendToAll(new SendDisplayName(player.getDisplayName(), player));
    }
}
