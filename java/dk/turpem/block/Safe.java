package dk.turpem.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.client.renderer.tileentity.TileEntitySafeRenderer;
import dk.turpem.creativetab.TurpemTabs;
import dk.turpem.tileentity.TileEntitySafe;

public class Safe extends BlockContainer
{
    private final Random random = new Random();

    public Safe(String name, Material material)
    {
        super(material);
        this.setCreativeTab(TurpemTabs.tabDecorations);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        setBlockName(name);
        GameRegistry.registerBlock(this, name);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return TileEntitySafeRenderer.renderId;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        byte b0 = 0;
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        switch (l) {
		case 0:
			b0 = 2;
			break;
		case 1:
			b0 = 5;
			break;
		case 2:
			b0 = 3;
			break;
		case 3:
			b0 = 4;
			break;
		}

        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, b0, 3);


        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntitySafe)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145976_a(p_149689_6_.getDisplayName());
        }
    }

    private boolean func_149952_n(World p_149952_1_, int p_149952_2_, int p_149952_3_, int p_149952_4_)
    {
        return p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_) != this ? false : (p_149952_1_.getBlock(p_149952_2_ - 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_ + 1, p_149952_3_, p_149952_4_) == this ? true : (p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ - 1) == this ? true : p_149952_1_.getBlock(p_149952_2_, p_149952_3_, p_149952_4_ + 1) == this)));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
        TileEntitySafe tileentitySafe = (TileEntitySafe)p_149695_1_.getTileEntity(p_149695_2_, p_149695_3_, p_149695_4_);

        if (tileentitySafe != null)
        {
            tileentitySafe.updateContainingBlockInfo();
        }
    }

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
    	TileEntitySafe tileentitySafe = (TileEntitySafe)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

        if (tileentitySafe != null)
        {
            for (int i1 = 0; i1 < tileentitySafe.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tileentitySafe.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; p_149749_1_.spawnEntityInWorld(entityitem))
                    {
                        int j1 = this.random.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            IInventory iinventory = this.func_149951_m(world, x, y, z);

            if (iinventory != null)
            {
                player.displayGUIChest(iinventory);
            }

            return true;
        }
    }

    public IInventory func_149951_m(World world, int x, int y, int z)
    {
        Object object = (TileEntitySafe)world.getTileEntity(x, y, z);

        if (object == null)
        {
            return null;
        }
       /* else if (world.isSideSolid(x, y + 1, z, DOWN))
        {
            return null;
        }
        else if (func_149953_o(world, x, y, z))
        {
            return null;
        }*/
        else
        {
            return (IInventory)object;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
    	TileEntitySafe tileentitySafe = new TileEntitySafe();
        return tileentitySafe;
    }

    /*private static boolean func_149953_o(World world, int x, int y, int z)
    {
        Iterator iterator = world.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getBoundingBox((double)x, (double)(y + 1), (double)z, (double)(x + 1), (double)(y + 2), (double)(z + 1))).iterator();
        EntityOcelot entityocelot1;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            EntityOcelot entityocelot = (EntityOcelot)iterator.next();
            entityocelot1 = (EntityOcelot)entityocelot;
        }
        while (!entityocelot1.isSitting());

        return true;
    }*/

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory(this.func_149951_m(p_149736_1_, p_149736_2_, p_149736_3_, p_149736_4_));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("anvil_base");
    }
}