package cultys.technium.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;
import cultys.technium.blocks.BlockCrusher;
import cultys.technium.blocks.BlockOre;
import cultys.technium.blocks.BlockTaintedSoil;
import cultys.technium.blocks.BlockTechnium;
import cultys.technium.blocks.BlockTechniumSource;

public class TechniumBlocks {
	
	public static final Block blockTechnium = new BlockTechnium(Material.glass);
	public static final Block blockTechniumSource = new BlockTechniumSource(Material.glass);
	public static final Block blockCopperOre = new BlockOre(Material.rock, 1, "blockCopperOre").setHardness(2.0F);
	public static final Block blockTinOre = new BlockOre(Material.rock, 1, "blockTinOre").setHardness(2.0F);
	public static final Block blockLeadOre = new BlockOre(Material.rock, 2, "blockLeadOre").setHardness(3.0F);
	public static final Block blockSilverOre = new BlockOre(Material.rock, 2, "blockSilverOre").setHardness(2.0F);
	public static final Block blockWolframiteOre = new BlockOre(Material.rock, 3, "blockWolframiteOre").setHardness(3.0F);
	public static final Block blockCrusher = new BlockCrusher(Material.rock);
	public static final Block blockTaintedDirt = new BlockTaintedSoil(Material.grass, "blockTaintedDirt");
	public static final Block blockTaintedSand = new BlockTaintedSoil(Material.sand, "blockTaintedSand");
	public static final Block blockTaintedStone= new BlockTaintedSoil(Material.rock, "blockTaintedStone");
	
	public static void init(){

		GameRegistry.registerBlock(blockTechnium, "blockTechium");
		GameRegistry.registerBlock(blockTechniumSource, "blockTechniumSource");
		GameRegistry.registerBlock(blockCopperOre, "blockCopperOre");
		GameRegistry.registerBlock(blockTinOre, "blockTinOre");
		GameRegistry.registerBlock(blockLeadOre, "blockLeadOre");
		GameRegistry.registerBlock(blockSilverOre, "blockSilverOre");
		GameRegistry.registerBlock(blockWolframiteOre, "blockWolframiteOre");
		GameRegistry.registerBlock(blockCrusher, "blockCrusher");
		GameRegistry.registerBlock(blockTaintedDirt, "blockTaintedDirt");
		GameRegistry.registerBlock(blockTaintedSand, "blockTaintedSand");
		GameRegistry.registerBlock(blockTaintedStone, "blockTaintedStone");
		
		OreDictionary.registerOre("oreCopper", blockCopperOre);
		OreDictionary.registerOre("oreTin", blockTinOre);
		OreDictionary.registerOre("oreLead", blockLeadOre);
		OreDictionary.registerOre("oreSilver", blockSilverOre);
		OreDictionary.registerOre("oreWolframite", blockWolframiteOre);
		
		TechniumMod.writeToConsole("Blocks initialized.");
	}
}
