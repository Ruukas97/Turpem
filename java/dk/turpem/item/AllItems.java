package dk.turpem.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;

public class AllItems {

	public static Item 
	//Materials
	bark,
	log,
	pelt,
	flintStone,
	
	//Tools
	blacksmithHammer,
	brokenWeapon,
	copperKnife,
	ironKnife,
	saw,
	sharpFlint;

	/**
	 * Adds all items in the mod
	 */
	public static void addTurpemItems() {
		//Please keep the list sorted and categorized
		
		//Materials
		bark = new TurpemItem("bark");
		log = new LogItem("log");
		pelt = new TurpemItem("pelt");
		flintStone = new FlintStone("flintStone");
		
		//Tools
		GameRegistry.addShapedRecipe(new ItemStack(ironKnife), new Object[] { "B", "H", 'B', Items.iron_ingot, 'H', Items.stick});
		blacksmithHammer = new TurpemTool("blacksmithHammer"); //TODO Make hammer class
		brokenWeapon = new BrokenWeapon("brokenweapon", ToolMaterial.IRON);
		copperKnife = new Knife("copperKnife", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		ironKnife = new Knife("ironKnife", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		saw = new Saw("saw");
		sharpFlint = new SharpFlint("sharpFlint");
	}
}
