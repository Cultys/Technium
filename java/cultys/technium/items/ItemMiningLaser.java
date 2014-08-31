package cultys.technium.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.network.PacketDispatcher;
import cultys.technium.network.PacketMiningLaserData;

public class ItemMiningLaser extends Item 
{
	
	public ItemMiningLaser ()
	{
		super();
		String itemName = TechniumMod.MODID + "_" + "itemMiningLaser";
		setHarvestLevel("pickaxe", 3);
		setUnlocalizedName(itemName);
		setCreativeTab(TechniumMod.techniumTabs);
		setHasSubtypes(true);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack item, World worldObj, EntityPlayer player, int par5)
    {
		resetDigging(item);
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
    {
        return 72000;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.bow;
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World worldObj, EntityPlayer player)
    {
		NBTTagCompound data = item.stackTagCompound;

		if (data != null)
		{
			int x = data.getInteger("blockX");
			int y = data.getInteger("blockY");
			int z = data.getInteger("blockZ");
			int hitX = data.getInteger("hitX");
			int hitY = data.getInteger("hitY");
			int hitZ = data.getInteger("hitZ");
			
			if (worldObj.isRemote)
			{
				MovingObjectPosition object = player.rayTrace(200, 1.0F);
				
				if (object.typeOfHit == MovingObjectType.BLOCK)
				{
					Vec3 hit = object.hitVec;
					
					if (x != object.blockX || y != object.blockY || z != object.blockZ || hitX != hit.xCoord || hitY != hit.yCoord || hitZ != hit.zCoord)
					{
						item.stackTagCompound.setInteger("blockX", object.blockX);
						item.stackTagCompound.setInteger("blockY", object.blockY);
						item.stackTagCompound.setInteger("blockZ", object.blockZ);
						item.stackTagCompound.setBoolean("isActive", true);
						
						PacketDispatcher.sendToServer(new PacketMiningLaserData(object.blockX, object.blockY, object.blockZ, hit.xCoord, hit.yCoord, hit.zCoord));
					}
				}
			}
			
			if (x != data.getInteger("blockX") || y != data.getInteger("blockY") || z != data.getInteger("blockZ"))
			{
				worldObj.destroyBlockInWorldPartially(player.getEntityId(), x, y, z, -1);
				resetDigging(item);
			}
			
			if (data.getBoolean("isActive"))
			{

				player.setItemInUse(item, getMaxItemUseDuration(item));

				//Block block = worldObj.getBlock(x, y, z);
				data.setFloat("length", (float) Math.sqrt(Math.pow(player.posX - hitX, 2) + Math.pow(player.posY - hitY, 2) + Math.pow(player.posZ - hitZ, 2)));
				
				int ticks = data.getInteger("ticks") + 1;
	            float factor = ticks * 0.10F;//block.getPlayerRelativeBlockHardness(player, worldObj, x, y, z);
	            int damage = (int) (factor * 10F);
	            
	            data.setInteger("ticks", ticks);
				worldObj.destroyBlockInWorldPartially(player.getEntityId(), x, y, z, damage);
				
				if (factor >=  1.0F && !worldObj.isRemote) 
				{
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					playerMP.theItemInWorldManager.tryHarvestBlock(x, y, z);
					resetDigging(item);
				}
				
				if (worldObj.getBlock(x, y, z).getMaterial() == Material.air) 
				{
					resetDigging(item);
				}
			}
		}
		else
		{
			item.stackTagCompound = new NBTTagCompound();
		}
		return item;
    }
	
	public static void resetDigging(ItemStack item) 
	{
		item.stackTagCompound.setBoolean("isActive", false);
		item.stackTagCompound.setInteger("ticks", 0);
	}
	
	@Override
	public void onCreated(ItemStack item, World worldObj, EntityPlayer entityPlayer) 
	{
		item.stackTagCompound = new NBTTagCompound();
		resetDigging(item);
	}

	public static final String[] names = new String[] { "miningLaser" };

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, names.length - 1);
		return super.getUnlocalizedName() + "." + names[i];
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] itemIcons;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcons = new IIcon[names.length];

		for (int i = 0; i < itemIcons.length; i++)
		{
			itemIcons[i] = iconRegister.registerIcon(TechniumMod.MODID + ":" + (this.getUnlocalizedName().substring(5, 13)) + "_" + names[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int metadata)
	{
		return itemIcons[metadata];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamageForRenderPass(int metadata, int renderPass)
	{
		return renderPass < 0 ? this.itemIcons[metadata] : null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < names.length; x++)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }
}
