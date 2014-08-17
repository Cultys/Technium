package cultys.technium.blocks;

import coloredlightscore.src.api.CLApi;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.init.TechniumBlocks;
import cultys.technium.tileentities.TileEntityTechnium;
import cultys.technium.tileentities.TileEntityTechniumSource;

public class BlockTaintedSoil extends Block {

	public BlockTaintedSoil(Material material, String name) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + name;
		setBlockName(blockName);
		setCreativeTab(TechniumMod.techniumTabs);	
		
		if (Loader.isModLoaded("coloredlightscore")) {
			CLApi.setBlockColorRGB(this, 0.05F, 0.15F, 0.05F);
		} else {
			this.setLightLevel(0.3F);
		}
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[6];

		icons[0] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedDirt_Side");
		icons[1] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedDirt_Top");
		icons[2] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedSand_Side");
		icons[3] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedSand_Top");
		icons[4] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedStone_Side");
		icons[5] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockTaintedStone_Top");

	}


	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntity tileTechnium = blockAccess.getTileEntity(x, y+1, z);
		
		if (tileTechnium != null && (tileTechnium instanceof TileEntityTechnium || tileTechnium instanceof TileEntityTechniumSource)) {
			if (side == 1) {
				if (this == TechniumBlocks.blockTaintedDirt) {
					return icons[1];
				}
				if (this == TechniumBlocks.blockTaintedSand) {
					return icons[3];
				}
				if (this == TechniumBlocks.blockTaintedStone) {
					return icons[5];
				}
			}
		}
		
		if (this == TechniumBlocks.blockTaintedDirt){
			return icons[0];
		}
		if (this == TechniumBlocks.blockTaintedSand) {
			return icons[2];
		}
		if (this == TechniumBlocks.blockTaintedStone) {
			return icons[4];
		}
		
		return icons[0];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (this == TechniumBlocks.blockTaintedDirt){
			return icons[0];
		}
		if (this == TechniumBlocks.blockTaintedSand) {
			return icons[2];
		}
		if (this == TechniumBlocks.blockTaintedStone) {
			return icons[4];
		}
		
		return icons[0];
	}
}
