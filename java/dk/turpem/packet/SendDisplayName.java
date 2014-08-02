package dk.turpem.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SendDisplayName implements IMessage{

	private String displayName; 
	private int entityID;

    public SendDisplayName() {}

    public SendDisplayName(String displayName, EntityPlayer player) { 
        this.displayName = displayName;
        this.entityID = player.getEntityId();
    }

    @Override
    public void toBytes(ByteBuf buf) { 
    	ByteBufUtils.writeUTF8String(buf, displayName);
    	buf.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buf) { 
    	displayName = ByteBufUtils.readUTF8String(buf);     	
    	entityID = buf.readInt();     	
    }
    
    public static class Handler implements IMessageHandler<SendDisplayName, IMessage> {        
    	@Override
        public IMessage onMessage(SendDisplayName message, MessageContext ctx) {
    		EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.getEntityByID(message.entityID);
    		if(player != null){
        		System.out.println("Received package containing : " + message.displayName + " and " + player.getCommandSenderName());
        		player.getEntityData().setString("DisplayName", message.displayName);
        		player.refreshDisplayName();
    		}
    		return null;
        }
    }
}