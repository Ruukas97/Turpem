package dk.turpem.event;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import dk.turpem.client.gui.ButtonNameChange;

public class ClientTickHandler
{
	private static boolean addedInventoryButton = false;
	private static boolean addedMainMenuButton = false;
  
	@SubscribeEvent
	public void tickEnd(TickEvent.RenderTickEvent event)
	{
		if (Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null)
		{
			if (Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative)
			{
				if (!this.addedInventoryButton)
				{
					this.addedInventoryButton = true;
					GuiScreen gui = Minecraft.getMinecraft().currentScreen;
					List currentButtons = null;
					for (Field f : GuiScreen.class.getDeclaredFields())
					{
						f.setAccessible(true);
						try
						{
							if ((f.getName().equals("buttonList")) || (f.getName().equals("field_146292_n")))
							{
								Field modfield = Field.class.getDeclaredField("modifiers");
                
								modfield.setAccessible(true);
								modfield.setInt(f, f.getModifiers() & 0xFFFFFFFB);
                
								currentButtons = (List)f.get(gui);
								int offsetx = 80;
								int offsety = 93;
								int posX = Minecraft.getMinecraft().currentScreen.width / 2;
								int posY = Minecraft.getMinecraft().currentScreen.height / 2;
								
								currentButtons.add(new ButtonNameChange(currentButtons.size() + 1, posX - offsetx, posY + offsety, 80, 20, "Change Name"));         
								currentButtons.add(new ButtonNameChange(currentButtons.size() + 1, posX - offsetx + 80 + 15, posY + offsety, 80, 20, "Close"));
							}
						}
						catch (Exception e)
						{
							System.err.println("Severe error, please report this to the mod author:");
              
							System.err.println(e);
						}
					}
				}
			}
			else if(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu && !addedMainMenuButton){
				addedMainMenuButton = true;
				GuiScreen gui = Minecraft.getMinecraft().currentScreen;
				List currentButtons = null;
				for (Field f : GuiScreen.class.getDeclaredFields())
				{
					f.setAccessible(true);
					try
					{
						if ((f.getName().equals("buttonList")) || (f.getName().equals("field_146292_n")))
						{
							Field modfield = Field.class.getDeclaredField("modifiers");
            
							modfield.setAccessible(true);
							modfield.setInt(f, f.getModifiers() & 0xFFFFFFFB);
            
							currentButtons = (List)f.get(gui);
							int offsetx = 80;
							int offsety = 93;
							int posX = Minecraft.getMinecraft().currentScreen.width / 2;
							int posY = Minecraft.getMinecraft().currentScreen.height / 2;
							
							currentButtons.add(new ButtonNameChange(currentButtons.size() + 1, posX - offsetx, posY + offsety, 80, 20, "Change Name"));         
						}
					}
					catch (Exception e)
					{
						System.err.println("Severe error, please report this to the mod author:");
          
						System.err.println(e);
					}
				}
			}
			else {
				this.addedInventoryButton = false;
				addedMainMenuButton = true;
			}
		}
		else {
			this.addedInventoryButton = false;
			addedMainMenuButton = true;

		}
	}
}