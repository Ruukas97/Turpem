package dk.turpem.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import dk.turpem.Turpem;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null){
			switch(ID){
				case Turpem.guiCharacterCreation:
						return new GuiNameChange(player);
				default:
					return null;
			}
		}
		else return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		switch(ID){
		case Turpem.guiCharacterCreation:
			return new GuiNameChange(player);
		default:
			return null;
		}
	}

}
