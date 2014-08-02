package dk.turpem.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;

public class AllItems {

	public static Item 
	bark,
	log,
	pelt,
	flintStone,
	
	brokenWeapon,
	copperKnife,
	ironKnife,
	saw,
	sharpFlint;

	public static void addTurpemItems() {
		//Materials
		bark = new TurpemItem("bark");
		log = new LogItem("log");
		pelt = new TurpemItem("pelt");
		flintStone = new FlintStone("flintStone");
		
		//Tools
		GameRegistry.addShapedRecipe(new ItemStack(ironKnife), new Object[] { "B", "H", 'B', Items.iron_ingot, 'H', Items.stick});
		brokenWeapon = new BrokenWeapon("brokenweapon", ToolMaterial.IRON);
		copperKnife = new Knife("copperKnife", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		ironKnife = new Knife("ironKnife", Items.stick, Items.iron_ingot, ToolMaterial.IRON);
		saw = new Saw("saw");
		sharpFlint = new SharpFlint("sharpFlint");
	}
}
