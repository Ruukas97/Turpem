package dk.turpem.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

public class RenderPlayerTurpem extends RenderPlayer {
	
	@Override
	protected void passSpecialRender(EntityLivingBase entity, double x, double y, double z) {
		if(MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Pre(entity, this, x, y, z))) {
			return;
		}
		if(func_110813_b(entity)) {
			float f = 1.6F;
			float scale = 0.016666668F * f;
			double distance = entity.getDistanceSqToEntity(renderManager.livingPlayer);
			float range = entity.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
			if(distance < (double) (range * range)) {
				String s = entity.func_145748_c_().getFormattedText();
				if(entity.isSneaking()) {
					FontRenderer fontRenderer = getFontRendererFromRenderManager();
					GL11.glPushMatrix();
					GL11.glTranslatef((float) x + 0.0F, (float) y + entity.height + 0.5F, (float) z);
					GL11.glNormal3f(0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
					GL11.glScalef(-scale, -scale, scale);
					GL11.glDisable(GL11.GL_LIGHTING);
					GL11.glTranslatef(0.0F, 0.25F / scale, 0.0F);
					GL11.glDepthMask(false);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					Tessellator tessellator = Tessellator.instance;
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					tessellator.startDrawingQuads();
					int i = fontRenderer.getStringWidth(s) / 2;
					tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
					tessellator.addVertex((double) (-i - 1), -1.0D, 0.0D);
					tessellator.addVertex((double) (-i - 1), 8.0D, 0.0D);
					tessellator.addVertex((double) (i + 1), 8.0D, 0.0D);
					tessellator.addVertex((double) (i + 1), -1.0D, 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glDepthMask(true);
					fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, 0, 553648127);
					GL11.glEnable(GL11.GL_LIGHTING);
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glPopMatrix();
				} else {
					func_96449_a(entity, x, y, z, s, scale, distance);
				}
			}
		}
		MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Post(entity, this, x, y, z));
	}

	@Override
	protected boolean func_110813_b(EntityLivingBase entity) {
		return Minecraft.isGuiEnabled()/* && entity != renderManager.livingPlayer*/ && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer) && entity.riddenByEntity == null;
	}

	@Override
	protected void func_96449_a(EntityLivingBase entity, double x, double y, double z, String label, float par9, double par10) {
		func_96449_a((AbstractClientPlayer) entity, x, y, z, label, par9, par10);
	}

	@Override
	protected void func_96449_a(AbstractClientPlayer player, double x, double y, double z, String label, float par9, double par10) {
		if(par10 < 100.0D) {
			Scoreboard scoreboard = player.getWorldScoreboard();
			ScoreObjective scoreobjective = scoreboard.func_96539_a(2);
			if(scoreobjective != null) {
				Score score = scoreboard.func_96529_a(player.getCommandSenderName(), scoreobjective);
				if(player.isPlayerSleeping()) {
					func_147906_a(player, score.getScorePoints() + " " + scoreobjective.getDisplayName(), x, y - 1.5D, z, 64);
				} else {
					func_147906_a(player, score.getScorePoints() + " " + scoreobjective.getDisplayName(), x, y, z, 64);
				}
				y += (double) ((float) getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par9);
			}
		}
		if(player.isPlayerSleeping()) {
			func_147906_a(player, label, x, y - 1.5D, z, 64);
		} else {
			func_147906_a(player, label, x, y, z, 64);
		}
	}

	@Override
	protected void func_147906_a(Entity entity, String label, double x, double y, double z, int maxDistance) {
		double distance = entity.getDistanceSqToEntity(renderManager.livingPlayer);
		if(distance <= (double) (maxDistance * maxDistance)) {
			FontRenderer fontRenderer = getFontRendererFromRenderManager();
			float f = 1.6F;
			float f1 = 0.016666668F * f;
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.0F, (float) y + entity.height + 0.5F, (float) z);
			GL11.glNormal3f(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(-f1, -f1, f1);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDepthMask(false);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			byte b0 = -1;

			/*
			Tessellator tessellator = Tessellator.instance;
			if(label.equals("deadmau5")) {
				b0 = -10;
			}
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			tessellator.startDrawingQuads();
			int j = fontRenderer.getStringWidth(label) / 2;
			tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
			tessellator.addVertex((double) (-j - 1), (double) (-1 + b0), 0.0D);
			tessellator.addVertex((double) (-j - 1), (double) (8 + b0), 0.0D);
			tessellator.addVertex((double) (j + 1), (double) (8 + b0), 0.0D);
			tessellator.addVertex((double) (j + 1), (double) (-1 + b0), 0.0D);
			
			tessellator.addVertex((double)(-j - 1), -1.0D, 0.0D);
			tessellator.addVertex((double)(-j - 1), 8.0D, 0.0D);
			tessellator.addVertex((double)(j + 1), 8.0D, 0.0D);
			tessellator.addVertex((double)(j + 1), -1.0D, 0.0D);	
			
			tessellator.draw();
			*/
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			fontRenderer.drawString(label, -fontRenderer.getStringWidth(label) / 2, b0, 553648127);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			fontRenderer.drawString(label, -fontRenderer.getStringWidth(label) / 2, b0, -1);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
		}
	}

	@Override
	public FontRenderer getFontRendererFromRenderManager() {
		return renderManager.getFontRenderer();
	}

	@Override
	protected void bindEntityTexture(Entity par1Entity) {
		updatePlayerLook((EntityPlayer) par1Entity);
	}

	public void updatePlayerLook(EntityPlayer player) {
		int glTextureID;
		if(!player.getEntityData().hasKey("glTextureID")){
			player.getEntityData().setInteger("glTexureID", GeneratePlayerSkin.generatePlayerSkin(player));		
			//System.out.println("Test");
		}
		glTextureID = player.getEntityData().getInteger("glTextureID");
			
		//System.out.println("1: " + glTextureID);
		switch (glTextureID) {
		case -1:
			super.bindEntityTexture(player);
			break;
		default:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, glTextureID);
			//System.out.println("2: " + glTextureID);
			break;
		}
	}

	@Override
	public void renderFirstPersonArm(EntityPlayer player) {
		super.renderFirstPersonArm(player);
	}
}
