package dk.turpem.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class AllItems {

	public static Item 
	//Materials
	bark,
	copperBar,
	log,
	pelt,
	flintStone,
	
	//Tools
	blacksmithHammer,
	brokenWeapon,
	knifeCopper,
	knifeIron,
	saw,
	sharpFlint;

	/**
	 * Adds all items in the mod
	 */
	public static void addTurpemItems() {
		//Please keep the list sorted and categorized
		
		//Materials
		bark = new TurpemItem("bark");
		copperBar = new TurpemItem("copperBar");
		log = new LogItem("log");
		pelt = new TurpemItem("pelt");
		flintStone = new FlintStone("flintStone");
		
		//Tools
		//GameRegistry.addShapedRecipe(new ItemStack(knifeIron), new Object[] { "B", "H", 'B', Items.iron_ingot, 'H', Items.stick});
		blacksmithHammer = new TurpemTool("blacksmithHammer"); //TODO Make hammer class
		brokenWeapon = new BrokenWeapon("brokenweapon", ToolMaterial.IRON);
		knifeCopper = new Knife("knifeCopper", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		knifeIron = new Knife("knifeIron", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		saw = new Saw("saw");
		sharpFlint = new SharpFlint("sharpFlint");
	}
}
