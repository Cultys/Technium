package cultys.technium.blocks;

import cultys.technium.TechniumMod;
import cultys.technium.init.TechniumBlocks;
import cultys.technium.tileentities.TileEntityTechniumSource;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTechniumSource extends BlockContainer {

	public BlockTechniumSource(Material material) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + "blockTechniumSource";
		
		this.setBlockName(blockName);
		this.setCreativeTab(TechniumMod.techniumTabs);
		this.setBlockTextureName(TechniumMod.MODID  + ":" + blockName);
		this.setLightLevel(0.3F);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1F, 0.9F);
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TileEntityTechniumSource();
	}
	
	@Override
	 public void onBlockAdded(World worldObj, int x, int y, int z) {
		TileEntityTechniumSource tileSource = (TileEntityTechniumSource) worldObj.getTileEntity(x, y, z);
		tileSource.generateMineralComposition();
	}

	public static boolean placeSource (World worldObj, int x, int y, int z) {
		worldObj.setBlock(x, y, z, TechniumBlocks.blockTechniumSource, 0, 3);
		worldObj.notifyBlocksOfNeighborChange(x, y, z, TechniumBlocks.blockTechniumSource);
		return true;
	}
}
