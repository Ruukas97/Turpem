package dk.turpem.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dk.turpem.Turpem;

public class RequestNameChange implements IMessage{

	private String name; 

    public RequestNameChange() {}

    public RequestNameChange(String name) { 
        this.name = name;
    }

    @Override
    public void toBytes(ByteBuf buf) { 
    	ByteBufUtils.writeUTF8String(buf, name);
    }

    @Override
    public void fromBytes(ByteBuf buf) { 
    	name = ByteBufUtils.readUTF8String(buf); 
    }
    
    public static class Handler implements IMessageHandler<RequestNameChange, IMessage> {        
    	@Override
        public IMessage onMessage(RequestNameChange message, MessageContext ctx) {
            System.out.println(String.format("Received namechange-request : %s from %s", message.name, ctx.getServerHandler().playerEntity.getDisplayName()));
    		ctx.getServerHandler().playerEntity.getEntityData().setString("DisplayName", message.name);
    		ctx.getServerHandler().playerEntity.refreshDisplayName();
    		ctx.getServerHandler().playerEntity.addChatComponentMessage(new ChatComponentText(String.format("The server sucessfully changed your name to %s.", message.name)));
    		Turpem.snw.sendToAll(new SendDisplayName(message.name, ctx.getServerHandler().playerEntity));
            return null;
        }
    }
}