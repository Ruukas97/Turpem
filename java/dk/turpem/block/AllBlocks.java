package dk.turpem.block;

import cpw.mods.fml.common.registry.GameRegistry;
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
	ironSafe,
	
	fakeshit;
	

	public static void addTurpemBlocks(){
        barklessLog = new BarklessLog("barkless");
        barklessLog2 = new BarklessLog2("barkless");
        
        oiledChest = new TurpemChest("oiledChest", 0, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        acaciaChest = new TurpemChest("acaciaChest", 1, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        birchChest = new TurpemChest("birchChest", 2, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        darkoakChest = new TurpemChest("darkoakChest", 3, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        jungleChest = new TurpemChest("jungleChest", 4, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        oakChest = new TurpemChest("oakChest", 5, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        spruceChest = new TurpemChest("spruceChest", 6, Material.wood).setHardness(2.5F).setStepSound(Block.soundTypeWood);
        ironChest = new TurpemChest("ironChest", 7, Material.iron).setHardness(4.5F).setStepSound(Block.soundTypeMetal);    	
	
        //anvil = new Anvil("anvil", Material.iron).setHardness(10F).setStepSound(Block.soundTypeMetal);
        ironSafe = new Safe("ironSafe", Material.iron).setHardness(4.5F).setStepSound(Block.soundTypeMetal);    	
	}
}
