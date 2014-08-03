package cultys.technium.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.blocks.BlockTechnium;
import cultys.technium.init.TechniumBlocks;

public class ItemTechnium extends Item {
	
	public ItemTechnium () {
		super();
		String itemName = TechniumMod.MODID + "_" + "itemTechnium";
		
		setUnlocalizedName(itemName);
		setCreativeTab(TechniumMod.techniumTabs);
		setHasSubtypes(true);
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World worldObj, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        
		Block block = TechniumBlocks.blockTechnium;

		//Is target replaceable?
		if (worldObj.getBlock(x, y, z).isReplaceable(worldObj, x, y, z)) {
			return BlockTechnium.placeTechnium(worldObj, block, x, y, z, itemStack.getItemDamage());
		}
		
		//Is block above target replaceable?
		if (worldObj.getBlock(x, y, z).isReplaceable(worldObj, x, y+1, z) && side == 1) {
			return BlockTechnium.placeTechnium(worldObj, block, x, y+1, z, itemStack.getItemDamage());
		}
		
		//Is block above target air?
		if (worldObj.getBlock(x, y+1, z) == Blocks.air && side == 1) {
			return BlockTechnium.placeTechnium(worldObj, block, x, y+1, z, itemStack.getItemDamage());
		}
		
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcons = new IIcon[4];

		for (int i = 0; i < itemIcons.length; i++)
		{
			itemIcons[i] = iconRegister.registerIcon(TechniumMod.MODID + ":" + (this.getUnlocalizedName().substring(5)) + "_" + i);
		}
	}

	public static final String[] names = new String[] { "stage1", "stage2", "stage3" , "stage4" };

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 3);
		return super.getUnlocalizedName() + "." + names[i];
	}

	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return itemIcons[par1];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < 4; x++)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }
}
