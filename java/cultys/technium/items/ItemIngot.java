package cultys.technium.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemIngot extends Item {

	public ItemIngot() {
		super();
		String itemName = TechniumMod.MODID + "_" + "itemIngot";
		
		setUnlocalizedName(itemName);
		setCreativeTab(TechniumMod.techniumTabs);
		setHasSubtypes(true);
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

	public static final String[] names = new String[] { "ingotCopper", "ingotTin", "ingotLead" , "ingotSilver" , "ingotTungsten" };

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, names.length - 1);
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
        for (int x = 0; x < names.length; x++)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }
}
