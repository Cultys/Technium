package cultys.technium.blocks;

import cultys.technium.TechniumMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOre extends Block {
	
	public BlockOre(Material material, int miningLevel, String baseName) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + baseName;
		
		setCreativeTab(TechniumMod.techniumTabs);
		setBlockName(blockName);
		setHarvestLevel("pickaxe", miningLevel);
		setStepSound(Block.soundTypeStone);
		setBlockTextureName(TechniumMod.MODID  + ":" + blockName);
	}
}
