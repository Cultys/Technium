package cultys.technium.init;

import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import cultys.technium.blocks.BlockTechnium;
import cultys.technium.blocks.BlockTechniumSource;

public class TechniumWorldGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId)
        {
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
	}
	
	private void generateEnd(World world, Random random, int x, int z)
    {
 
    }
 
    private void generateSurface(World world, Random random, int x, int z)
    {
    	for (Entry<String, Boolean> entry : TechniumRef.oreGen_Generate.entrySet()){
    		if (entry.getValue()) {
	    		this.addOreSpawn(	TechniumRef.oreGen_Block.get(entry.getKey()), 
	    							world, 
	    							random, 
	    							x, z, 16, 16,
	    							TechniumRef.oreGen_MinVeinSize.get(entry.getKey()) + random.nextInt(TechniumRef.oreGen_MaxVeinSize.get(entry.getKey())),
	    							TechniumRef.oreGen_VeinsPerChunck.get(entry.getKey()), 
	    							TechniumRef.oreGen_MinY.get(entry.getKey()), 
	    							TechniumRef.oreGen_MaxY.get(entry.getKey()));
    		}
    	}
    	
    	if (random.nextFloat() <= TechniumRef.techniumSourceSpawnChance) {
    		this.addTechniumNode(world, random, x + random.nextInt(16), z + random.nextInt(16));
    	}
    }
 
    private void generateNether(World world, Random random, int x, int z)
    {
        int Xcoord = x + random.nextInt(16);
        int Ycoord = 10 + random.nextInt(128);
        int Zcoord = z + random.nextInt(16);
        (new WorldGenMinable(TechniumBlocks.blockCopperOre, 1, 15, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);
    }
 
    /**
     * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method in your IWorldGeneration extending Class
     *
     * @param block The Block to spawn
     * @param world The World to spawn in
     * @param random A Random object for retrieving random positions within the world to spawn the Block
     * @param blockXPos An int for passing the X-Coordinate for the Generation method
     * @param blockZPos An int for passing the Z-Coordinate for the Generation method
     * @param maxX An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
     * @param maxZ An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
     * @param maxVeinSize An int for setting the maximum size of a vein
     * @param chanceToSpawn An int for the Number of chances available for the Block to spawn per-chunk
     * @param minY int for the minimum Y-Coordinate height at which this block may spawn
     * @param maxY int for the maximum Y-Coordinate height at which this block may spawn
     **/
    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
    {
        assert maxY > minY : 			"The maximum Y must be greater than the Minimum Y";
        assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
        assert minY > 0 : 				"addOreSpawn: The Minimum Y must be greater than 0";
        assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
        assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
 
        int diffBtwnMinMaxY = maxY - minY;
        for (int x = 0; x < chancesToSpawn; x++)
        {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffBtwnMinMaxY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
        }
    }
    
    public void addTechniumNode(World worldObj, Random random, int x, int z){
    	for (int y = 90; y > 50; y--) {
	    	if (worldObj.isAirBlock(x, y, z) || worldObj.getBlock(x, y, z) == Blocks.tallgrass) {
	    		if (BlockTechnium.canSustainTechnium(worldObj, x, y, z)) {
	    			BlockTechniumSource.placeSource(worldObj, x, y, z);
	    		}
	    	}
    	}
    }
}
