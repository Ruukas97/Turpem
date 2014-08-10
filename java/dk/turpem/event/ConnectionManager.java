package dk.turpem.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import dk.turpem.Turpem;
import dk.turpem.packet.SendDisplayName;

public class ConnectionManager {
    @SubscribeEvent
    public void clientConnection(PlayerLoggedInEvent event)
    {
    	Turpem.snw.sendToAll(new SendDisplayName(event.player.getDisplayName(), event.player));
    }
}
