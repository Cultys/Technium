package cultys.technium.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.tileentities.TileEntityCrusher;

public class BlockCrusher extends BlockContainer {

	public BlockCrusher(Material material) {
		super(material);
		String blockName = TechniumMod.MODID + "_" + "blockCrusher";

		this.setBlockName(blockName);
		this.setCreativeTab(TechniumMod.techniumTabs);		
	}

	@Override
	public TileEntity createNewTileEntity(World worldObj, int par2) {
		return new TileEntityCrusher();
	}

	@Override
	public void onBlockPlacedBy(World worldObj, int x, int y, int z, EntityLivingBase player, ItemStack item)
	{
		int direction = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		TileEntityCrusher tileCrusher = (TileEntityCrusher) worldObj.getTileEntity(x, y, z);

		if (direction == 0) { tileCrusher.setDirection(2);}
		if (direction == 1) { tileCrusher.setDirection(5);}
		if (direction == 2) { tileCrusher.setDirection(3);}
		if (direction == 3) { tileCrusher.setDirection(4);}

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
		icons = new IIcon[6];

		icons[0] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineBase");
		icons[1] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineBaseTopBottom");
		icons[2] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherStill");
		icons[3] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherActive1");
		icons[4] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherActive2");
		icons[5] = par1IconRegister.registerIcon(TechniumMod.MODID + ":" + "technium_blockMachineCrusherActive3");
	}


	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		TileEntityCrusher tileCrusher = (TileEntityCrusher) blockAccess.getTileEntity(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		
		if (side == 0 || side == 1) {
			return icons[1];
		}
		
		if (side == tileCrusher.getDirection()) {
			return icons[2 + metadata];
		} else { return icons[0]; }
		
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (side == 3) {
			return icons[2];
		}
		
		if (side == 0 || side == 1) {
			return icons[1];
		}
		return icons[0];
	}
}
