package dk.turpem.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Iterator;
import java.util.List;

import dk.turpem.block.AllBlocks;
import dk.turpem.item.AllItems;
import dk.turpem.item.TurpemItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class TurpemTabs extends CreativeTabs{
    public static CreativeTabs[] creativeTabArray = new CreativeTabs[12];
    public static final CreativeTabs tabBlock = new TurpemTabs(0, "buildingBlocks")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(AllBlocks.barklessLog2);
        }
    };
    public static final CreativeTabs tabDecorations = new TurpemTabs(1, "decorations")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(AllBlocks.ironSafe);
        }
        @SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
            return 5;
        }
    };
    public static final CreativeTabs tabRedstone = new TurpemTabs(2, "redstone")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.redstone;
        }
    };
    public static final CreativeTabs tabTransport = new TurpemTabs(3, "transportation")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(Blocks.golden_rail);
        }
    };
    public static final CreativeTabs tabMisc = (new TurpemTabs(4, "misc")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.lava_bucket;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.all});
    public static final TurpemTabs tabFood = new TurpemTabs(6, "food")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.apple;
        }
    };
    public static final CreativeTabs tabTools = (new TurpemTabs(7, "tools")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return AllItems.saw;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.digger, EnumEnchantmentType.fishing_rod, EnumEnchantmentType.breakable});
    public static final CreativeTabs tabCombat = (new TurpemTabs(8, "combat")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return AllItems.knifeIron;
        }
    }).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.armor, EnumEnchantmentType.armor_feet, EnumEnchantmentType.armor_head, EnumEnchantmentType.armor_legs, EnumEnchantmentType.armor_torso, EnumEnchantmentType.bow, EnumEnchantmentType.weapon});
    public static final CreativeTabs tabBrewing = new TurpemTabs(9, "brewing")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.potionitem;
        }
    };
    public static final TurpemTabs tabMaterials = new TurpemTabs(10, "materials")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return AllItems.bark;
        }
    };
    public static final CreativeTabs tabVanilla = (new TurpemTabs(getNextID(), "vanilla")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.diamond_pickaxe;
        }
    }).setBackgroundImageName("item_search.png");
    
    private final int tabIndex;
    private final String tabLabel;
    /** Texture to use. */
    private String backgroundImageName = "items.png";
    private boolean hasScrollbar = true;
    /** Whether to draw the title in the foreground of the creative GUI */
    private boolean drawTitle = true;
    private EnumEnchantmentType[] field_111230_s;
    @SideOnly(Side.CLIENT)
    private ItemStack field_151245_t;
    private static final String __OBFID = "CL_00000005";

    public TurpemTabs(String label)
    {
        this(getNextID(), label);
    }

    public TurpemTabs(int id, String name)
    {
    	super(id, name);
        if (id >= creativeTabArray.length)
        {
            CreativeTabs[] tmp = new CreativeTabs[id + 1];
            for (int x = 0; x < creativeTabArray.length; x++)
            {
                tmp[x] = creativeTabArray[x];
            }
            creativeTabArray = tmp;
        }
        this.tabIndex = id;
        this.tabLabel = name;
        creativeTabArray[id] = this;
    }

    @SideOnly(Side.CLIENT)
    public int getTabIndex()
    {
        return this.tabIndex;
    }

    public CreativeTabs setBackgroundImageName(String p_78025_1_)
    {
        this.backgroundImageName = p_78025_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String getTabLabel()
    {
        return this.tabLabel;
    }

    /**
     * Gets the translated Label.
     */
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "itemGroup." + this.getTabLabel();
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack()
    {
        if (this.field_151245_t == null)
        {
            this.field_151245_t = new ItemStack(this.getTabIconItem(), 1, this.func_151243_f());
        }

        return this.field_151245_t;
    }

    @SideOnly(Side.CLIENT)
    public abstract Item getTabIconItem();

    @SideOnly(Side.CLIENT)
    public int func_151243_f()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public String getBackgroundImageName()
    {
        return this.backgroundImageName;
    }

    @SideOnly(Side.CLIENT)
    public boolean drawInForegroundOfTab()
    {
        return this.drawTitle;
    }

    public CreativeTabs setNoTitle()
    {
        this.drawTitle = false;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldHidePlayerInventory()
    {
        return this.hasScrollbar;
    }

    public CreativeTabs setNoScrollbar()
    {
        this.hasScrollbar = false;
        return this;
    }

    /**
     * returns index % 6
     */
    @SideOnly(Side.CLIENT)
    public int getTabColumn()
    {
        if (tabIndex > 11)
        {
            return ((tabIndex - 12) % 10) % 5;
        }
        return this.tabIndex % 6;
    }

    /**
     * returns tabIndex < 6
     */
    @SideOnly(Side.CLIENT)
    public boolean isTabInFirstRow()
    {
        if (tabIndex > 11)
        {
            return ((tabIndex - 12) % 10) < 5;
        }
        return this.tabIndex < 6;
    }

    @SideOnly(Side.CLIENT)
    public EnumEnchantmentType[] func_111225_m()
    {
        return this.field_111230_s;
    }

    public CreativeTabs func_111229_a(EnumEnchantmentType ... p_111229_1_)
    {
        this.field_111230_s = p_111229_1_;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_111226_a(EnumEnchantmentType p_111226_1_)
    {
        if (this.field_111230_s == null)
        {
            return false;
        }
        else
        {
            EnumEnchantmentType[] aenumenchantmenttype = this.field_111230_s;
            int i = aenumenchantmenttype.length;

            for (int j = 0; j < i; ++j)
            {
                EnumEnchantmentType enumenchantmenttype1 = aenumenchantmenttype[j];

                if (enumenchantmenttype1 == p_111226_1_)
                {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * only shows items which have tabToDisplayOn == this
     */
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List list)
    {
        Iterator iterator = Item.itemRegistry.iterator();

        while (iterator.hasNext())
        {
            Item item = (Item)iterator.next();

            if (item == null)
            {
                continue;
            }

            for (CreativeTabs tab : item.getCreativeTabs())
            {
                if(item instanceof TurpemItem && this == tabAllSearch){
                	list.add(new ItemStack(item));
                }
                if (tab == this){
                    item.getSubItems(item, this, list);
                }else if(this == tabVanilla && (tab == CreativeTabs.tabBlock || tab == CreativeTabs.tabBrewing || tab == CreativeTabs.tabCombat || tab == CreativeTabs.tabDecorations || tab == CreativeTabs.tabFood || tab == CreativeTabs.tabMaterials || tab == CreativeTabs.tabMisc || tab == CreativeTabs.tabRedstone || tab == CreativeTabs.tabTools || tab == CreativeTabs.tabTransport)){
                    item.getSubItems(item, this, list);
                }
            }
        }

        if (this.func_111225_m() != null)
        {
            this.addEnchantmentBooksToList(list, this.func_111225_m());
        }     
    }

    /**
     * Adds the enchantment books from the supplied EnumEnchantmentType to the given list.
     */
    @SideOnly(Side.CLIENT)
    public void addEnchantmentBooksToList(List p_92116_1_, EnumEnchantmentType ... p_92116_2_)
    {
        Enchantment[] aenchantment = Enchantment.enchantmentsList;
        int i = aenchantment.length;

        for (int j = 0; j < i; ++j)
        {
            Enchantment enchantment = aenchantment[j];

            if (enchantment != null && enchantment.type != null)
            {
                boolean flag = false;

                for (int k = 0; k < p_92116_2_.length && !flag; ++k)
                {
                    if (enchantment.type == p_92116_2_[k])
                    {
                        flag = true;
                    }
                }

                if (flag)
                {
                    p_92116_1_.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
                }
            }
        }
    }

    public int getTabPage()
    {
        if (tabIndex > 11)
        {
            return ((tabIndex - 12) / 10) + 1;
        }
        return 0;
    }

    public static int getNextID()
    {
        return creativeTabArray.length;
    }

    /**
     * Determines if the search bar should be shown for this tab.
     * 
     * @return True to show the bar
     */
    public boolean hasSearchBar()
    {
        return tabIndex == tabAllSearch.getTabIndex() || tabIndex == tabVanilla.getTabIndex();
    }
}