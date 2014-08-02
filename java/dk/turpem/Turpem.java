package dk.turpem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import dk.turpem.block.AllBlocks;
import dk.turpem.client.gui.GuiHandler;
import dk.turpem.event.ConnectionManager;
import dk.turpem.event.PlayerEvents;
import dk.turpem.event.ServerEvents;
import dk.turpem.item.AllItems;
import dk.turpem.packet.RequestDisplayName;
import dk.turpem.packet.RequestNameChange;
import dk.turpem.packet.SendDisplayName;
import dk.turpem.potion.TurpemPotion;
import dk.turpem.proxy.CommonProxy;
import dk.turpem.tileentity.TileEntitySafe;
import dk.turpem.tileentity.TileEntityTurpemChest;
import dk.turpem.util.RecipeRemover;

@Mod(name = Turpem.NAME ,modid = Turpem.MODID, version = Turpem.VERSION)
public class Turpem
{
	/**
	 * The localised name of the mod
	 */
	public static final String NAME = "Turpem";
	/**
	 * The ModID
	 */
    public static final String MODID = "turpem";
    /**
     * The version of the mod
     */
    public static final String VERSION = "0.5";
    
    /**
     * A list containing the Minecraft usernames of people who have p in the development of the mod
     */
    public static ArrayList<String> developers = new ArrayList();
    /**
     * A list containing the Minecraft usernames of people who have provided help in form of textures, sound, solving a problem etc.
     */
    public static final List<String> helpful = Arrays.asList(new String[] {});
    /**
     * A list containing the Minecraft usernames of people who have donated.
     */
    public static ArrayList<String> donators = new ArrayList();

    /**
     * The GuiHandler of Turpem
     */
    public GuiHandler guiHandler = new GuiHandler();
	public static Potion frozenPotion;
    /**
     * The SimpleNetworkWrapper of Turpem
     */
    public static SimpleNetworkWrapper snw; 
    
    /**
     * The ModMetadata containing mcmod.info data
     */
    @Metadata(MODID)
    public static ModMetadata meta;

    /**
     * Turpems sided proxy
     */
	@SidedProxy(clientSide = "dk.turpem.proxy.ClientProxy", serverSide = "dk.turpem.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	/**
	 * Instance of Turpem
	 */
	@Instance(MODID)
	public static Turpem instance;
	
	/**
	 * The Character Creation Graphical User Interface
	 */
	public static final int guiCharacterCreation = 1;
    
	/**
	 * Called when server starts
	 * @param event
	 */
    @EventHandler
    private void serverStart(FMLServerStartingEvent event) 
    {
    	System.out.println("Server Started!");
        MinecraftForge.EVENT_BUS.register(new ServerEvents());
    }
    
    /**
     * Called when Minecraft is launched
     * @param event
     */
    @EventHandler
    private void preInit(FMLPreInitializationEvent event){    	
    	setModMeta();
    	
        snw = NetworkRegistry.INSTANCE.newSimpleChannel(MODID); 
        snw.registerMessage(RequestNameChange.Handler.class, RequestNameChange.class, 0, Side.SERVER); 
        snw.registerMessage(SendDisplayName.Handler.class, SendDisplayName.class, 1, Side.CLIENT); 
        snw.registerMessage(RequestDisplayName.Handler.class, RequestDisplayName.class, 2, Side.SERVER); 
        
    	proxy.registerEventHandlers();
    	proxy.registerEventRenderers();
    	
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
		FMLCommonHandler.instance().bus().register(new ConnectionManager());		

		//For making custom potion effect possible
		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[])f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			}
			catch (Exception e) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}
    }
   
    /**
     * Called right after {@link #preInit(FMLPreInitializationEvent)}
     * @param event
     */
    @EventHandler
    private void init(FMLInitializationEvent event)
    {    
    	proxy.setNametagRange(10, 3);
    	RecipeRemover.removeRecipe(Item.getItemFromBlock(Blocks.planks));
    	    	
    	AllBlocks.addTurpemBlocks();
    	AllItems.addTurpemItems();
    	GameRegistry.registerTileEntity(TileEntityTurpemChest.class, "turpemChest");
    	GameRegistry.registerTileEntity(TileEntitySafe.class, "turpemSafe");
    	
    	frozenPotion = (new TurpemPotion(32, true, 0)).setIconIndex(0, 0).setPotionName("turpem.potion.frozen");
    }
    
    /**
     * Called right after {@link #init(FMLInitializationEvent)}
     * @param event
     */
    @EventHandler
    private void postInit(FMLPostInitializationEvent event){
    	readListFromUrl("DeveloperList", developers, "https://raw.githubusercontent.com/Zerotiger/Turpem/master/data/Developers.turpem");
    	readListFromUrl("DonatorList", donators, "https://raw.githubusercontent.com/Zerotiger/Turpem/master/data/Donators.turpem");
    }
    
    /**
     * Fills the mcmod.info file
     */
    private void setModMeta()
    {
    	/**
    	 * List containing the www.minecraftforum.net usernames of the authors.
    	 */
        final List<String> authors = Arrays.asList(new String[] {"GamingTutsDK"});
        meta.modId = MODID;
        meta.name = NAME;
        meta.description = "WIP";
        //meta.url = "www.google.dk";
        meta.logoFile = "/turpem_logo.png";
        meta.version = VERSION;
        meta.authorList = authors;
        meta.credits = "The helpful Minecraft community. The3141 for barklesslog-side textures.";
        meta.autogenerated = false;
    }
    
    /**
     * Loads a list from a url
     */
    private void readListFromUrl(String name, ArrayList<String> list, String urlString)
    {
    	try	{     
        	URL url = new URL(urlString);
    		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    		String donatorlist;
    		while ((donatorlist = in.readLine()) != null){
    			donators.add(donatorlist);     
    		}
    		FMLLog.getLogger().info("[" + NAME + "] Read " + name + " from + " + url + " : " + donators.toString());        

    		in.close();
    	}
    	catch (MalformedURLException e){
    		FMLLog.getLogger().info("[" + NAME + "] Couldn't read " + name + " from url. MalformedURLException");
    	}
    	catch (IOException e){
    		FMLLog.getLogger().info("[" + NAME + "] Couldn't read " + name + " from url. IOException");
    	}
    }
}