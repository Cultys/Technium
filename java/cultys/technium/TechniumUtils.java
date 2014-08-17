package cultys.technium;

import cultys.technium.init.TechniumBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;


public class TechniumUtils {

	public static void corruptBlockAt (World worldObj, int x, int y, int z) {
		Block block = worldObj.getBlock(x, y, z);
		
		if (block == Blocks.dirt || block == Blocks.grass) {
			worldObj.setBlock(x, y, z, TechniumBlocks.blockTaintedDirt);
		}
		if (block == Blocks.sand) {
			worldObj.setBlock(x, y, z, TechniumBlocks.blockTaintedSand);
		}
		if (block == Blocks.stone) {
			worldObj.setBlock(x, y, z, TechniumBlocks.blockTaintedStone);
		}
	}
	
}
