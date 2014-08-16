package cultys.technium.init;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

public class TechniumRef {
	//Values for technium crystals.
	public static float techniumGrowChance = 0.1F;
	public static int techniumGrowTicks = 1200;
	
	//Values for technium source blocks.
	public static int techniumSourceMaxChildren = 50;
	public static int techniumSourceSproutTicks = 2400;
	public static float techniumSourceSproutChance = 0.25F;
	public static int techniumSourceSproutAttempts = 20;
	public static int techniumSourceSproutRadius = 25;
	public static int techniumSourceSproutDY = 3;
	public static float techniumSourceSpawnChance = 0.01F;
	
	//Global values for both technium crystal and source blocks.
	public static final short techniumMaxColoring = 100;
	public static final int defaultMineralValue = 500;
	
	//Mineral en color mapping.
	public static Map<String, Float> defaultMineralComposition = new HashMap<String, Float>();
	public static Map<String, Short> mineralRedValue = new HashMap<String, Short>();
	public static Map<String, Short> mineralGreenValue = new HashMap<String, Short>();
	public static Map<String, Short> mineralBlueValue = new HashMap<String, Short>();
	
	//Ore configuration.
	public static Map<String, Integer> oreGen_MaxY = new HashMap<String, Integer>();
	public static Map<String, Integer> oreGen_MinY = new HashMap<String, Integer>();
	public static Map<String, Integer> oreGen_MinVeinSize = new HashMap<String, Integer>();
	public static Map<String, Integer> oreGen_MaxVeinSize = new HashMap<String, Integer>();
	public static Map<String, Integer> oreGen_VeinsPerChunck = new HashMap<String, Integer>();
	public static Map<String, Boolean> oreGen_Generate = new HashMap<String, Boolean>();
	public static Map<String, Block> oreGen_Block = new HashMap<String, Block>();
	
	public static void initializeReference() {
		defaultMineralComposition.clear();
		defaultMineralComposition.put("oreCopper", 0.1F);
		addMineralColorMapping("oreCopper", 243, 74, 0);
		
		defaultMineralComposition.put("oreTin", 0.1F);
		addMineralColorMapping("oreTin", 160, 160, 160);
		
		defaultMineralComposition.put("oreSilver", 0.1F);
		addMineralColorMapping("oreSilver", 220, 220, 220);
		
		defaultMineralComposition.put("oreLead", 0.1F);
		addMineralColorMapping("oreLead", 58, 56, 63);
		
		defaultMineralComposition.put("oreWolframite", 0.1F);
		addMineralColorMapping("oreWolframite", 63, 47, 45);
		
		defaultMineralComposition.put("oreIron", 0.1F);
		addMineralColorMapping("oreIron", 130, 130, 130);
		
		defaultMineralComposition.put("oreGold", 0.1F);
		addMineralColorMapping("oreGold", 255, 197, 73);
		
		defaultMineralComposition.put("oreRedstone", 0.1F);
		addMineralColorMapping("oreRedstone", 255, 20, 20);
		
		defaultMineralComposition.put("oreDiamond", 0.1F);
		addMineralColorMapping("oreDiamond", 119, 232, 255);
		
		defaultMineralComposition.put("oreLapis", 0.1F);
		addMineralColorMapping("oreLapis", 20, 20, 255);

		defaultMineralComposition.put("oreEmerald", 0.1F);
		addMineralColorMapping("oreEmerald", 20, 255, 20);
		
		defaultMineralComposition.put("oreCoal", 0.1F);
		addMineralColorMapping("oreCoal", 10, 10, 10);
		
		mineralCompositionRefractor(defaultMineralComposition);
	}
	
	public static void addMineralColorMapping (String oreDictName, int i, int j, int k)
	{
		mineralRedValue.put(oreDictName, (short) Math.min(i, 255));
		mineralGreenValue.put(oreDictName, (short) Math.min(j, 255));
		mineralBlueValue.put(oreDictName, (short) Math.min(k, 255));
	}
	
	public static void mineralCompositionRefractor (Map<String, Float> mineralComposition) {
		float totals = 0;
		
		for (Map.Entry<String, Float> entry : mineralComposition.entrySet()){
		    totals = totals + entry.getValue();
		}
		
		for (Map.Entry<String, Float> entry : mineralComposition.entrySet()){
		    mineralComposition.put(entry.getKey(), entry.getValue()/totals);
		}
	}
	
	public static void loadConfig(File configFile) {
		Configuration config = new Configuration(configFile);
		
		config.load();
		
		oreGen_MaxY.put("oreCopper", config.getInt("oreCopper_maxY", "OreGeneration", 64, 0, 128, ""));
		oreGen_MinY.put("oreCopper", config.getInt("oreCopper_minY", "OreGeneration", 15, 0, 128, ""));
		oreGen_MinVeinSize.put("oreCopper", config.getInt("oreCopper_minVeinSize", "OreGeneration", 2, 0, 500, ""));
		oreGen_MaxVeinSize.put("oreCopper", config.getInt("oreCopper_maxVeinSize", "OreGeneration", 5, 1, 500, ""));
		oreGen_VeinsPerChunck.put("oreCopper", config.getInt("oreCopper_veinsPerChunck", "OreGeneration", 20, 1, 500, ""));
		oreGen_Generate.put("oreCopper", config.getBoolean("oreCopper_spawn", "OreGeneration", true, ""));
		
		oreGen_MaxY.put("oreTin", config.getInt("oreTin_maxY", "OreGeneration", 64, 0, 128, ""));
		oreGen_MinY.put("oreTin", config.getInt("oreTin_minY", "OreGeneration", 15, 0, 128, ""));
		oreGen_MinVeinSize.put("oreTin", config.getInt("oreTin_minVeinSize", "OreGeneration", 2, 0, 500, ""));
		oreGen_MaxVeinSize.put("oreTin", config.getInt("oreTin_maxVeinSize", "OreGeneration", 5, 1, 500, ""));
		oreGen_VeinsPerChunck.put("oreTin", config.getInt("oreTin_veinsPerChunck", "OreGeneration", 20, 1, 500, ""));
		oreGen_Generate.put("oreTin", config.getBoolean("oreTin_spawn", "OreGeneration", true, ""));
		
		oreGen_MaxY.put("oreLead", config.getInt("oreLead_maxY", "OreGeneration", 40, 0, 128, ""));
		oreGen_MinY.put("oreLead", config.getInt("oreLead_minY", "OreGeneration", 5, 0, 128, ""));
		oreGen_MinVeinSize.put("oreLead", config.getInt("oreLead_minVeinSize", "OreGeneration", 2, 0, 500, ""));
		oreGen_MaxVeinSize.put("oreLead", config.getInt("oreLead_maxVeinSize", "OreGeneration", 4, 1, 500, ""));
		oreGen_VeinsPerChunck.put("oreLead", config.getInt("oreLead_veinsPerChunck", "OreGeneration", 10, 1, 500, ""));
		oreGen_Generate.put("oreLead", config.getBoolean("oreLead_spawn", "OreGeneration", true, ""));
		
		oreGen_MaxY.put("oreSilver", config.getInt("oreSilver_maxY", "OreGeneration", 40, 0, 128, ""));
		oreGen_MinY.put("oreSilver", config.getInt("oreSilver_minY", "OreGeneration", 5, 0, 128, ""));
		oreGen_MinVeinSize.put("oreSilver", config.getInt("oreSilver_minVeinSize", "OreGeneration", 2, 0, 500, ""));
		oreGen_MaxVeinSize.put("oreSilver", config.getInt("oreSilver_maxVeinSize", "OreGeneration", 4, 1, 500, ""));
		oreGen_VeinsPerChunck.put("oreSilver", config.getInt("oreSilver_veinsPerChunck", "OreGeneration", 10, 1, 500, ""));
		oreGen_Generate.put("oreSilver", config.getBoolean("oreSilver_spawn", "OreGeneration", true, ""));
		
		oreGen_MaxY.put("oreWolframite", config.getInt("oreWolframite_maxY", "OreGeneration", 22, 0, 128, ""));
		oreGen_MinY.put("oreWolframite", config.getInt("oreWolframite_minY", "OreGeneration", 2, 0, 128, ""));
		oreGen_MinVeinSize.put("oreWolframite", config.getInt("oreWolframite_minVeinSize", "OreGeneration", 1, 0, 500, ""));
		oreGen_MaxVeinSize.put("oreWolframite", config.getInt("oreWolframite_maxVeinSize", "OreGeneration", 2, 1, 500, ""));
		oreGen_VeinsPerChunck.put("oreWolframite", config.getInt("oreWolframite_veinsPerChunck", "OreGeneration", 10, 1, 500, ""));
		oreGen_Generate.put("oreWolframite", config.getBoolean("oreWolframite_spawn", "OreGeneration", true, ""));
		
		techniumGrowChance = config.getFloat("techniumGrowChance", "Technium", 0.1F, 0F, 1.0F, "");
		techniumGrowTicks = config.getInt("techniumGrowTicks", "Technium", 1200, 20, 72000, "");
		
		techniumSourceMaxChildren = config.getInt("techniumSourceMaxChildren", "Technium", 50, 0, 100, "");
		techniumSourceSproutTicks = config.getInt("techniumSproutTicks", "Technium", 2400, 20, 72000, "");
		techniumSourceSproutChance = config.getFloat("techniumSproutChance", "Technium", 0.25F, 0F, 1.0F, "");
		techniumSourceSproutAttempts = config.getInt("techniumSproutAttempts", "Technium", 20, 1, 30, "");
		techniumSourceSproutRadius = config.getInt("techniumSproutRadius", "Technium", 20, 1, 50, "");
		techniumSourceSproutDY = config.getInt("techniumSproutDY", "Technium", 3, 1, 10, "");
		techniumSourceSpawnChance = config.getFloat("techniumSourceChance", "Technium", 0.02F, 0F, 1.0F, "");
		
		
		config.save();
	}
	
	public static void mapOres() {
		oreGen_Block.put("oreCopper", TechniumBlocks.blockCopperOre);
		oreGen_Block.put("oreTin", TechniumBlocks.blockTinOre);
		oreGen_Block.put("oreLead", TechniumBlocks.blockLeadOre);
		oreGen_Block.put("oreSilver", TechniumBlocks.blockSilverOre);
		oreGen_Block.put("oreWolframite", TechniumBlocks.blockWolframiteOre);
	}
}
