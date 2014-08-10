package dk.turpem.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AllBlocks {
	public static Block 
	barklessLog,
	barklessLog2,
	
	oiledChest,
	acaciaChest,
	birchChest,
	darkoakChest,
	jungleChest,
	oakChest,
	spruceChest,
	ironChest,
	
	anvil,
	ironSafe;	

	/**
	 * Adds all blocks in the mod
	 */
	public static void addTurpemBlocks(){
		//Building Blocks
        barklessLog = new BarklessLog("barkless");
        barklessLog2 = new BarklessLog2("barkless");
        
        //Decoration (Named after the #CreativeTabs)
        oiledChest = new TurpemChest("oiledChest", 0, Material.wood);
        acaciaChest = new TurpemChest("acaciaChest", 1, Material.wood);
        birchChest = new TurpemChest("birchChest", 2, Material.wood);
        darkoakChest = new TurpemChest("darkoakChest", 3, Material.wood);
        jungleChest = new TurpemChest("jungleChest", 4, Material.wood);
        oakChest = new TurpemChest("oakChest", 5, Material.wood);
        spruceChest = new TurpemChest("spruceChest", 6, Material.wood);
        ironChest = new TurpemChest("ironChest", 7, Material.iron);    	
	
        //anvil = new Anvil("anvil", Material.iron).setHardness(10F).setStepSound(Block.soundTypeMetal); TODO make TileEntityAnvil
        ironSafe = new Safe("ironSafe", Material.iron).setHardness(4.5F).setStepSound(Block.soundTypeMetal);    	
	}
}
