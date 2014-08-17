package cultys.technium.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;

public class ItemTechniumDust extends Item {
	
	public ItemTechniumDust () {
		super();
		String itemName = TechniumMod.MODID + "_" + "itemDustTechnium";
		
		setUnlocalizedName(itemName);
		setCreativeTab(TechniumMod.techniumTabs);
		setHasSubtypes(true);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World worldObj, EntityPlayer entityPlayer) 
	{
		itemStack.stackTagCompound = new NBTTagCompound();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) 
	{
		if (itemStack.stackTagCompound != null) {
			if (!itemStack.stackTagCompound.getBoolean("hidden")) {
				list.add(EnumChatFormatting.AQUA + "Mineral value: " + itemStack.stackTagCompound.getInteger("mineralValue"));
				
				for (String entry : OreDictionary.getOreNames()) {
					if (entry.substring(0, 3).equals("ore")) {
						if (itemStack.stackTagCompound.hasKey(entry)) {
							list.add(EnumChatFormatting.GREEN + entry.substring(3) + ": " + Math.round(itemStack.stackTagCompound.getFloat(entry) * 100) + "%");
						}
					}
				}
			} else {
				list.add("Unidentified");
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	private IIcon itemIcon;

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{		
		itemIcon = iconRegister.registerIcon(TechniumMod.MODID + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIconFromDamage(int metadata)
    {
        return this.itemIcon;
    }
}
