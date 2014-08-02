package cultys.technium.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;

public class TechniumBlocks {
	
	public static Block blockTechnium;
	public static Block blockTechniumSource;
	public static Block blockCopperOre;
	public static Block blockTinOre;
	public static Block blockLeadOre;
	public static Block blockSilverOre;
	public static Block blockWolframiteOre;
	public static Block blockCrusher;
	
	public static void init(){
		blockTechnium = new BlockTechnium(Material.glass);
		blockTechniumSource = new BlockTechniumSource(Material.glass);
		blockCopperOre = new BlockOre(Material.rock, 1, "blockCopperOre").setHardness(2.0F);
		blockTinOre = new BlockOre(Material.rock, 1, "blockTinOre").setHardness(2.0F);
		blockLeadOre = new BlockOre(Material.rock, 2, "blockLeadOre").setHardness(3.0F);
		blockSilverOre = new BlockOre(Material.rock, 2, "blockSilverOre").setHardness(2.0F);
		blockWolframiteOre = new BlockOre(Material.rock, 3, "blockWolframiteOre").setHardness(3.0F);
		blockCrusher = new BlockCrusher(Material.rock);
		
		GameRegistry.registerBlock(blockTechnium, "blockTechium");
		GameRegistry.registerBlock(blockTechniumSource, "blockTechniumSource");
		GameRegistry.registerBlock(blockCopperOre, "blockCopperOre");
		GameRegistry.registerBlock(blockTinOre, "blockTinOre");
		GameRegistry.registerBlock(blockLeadOre, "blockLeadOre");
		GameRegistry.registerBlock(blockSilverOre, "blockSilverOre");
		GameRegistry.registerBlock(blockWolframiteOre, "blockWolframiteOre");
		GameRegistry.registerBlock(blockCrusher, "blockCrusher");
		
		OreDictionary.registerOre("oreCopper", blockCopperOre);
		OreDictionary.registerOre("oreTin", blockTinOre);
		OreDictionary.registerOre("oreLead", blockLeadOre);
		OreDictionary.registerOre("oreSilver", blockSilverOre);
		OreDictionary.registerOre("oreWolframite", blockWolframiteOre);
		
		TechniumMod.writeToConsole("Blocks initialized.");
	}
}
