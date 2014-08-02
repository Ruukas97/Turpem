package dk.turpem.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dk.turpem.Turpem;
import dk.turpem.packet.RequestDisplayName;
import dk.turpem.util.MathHelper;

public class RenderEvents {	
	
	private static final ResourceLocation goldcrown = new ResourceLocation("turpem:textures/titles/goldcrown.png");
	private static final ResourceLocation silvercrown = new ResourceLocation("turpem:textures/titles/silvercrown.png");
	private static final ResourceLocation donatorcrown = new ResourceLocation("turpem:textures/titles/donatorcrown.png");
	
	@SubscribeEvent
	public void renderPlayerNameTag(RenderLivingEvent.Specials.Pre event){
		EntityPlayer client = Minecraft.getMinecraft().thePlayer;
		if(event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			String s = player.getDisplayName();//I'm pretty sure this is what accidentally, but fortunately changes the username

			if(!player.getEntityData().hasKey("DisplayName")) {
				Turpem.snw.sendToServer(new RequestDisplayName(player));
			}
	            	
			if(MathHelper.calculateDistanceEntities3D(client, player) <= event.renderer.NAME_TAG_RANGE){
				/**
				 * Gets the username for rendering on the smaller nameplate
				 */
				String u = player.getCommandSenderName();
				float f = 1F;
				float f1 = 0.016666668F * f;
				FontRenderer fontrenderer = event.renderer.getFontRendererFromRenderManager();
				GL11.glPushMatrix();
				GL11.glTranslatef((float)event.x + 0.0F, (float)event.y + player.height + 0.3F, (float)event.z);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				float playerViewY = client.prevRotationYaw + (client.rotationYaw - client.prevRotationYaw);
				float playerViewX = client.prevRotationPitch + (client.rotationPitch - client.prevRotationPitch);
				GL11.glRotatef(-playerViewY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(playerViewX, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(-f1, -f1, f1);
				GL11.glDisable(GL11.GL_LIGHTING);
				//GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
				GL11.glDepthMask(false);
				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				Tessellator tessellator = Tessellator.instance;
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				tessellator.startDrawingQuads();
				int i = fontrenderer.getStringWidth(u) / 2;
				tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.4F);
				tessellator.addVertex((double)(-i - 1), -1.0D, 0.0D);
				tessellator.addVertex((double)(-i - 1), 8.0D, 0.0D);
				tessellator.addVertex((double)(i + 1), 8.0D, 0.0D);
				tessellator.addVertex((double)(i + 1), -1.0D, 0.0D);	
				tessellator.draw();
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glDepthMask(true);
				fontrenderer.drawString(u, -fontrenderer.getStringWidth(u) / 2, 0, 553648127);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glPopMatrix();
						
				/* Renders the appropriate crown
				 * Which took me too long, to figure out how to do...
				 */
				if(Turpem.developers.contains(player.getCommandSenderName())  || Turpem.helpful.contains(player.getCommandSenderName()) || Turpem.donators.contains(player.getCommandSenderName())){
					Minecraft.getMinecraft().renderEngine.bindTexture(goldcrown);
					GL11.glPushMatrix();
					GL11.glTranslatef((float)event.x, (float)event.y + player.height + 0.3F, (float)event.z);
					GL11.glNormal3f(0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-playerViewY, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(playerViewX, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(-f1, -f1, f1);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glDepthMask(false);
					GL11.glEnable(GL11.GL_BLEND);
					OpenGlHelper.glBlendFunc(770, 771, 1, 0);
					tessellator.startDrawingQuads();
					if(Turpem.developers.contains(player.getCommandSenderName()))
						FMLClientHandler.instance().getClient().renderEngine.getTexture(goldcrown);
					else if(Turpem.helpful.contains(player.getCommandSenderName()))
						FMLClientHandler.instance().getClient().renderEngine.getTexture(silvercrown);
					else
						FMLClientHandler.instance().getClient().renderEngine.getTexture(donatorcrown);
					tessellator.setColorRGBA_F(1F, 1F, 1F, 0.3F);
					tessellator.addVertexWithUV((double)(-i - 20), -4D, 0.0D, 0, 0);
					tessellator.addVertexWithUV((double)(-i - 20), 12D, 0.0D, 0, 1);
					tessellator.addVertexWithUV((double)(-i - 2), 12D, 0.0D, 1, 1);
					tessellator.addVertexWithUV((double)(-i - 2), -4D, 0.0D, 1, 0);	
					tessellator.draw();
					GL11.glDepthMask(true);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glPopMatrix();
				}
			}			        
		}
	}
	
	public void setArmor(SetArmorModel event){
        Item item = event.stack.getItem();
		if(item == Items.iron_chestplate){
			if (item instanceof ItemArmor)
			{
				ItemArmor itemarmor = (ItemArmor)item;
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(RenderBiped.getArmorResource(event.entityPlayer, event.stack, event.slot, null));
				ModelBiped modelbiped = event.slot == 2 ? event.renderer.modelArmor : event.renderer.modelArmorChestplate;
				modelbiped.bipedHead.showModel = event.slot == 0;
				modelbiped.bipedHeadwear.showModel = event.slot == 0;
				modelbiped.bipedBody.showModel = event.slot == 1 || event.slot == 2;
				modelbiped.bipedRightArm.showModel = event.slot == 1;
				modelbiped.bipedLeftArm.showModel = event.slot == 1;
				modelbiped.bipedRightLeg.showModel = event.slot == 2 || event.slot == 3;
				modelbiped.bipedLeftLeg.showModel = event.slot == 2 || event.slot == 3;
				modelbiped = net.minecraftforge.client.ForgeHooksClient.getArmorModel(event.entityPlayer, event.stack, event.slot, modelbiped);
				event.renderer.setRenderPassModel(modelbiped);
				/*modelbiped.onGround = event.renderer.mainModel.onGround;
				modelbiped.isRiding = event.renderer.mainModel.isRiding;
				modelbiped.isChild = event.renderer.mainModel.isChild;*/

				//Move outside if to allow for more then just CLOTH
				int j = itemarmor.getColor(event.stack);
				if (j != -1)
				{
					float f1 = (float)(j >> 16 & 255) / 255.0F;
					float f2 = (float)(j >> 8 & 255) / 255.0F;
					float f3 = (float)(j & 255) / 255.0F;
					GL11.glColor3f(f1, f2, f3);					
					return;
				}
				
				GL11.glColor3f(1.0F, 1.0F, 1.0F);				
				return;
			}
		}
		
		return;
	}
}