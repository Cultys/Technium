package cultys.technium.blocks;

import java.util.Random;

import coloredlightscore.src.api.CLApi;
import cpw.mods.fml.common.Loader;
import cultys.technium.TechniumMod;
import cultys.technium.init.TechniumBlocks;
import cultys.technium.init.TechniumItems;
import cultys.technium.tileentities.TileEntityTechnium;
import cultys.technium.tileentities.TileEntityTechniumSource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTechnium extends BlockContainer {

	public BlockTechnium(Material material) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + "blockTechnium";
		
		this.setBlockName(blockName);
		this.setCreativeTab(TechniumMod.techniumTabs);
		this.setBlockTextureName(TechniumMod.MODID  + ":" + blockName);
		
		if (Loader.isModLoaded("coloredlightscore")) {
			CLApi.setBlockColorRGB(this, 0.1F, 0.3F, 0.1F);
		} else {
			this.setLightLevel(0.3F);
		}
		
		this.setStepSound(soundTypeGlass);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
    	this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    	
    	if (meta == 0)
    	{
    		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.6F, 0.8F);
    	}

    	if (meta == 1)
    	{
    		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
    	}
    	
    	if (meta == 2)
    	{
    		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
    	}
    	
    	if (meta == 3)
    	{
    		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
    	}
	}
	
	@Override
	public void updateTick(World worldObj, int x, int y, int z, Random random)
    {
		this.checkAndShatter(worldObj, x, y, z);
    }
	
	@Override
	public void onNeighborBlockChange(World worldObj, int x, int y, int z, Block block)
    {
        super.onNeighborBlockChange(worldObj, x, y, z, block);
        this.checkAndShatter(worldObj, x, y, z);
    }
	
	public void checkAndShatter(World worldObj, int x, int y, int z){
		if (!canSustainTechnium (worldObj, x, y, z)) {
			TileEntityTechnium tileEntity = (TileEntityTechnium) worldObj.getTileEntity(x, y, z);
			tileEntity.shatter(worldObj);
		}
	}
	
	public static boolean canSustainTechnium(World worldObj, int x, int y, int z) {
		Block block = worldObj.getBlock(x, y-1, z);
		
		if (block == Blocks.dirt ||
			block == Blocks.sandstone ||
			block == Blocks.stone ||
			block == Blocks.grass ||
			block == Blocks.sand ||
			block == TechniumBlocks.blockTaintedDirt ||
			block == TechniumBlocks.blockTaintedSand ||
			block == TechniumBlocks.blockTaintedStone) {

			return true;
		}
		
		return false;
	}

	public static boolean placeTechnium (World worldObj, Block block, int x, int y, int z, int metadata) {
		worldObj.setBlock(x, y, z, block, metadata, 3);
		worldObj.notifyBlocksOfNeighborChange(x, y, z, block);
		return true;
	}
	
	public static boolean placeTechniumAndSetParent (World worldObj, Block block, int x, int y, int z, int xParent, int yParent, int zParent, int metadata) {
		placeTechnium(worldObj, block, x, y, z, metadata);
		TileEntityTechnium tileEntity = (TileEntityTechnium) worldObj.getTileEntity(x, y, z);
		
		if (tileEntity != null) {
			tileEntity.setParent(xParent, yParent, zParent);
		}
		
		return true;
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
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityTechnium();
	}

	@Override
	public int quantityDropped(Random random)
    {
        return 0;
    }

	@Override
    public Item getItemDropped(int par1, Random random, int par3)
    {
        return null;
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
		dropTechniumShards(world, x, y, z);
		super.breakBlock(world, x, y, z, block, metadata);
    }
	
	private void dropTechniumShards(World world, int x, int y, int z){
		Random rand = new Random();

		TileEntityTechnium tileTechnium = (TileEntityTechnium) world.getTileEntity(x, y, z);
		
		int stackSize = (int) Math.max((rand.nextInt((tileTechnium.getStage() + 1) * 4)), 1);
		ItemStack techniumShards = new ItemStack(TechniumItems.itemTechniumShard, stackSize, 0);
		techniumShards.stackTagCompound = new NBTTagCompound();
		tileTechnium.writeItemNBT(techniumShards.stackTagCompound);
		
		EntityItem entityItem = new EntityItem(world, x, y, z, techniumShards);
		world.spawnEntityInWorld(entityItem);
		
		TileEntityTechniumSource tileSource;
		TileEntity tileEntity = world.getTileEntity(tileTechnium.xParent, tileTechnium.yParent, tileTechnium.zParent);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityTechniumSource) {
				tileSource = (TileEntityTechniumSource) tileEntity;
				tileSource.removeChild();;
			}
		}
	}
}
