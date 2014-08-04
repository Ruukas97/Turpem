package dk.turpem.client.gui;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class ButtonNameChange
  extends GuiButton
{
  private EntityPlayer player;
  
  public ButtonNameChange(int par1, int par2, int par3, int x, int y, String par4Str)
  {
    super(par1, par2, par3, par4Str);
    this.player = Minecraft.getMinecraft().thePlayer;
    this.width = x;
    this.height = y;
    this.field_146123_n = true;
    this.enabled = true;
  }
  
  public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
  {
    if ((par2 >= this.xPosition) && (par2 <= this.xPosition + this.width) && 
      (par3 >= this.yPosition) && (par3 <= this.yPosition + this.height))
    {
      if (this.displayString.equals("Change Name")) {
    	  Minecraft.getMinecraft().displayGuiScreen(new GuiNameChange(Minecraft.getMinecraft().thePlayer));
      } else if (this.displayString.equals("Close")) {
        Minecraft.getMinecraft().thePlayer.closeScreen();
      }
      return false;
    }
    return super.mousePressed(par1Minecraft, par2, par3);
  }
}
