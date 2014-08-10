package dk.turpem.event;

import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerEvents {
	@SubscribeEvent
	public void onNameFormat(PlayerEvent.NameFormat event){
		if(event.entityPlayer.getEntityData().hasKey("DisplayName")) {			
			event.displayname = event.entityPlayer.getEntityData().getString("DisplayName");
		}
		//event.entityPlayer.addPotionEffect(new PotionEffect(32 /*ID of FrozenPotion*/, 10000)); (For testing purposes)
	}
	
	/*@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.isPotionActive(Turpem.frozenPotion)) {
			if(event.entity instanceof EntityPlayer){
				((EntityPlayer)event.entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, event.entityLiving.getActivePotionEffect(Turpem.frozenPotion).getDuration()));
			}
			if(event.entity.isInsideOfMaterial(Material.air) && !event.entity.worldObj.isRemote){
				int x = (event.entity.posX-0.5 - (int) event.entity.posX) < 0.49D ? (int) event.entity.posX-1 : (int) event.entity.posX;
				//int y = (event.entity.posY - (int) event.entity.posY) < 0.49D ? (int) event.entity.posY : (int) event.entity.posY - 1;
				int y = (int) (event.entity.posY+0.5);
				int z = ((event.entity.posZ-0.5) - (int) event.entity.posZ) < 0.49D ? (int) event.entity.posZ-1 : (int) event.entity.posZ;
				//System.out.println("X: " + x + " Y: " + y + " Z: " + z);
				if(event.entity.worldObj.getBlock(x, y-1, z) == Blocks.water){
					event.entity.worldObj.setBlock(x, y-1, z, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x-1, y-1, z) == Blocks.water){
					event.entity.worldObj.setBlock(x-1, y-1, z, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x, y-1, z-1) == Blocks.water){
					event.entity.worldObj.setBlock(x, y-1, z-1, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x-1, y-1, z-1) == Blocks.water){
					event.entity.worldObj.setBlock(x-1, y-1, z-1, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x+1, y-1, z) == Blocks.water){
					event.entity.worldObj.setBlock(x+1, y-1, z, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x, y-1, z+1) == Blocks.water){
					event.entity.worldObj.setBlock(x, y-1, z+1, Blocks.ice);
				}
				if(event.entity.worldObj.getBlock(x+1, y-1, z+1) == Blocks.water){
					event.entity.worldObj.setBlock(x+1, y-1, z+1, Blocks.ice);
				}
			}
		}
	}*/
}