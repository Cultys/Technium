package cultys.technium.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.tileentities.TileEntityCrusher;

public class BlockCrusher extends BlockContainer {

	protected BlockCrusher(Material material) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + "blockCrusher";

		this.setBlockName(blockName);
		this.setCreativeTab(TechniumMod.techniumTabs);
		//this.setBlockTextureName(TechniumMod.MODID  + ":" + blockName);		
	}

	@Override
	public TileEntity createNewTileEntity(World worldObj, int par2) {
		return new TileEntityCrusher();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
	{
		player.openGui(TechniumMod.instance, 0, world, x, y, z);
		return true;
	}

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[7];

		icons[0] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineBase");
		icons[1] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineDoubleTop");
		icons[2] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineDoubleBottom");
		icons[3] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherTopStill");
		icons[4] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherTopActive1");
		icons[5] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherTopActive2");
		icons[6] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherTopActive3");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntityCrusher tileCrusher = (TileEntityCrusher) blockAccess.getTileEntity(x, y, z);
		
		if (tileCrusher.isMultiblock()) {
			if (tileCrusher.isMaster()) {
				if (side == tileCrusher.getDirection()+2) {
					return icons[2];
				} else { return icons[2]; }
			} else {
				if (side == tileCrusher.getDirection()){
					if (tileCrusher.isActive()) {
						return icons[4 + tileCrusher.getTextureId()];
					} else { return icons[3]; }
				} else { return icons[1]; }
			}

		} else { return icons[0]; }
	}
}
