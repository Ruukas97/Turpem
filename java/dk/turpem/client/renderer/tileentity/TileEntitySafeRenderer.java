package dk.turpem.client.renderer.tileentity;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.Turpem;
import dk.turpem.block.Safe;
import dk.turpem.block.TurpemChest;
import dk.turpem.client.model.ModelSafe;
import dk.turpem.tileentity.TileEntitySafe;
import dk.turpem.tileentity.TileEntityTurpemChest;

@SideOnly(Side.CLIENT)
public class TileEntitySafeRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    private static final ResourceLocation ironSafe = new ResourceLocation(Turpem.MODID + ":textures/entity/safe/iron.png");

    private ModelSafe safeModel = new ModelSafe();

	public final static int renderId = RenderingRegistry.getNextAvailableRenderId();
	
	TileEntitySafe tileentity = new TileEntitySafe();

    public TileEntitySafeRenderer()
    {
    }

    public void renderTileEntityAt(TileEntitySafe tileentity, double p_147502_2_, double p_147502_4_, double p_147502_6_, float p_147502_8_)
    {
        int i;

        if (!tileentity.hasWorldObj())
        {
            i = 0;
        }

            ModelSafe modelsafe;
            modelsafe = this.safeModel;

            this.bindTexture(ironSafe);

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)p_147502_2_, (float)p_147502_4_ + 1.0F, (float)p_147502_6_ + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.25F, 0.5F);
            short short1 = 0;

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = tileentity.prevLidAngle + (tileentity.lidAngle - tileentity.prevLidAngle) * p_147502_8_;
            float f2;

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelsafe.chestDoor.rotateAngleY = (f1 * (float)Math.PI / 2.0F);
            modelsafe.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }


    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((TileEntitySafe)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {	 
		//GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        //GL11.glScalef(1.2F, 1.2F, 1.2F);
		TileEntityRendererDispatcher.instance.renderTileEntityAt(tileentity, 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return this.renderId ;
	}
}