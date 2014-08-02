package dk.turpem.block;

import java.util.List;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.Turpem;
import dk.turpem.creativetab.TurpemTabs;

public class BarklessLog extends BlockLog
{
    public static final String[] types = new String[] {"acacia", "birch", "darkoak", "jungle"};
    
    public BarklessLog(String name){
    	setBlockName(name);
    	setBlockTextureName(name);
    	GameRegistry.registerBlock(this, BarklessLogItem.class, Turpem.MODID + name);
    	setCreativeTab(TurpemTabs.tabBlock);
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
        list.add(new ItemStack(item, 1, 2));
        list.add(new ItemStack(item, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.field_150167_a = new IIcon[types.length];
        this.field_150166_b = new IIcon[types.length];

        for (int i = 0; i < this.field_150167_a.length; ++i)
        {
            this.field_150167_a[i] = iconRegister.registerIcon(Turpem.MODID + ":" + this.getTextureName() + "_" + types[i]);
            this.field_150166_b[i] = iconRegister.registerIcon(Turpem.MODID + ":" + this.getTextureName() + "_" + types[i] + "_top");
        }
    }
    
    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
}