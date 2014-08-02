package dk.turpem.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dk.turpem.Turpem;

public class RequestDisplayName implements IMessage{

	private int entityID;

    public RequestDisplayName() {}

    public RequestDisplayName(EntityPlayer player) { 
        this.entityID = player.getEntityId();
    }

    @Override
    public void toBytes(ByteBuf buf) { 
    	buf.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buf) { 
    	entityID = buf.readInt();     	
    }
    
    public static class Handler implements IMessageHandler<RequestDisplayName, IMessage> {        
    	@Override
        public IMessage onMessage(RequestDisplayName message, MessageContext ctx) {
    		System.out.println("Sending displayname by request!");
    		EntityPlayer player = (EntityPlayer)ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityID);
    		Turpem.snw.sendTo(new SendDisplayName(player.getDisplayName(), player), ctx.getServerHandler().playerEntity);
    		return null;
        }
    }
}