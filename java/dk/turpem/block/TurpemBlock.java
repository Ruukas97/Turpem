package dk.turpem.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.turpem.Turpem;
import dk.turpem.creativetab.TurpemTabs;

public class TurpemBlock extends Block{

	protected TurpemBlock(String name, Material material) {
		super(material);
		setBlockName(name);
		setBlockTextureName(Turpem.MODID + ":" + name.toLowerCase());
		GameRegistry.registerBlock(this, name);
		setCreativeTab(TurpemTabs.tabBlock);
	}
}
