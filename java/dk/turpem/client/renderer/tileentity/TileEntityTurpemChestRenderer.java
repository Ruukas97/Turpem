package dk.turpem.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
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
import dk.turpem.block.AllBlocks;
import dk.turpem.block.TurpemChest;
import dk.turpem.client.model.ModelSafe;
import dk.turpem.tileentity.TileEntitySafe;
import dk.turpem.tileentity.TileEntityTurpemChest;

@SideOnly(Side.CLIENT)
public class TileEntityTurpemChestRenderer extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
	public static final int renderId = RenderingRegistry.getNextAvailableRenderId();
    private static final ResourceLocation oiledDouble = new ResourceLocation("textures/entity/chest/normal_double.png");
    private static final ResourceLocation acaciaDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/acacia_double.png");
    private static final ResourceLocation birchDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/birch_double.png");
    private static final ResourceLocation darkoakDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/darkoak_double.png");
    private static final ResourceLocation jungleDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/jungle_double.png");
    private static final ResourceLocation oakDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/oak_double.png");
    private static final ResourceLocation spruceDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/spruce_double.png");
    private static final ResourceLocation ironDouble = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/iron_double.png");
    private static final ResourceLocation oiled = new ResourceLocation("textures/entity/chest/normal.png");
    private static final ResourceLocation acacia = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/acacia.png");
    private static final ResourceLocation birch = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/birch.png");
    private static final ResourceLocation darkoak = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/darkoak.png");
    private static final ResourceLocation jungle = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/jungle.png");
    private static final ResourceLocation oak = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/oak.png");
    private static final ResourceLocation spruce = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/spruce.png");
    private static final ResourceLocation iron = new ResourceLocation(Turpem.MODID + ":textures/entity/chest/iron.png");
    private ModelChest chestModel = new ModelChest();
    private ModelChest largeChestModel = new ModelLargeChest();    
	private TileEntityTurpemChest tileentity = new TileEntityTurpemChest();

    public void renderTileEntityAt(TileEntityTurpemChest tileentity, double p_147502_2_, double p_147502_4_, double p_147502_6_, float p_147502_8_)
    {
        int i;

        if (!tileentity.hasWorldObj())
        {
            i = 0;
        }
        else
        {
            Block block = tileentity.getBlockType();
            i = tileentity.getBlockMetadata();

            if (block instanceof TurpemChest && i == 0)
            {
                try
                {
                ((TurpemChest)block).func_149954_e(tileentity.getWorldObj(), tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
                }
                catch (ClassCastException e)
                {
                    FMLLog.severe("Attempted to render a chest at %d,  %d, %d that was not a chest", tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
                }
                i = tileentity.getBlockMetadata();
            }

            tileentity.checkForAdjacentChests();
        }

        if(tileentity.getIsInInventory()){
        	System.out.println("Got this far");
        	System.out.println(tileentity.getBlockType().getUnlocalizedName());
        	if(tileentity.getBlockType() == AllBlocks.oiledChest) {
				bindTexture(oiled);
			} else if(tileentity.getBlockType() == AllBlocks.acaciaChest) {
				bindTexture(acacia);
			} else if(tileentity.getBlockType() == AllBlocks.birchChest) {
				bindTexture(birch);
			} else if(tileentity.getBlockType() == AllBlocks.darkoakChest) {
				bindTexture(darkoak);
			} else if(tileentity.getBlockType() == AllBlocks.jungleChest) {
				bindTexture(jungle);
			} else if(tileentity.getBlockType() == AllBlocks.oakChest) {
				bindTexture(oak);
			} else if(tileentity.getBlockType() == AllBlocks.spruceChest) {
				bindTexture(spruce);
			} else if(tileentity.getBlockType() == AllBlocks.ironChest) {
				bindTexture(iron);
			}
        }
        else if (tileentity.adjacentChestZNeg == null && tileentity.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (tileentity.adjacentChestXPos == null && tileentity.adjacentChestZPos == null)
            {
                modelchest = this.chestModel;

                switch (tileentity.func_145980_j()) {
				case 1:
					this.bindTexture(oiled);
					break;
				case 0:
					this.bindTexture(acacia);
					break;
				case 2:
					this.bindTexture(birch);
					break;
				case 3:
					this.bindTexture(darkoak);
					break;
				case 4:
					this.bindTexture(jungle);
					break;
				case 5:
					this.bindTexture(oak);
					break;
				case 6:
					this.bindTexture(spruce);
					break;
				case 7:
					this.bindTexture(iron);
					break;
				}
            }
            else
            {
                modelchest = this.largeChestModel;

                if(tileentity.func_145980_j() == 0){
                	this.bindTexture(oiledDouble);
                }else if(tileentity.func_145980_j() == 1){
                	this.bindTexture(acaciaDouble);
                }else if(tileentity.func_145980_j() == 2){
                	this.bindTexture(birchDouble);
                }else if(tileentity.func_145980_j() == 3){
                	this.bindTexture(darkoakDouble);
                }else if(tileentity.func_145980_j() == 4){
                	this.bindTexture(jungleDouble);
                }else if(tileentity.func_145980_j() == 5){
                	this.bindTexture(oakDouble);
                }else if(tileentity.func_145980_j() == 6){
                	this.bindTexture(spruceDouble);
                }else if(tileentity.func_145980_j() == 7){
                	this.bindTexture(ironDouble);
                }
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float)p_147502_2_, (float)p_147502_4_ + 1.0F, (float)p_147502_6_ + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (i == 2)
            {
                short1 = 180;
            }

            if (i == 3)
            {
                short1 = 0;
            }

            if (i == 4)
            {
                short1 = 90;
            }

            if (i == 5)
            {
                short1 = -90;
            }

            if (i == 2 && tileentity.adjacentChestXPos != null)
            {
                GL11.glTranslatef(1.0F, 0.0F, 0.0F);
            }

            if (i == 5 && tileentity.adjacentChestZPos != null)
            {
                GL11.glTranslatef(0.0F, 0.0F, -1.0F);
            }

            GL11.glRotatef((float)short1, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float f1 = tileentity.prevLidAngle + (tileentity.lidAngle - tileentity.prevLidAngle) * p_147502_8_;
            float f2;

            if (tileentity.adjacentChestZNeg != null)
            {
                f2 = tileentity.adjacentChestZNeg.prevLidAngle + (tileentity.adjacentChestZNeg.lidAngle - tileentity.adjacentChestZNeg.prevLidAngle) * p_147502_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (tileentity.adjacentChestXNeg != null)
            {
                f2 = tileentity.adjacentChestXNeg.prevLidAngle + (tileentity.adjacentChestXNeg.lidAngle - tileentity.adjacentChestXNeg.prevLidAngle) * p_147502_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_)
    {
        this.renderTileEntityAt((TileEntityTurpemChest)p_147500_1_, p_147500_2_, p_147500_4_, p_147500_6_, p_147500_8_);
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {	
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
		return renderId;
	}
}
