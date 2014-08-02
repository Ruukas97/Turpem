package dk.turpem.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.creativetab.TurpemTabs;

public class BrokenWeapon extends TurpemTool {
    private float damage;
    private final Item.ToolMaterial material;
    public static final String[] types = new String[] {"knife"};
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public BrokenWeapon(String name, ToolMaterial material) {
		super(name);
        this.damage = 1.0F;
        this.material = material;
		setFull3D();
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(TurpemTabs.tabMaterials);
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icons = new IIcon[types.length];

        for (int i = 0; i < types.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon(this.getIconString() + "_" + types[i]);
        }
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, types.length-1);
        return this.icons[j];
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, types.length-1);
        return super.getUnlocalizedName() + "_" + types[i];
    }
    
    @Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
        
    public String getToolMaterialName()
    {
        return material.toString();
    }
}