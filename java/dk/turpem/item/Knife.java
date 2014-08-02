package dk.turpem.item;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.turpem.Turpem;
import dk.turpem.block.AllBlocks;
import dk.turpem.creativetab.TurpemTabs;
import dk.turpem.util.TranslationManager;

public class Knife extends TurpemTool {
    private float damage;
    private final Item.ToolMaterial material;
    private static Item handle;
    private static Item blade;
    public static final String[] types = new String[] {"clean", "bloody", "slimy", "dirty"};
	public static final String[] conditions = new String[] {StatCollector.translateToLocal(Turpem.MODID.toLowerCase() + ".condition.dull"), StatCollector.translateToLocal(Turpem.MODID.toLowerCase() + ".condition.normal"), StatCollector.translateToLocal(Turpem.MODID.toLowerCase() + ".condition.sharp")};
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
	
	public Knife(String name, Item handle, Item blade, ToolMaterial material) {
		super(name);
        this.damage = 1.0F + material.getDamageVsEntity();
        this.material = material;
        this.handle = handle;
        this.blade = blade;
		setFull3D();
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(TurpemTabs.tabCombat);
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
	public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		if (itemstack.stackTagCompound == null) {
			itemstack.stackTagCompound = new NBTTagCompound();
		}
		itemstack.stackTagCompound.setString("MadeBy", player.getDisplayName());
		itemstack.stackTagCompound.setInteger("condition", 0);
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(itemstack, player, list, par4);
		if (itemstack.stackTagCompound != null) {
			if(itemstack.stackTagCompound.hasKey("MadeBy")){
				list.add("");
	    		if(material != ToolMaterial.WOOD)
					list.add(EnumChatFormatting.GOLD + TranslationManager.translateToLocal("turpem.tooltip.forgedby") + " " + itemstack.stackTagCompound.getString("MadeBy"));
				else
					list.add(EnumChatFormatting.GOLD + TranslationManager.translateToLocal("turpem.tooltip.craftedby") + " " + itemstack.stackTagCompound.getString("MadeBy"));
			}
    	}
		list.add(EnumChatFormatting.WHITE + getConditionLocalized(itemstack));
    }
	
	//Break Speed
    @Override
	public float func_150893_a(ItemStack itemstack, Block block)
	{
		if(block instanceof BlockNewLog || block instanceof BlockOldLog)
			return 3F;
		Material material = block.getMaterial();
		return material != Material.plants && material != Material.vine && material != Material.leaves ? 1.0F : 1.5F;
	}
		
    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase target, EntityLivingBase attacker)
    {
    	damageItemStack(itemstack, 100, material.getMaxUses(), attacker, handle, blade);
    	if(target instanceof EntitySlime && itemstack.getItemDamage() != 2)
    		itemstack.setItemDamage(2);
    	else if(itemstack.getItemDamage() != 1 && !(target instanceof EntitySkeleton) && !(target instanceof EntityWither) && !(target instanceof EntityWaterMob && !(target instanceof EntitySlime)))
    		itemstack.setItemDamage(1);
		return true;
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
    {
    	World world = player.worldObj;
		int meta = world.getBlockMetadata(x, y, z);
		if(!player.capabilities.isCreativeMode)
			if(world.getBlock(x, y, z) == Blocks.log2){
			Random rand = new Random();
			if (!(rand.nextInt(3) == 0)){
				addBark(player, 1, false);
				return true;
			}
			if (meta == 0) {
				world.setBlock(x, y, z, AllBlocks.barklessLog);
				addBark(player, 2, true);
				return true;
			} else if (meta == 1) {
				world.setBlock(x, y, z, AllBlocks.barklessLog, 1, 3);
				addBark(player, 2, true);
				return true;
			}
		}else if(world.getBlock(x, y, z) == Blocks.log){
			Random rand = new Random();
			if (!(rand.nextInt(3) == 0)){
				addBark(player, 1, false);
				return true;
			}
			if (meta == 0) {
				world.setBlock(x, y, z, AllBlocks.barklessLog2);
				addBark(player, 2, true);
				return false;
			} else if (meta == 1) {
				world.setBlock(x, y, z, AllBlocks.barklessLog2, 1, 3);
				addBark(player, 2, true);
				return true;
			} else if (meta == 2) {
				world.setBlock(x, y, z, AllBlocks.barklessLog, 1, 3);
				addBark(player, 2, true);
				return true;
			} else if (meta == 3) {
				world.setBlock(x, y, z, AllBlocks.barklessLog, 3, 3 );
				addBark(player, 2, true);
				return true;
			}
		}
        return false;
    }
    
    private void addBark(EntityPlayer player, int amount, boolean isLast){
    	ItemStack bark = new ItemStack(AllItems.bark, amount);
		player.inventory.addItemStackToInventory(bark);
		if(!player.worldObj.isRemote){
			if(isLast){
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You cut the last of the " + EnumChatFormatting.DARK_PURPLE + bark.getDisplayName() + EnumChatFormatting.GRAY + " from the log."));
			} else {
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "You cut some " + EnumChatFormatting.DARK_PURPLE + bark.getDisplayName() + EnumChatFormatting.GRAY + " from the log."));
			}
		}
    }
    
    public static int getCondition(ItemStack itemstack){
		if (itemstack.stackTagCompound == null) {
			itemstack.stackTagCompound = new NBTTagCompound();
		}
    	if(itemstack.stackTagCompound.hasKey("condition"))
    		return itemstack.stackTagCompound.getInteger("condition");
		return 0;
    }
    
    public static String getConditionLocalized(ItemStack itemstack){
    	int conditionArrayNumber = getCondition(itemstack) + (conditions.length - 1) / 2;
    	if(conditionArrayNumber<0)
    		conditionArrayNumber = 0;
		return conditions[conditionArrayNumber];
    }
    
    public static void setCondition(ItemStack itemstack, int amount, boolean negate){
    	int value;
    	if(negate)
    		value = amount + getCondition(itemstack);
    	else
    		value = amount;
    	if(value > (conditions.length - 1) / 2 || value < (conditions.length * -1 + 1) / 2)
    		return;
		if(itemstack.stackTagCompound == null)
			itemstack.stackTagCompound = new NBTTagCompound();
		itemstack.stackTagCompound.setInteger("condition", value);
    }
    
    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int x, int y, int z, EntityLivingBase destroyer)
    {
    	if(block == Blocks.log || block == Blocks.log2)
    		damageItemStack(itemstack, 1, material.getMaxUses(), destroyer, handle, blade);
    	else if ((double)block.getBlockHardness(world, x, y, z) != 0.0D)
        {
            damageItemStack(itemstack, 2, material.getMaxUses(), destroyer, handle, blade);
            if(block.getMaterial().equals(Material.ground) || block.getMaterial().equals(Material.grass))
            	itemstack.setItemDamage(3);
        }

        return true;
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
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
    	player.addChatMessage(new ChatComponentTranslation("test.cool"));
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return itemstack;
        }
        else if (itemstack.getItemDamage() > 0)
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                /*if (!world.canMineBlock(player, i, j, k))
                {
                    return itemstack;
                }*/

                Material material = world.getBlock(i, j, k).getMaterial();

                if (material == Material.water && !world.isRemote)
                {   
                	player.addChatMessage(new ChatComponentText(TranslationManager.translateActionMessage("clean", player)));
                	itemstack.setItemDamage(0);
                	return itemstack;
                }
            }
        }
		return itemstack;
    }
}