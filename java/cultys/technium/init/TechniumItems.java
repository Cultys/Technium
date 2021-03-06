package cultys.technium.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;
import cultys.technium.items.ItemDust;
import cultys.technium.items.ItemIngot;
import cultys.technium.items.ItemMiningLaser;
import cultys.technium.items.ItemTechnium;
import cultys.technium.items.ItemTechniumDust;
import cultys.technium.items.ItemTechniumShard;


public class TechniumItems {
	
	public static final Item itemTechnium = new ItemTechnium();
	public static final Item itemTechniumShard = new ItemTechniumShard();
	public static final Item itemDust = new ItemDust();
	public static final Item itemIngot = new ItemIngot();
	public static final Item itemTechniumDust = new ItemTechniumDust();
	public static final Item itemMiningLaser = new ItemMiningLaser().setFull3D();
	
	public static void init(){
		GameRegistry.registerItem(itemTechnium, "itemTechnium");
		GameRegistry.registerItem(itemTechniumShard, "itemTechniumShard");
		GameRegistry.registerItem(itemDust, "itemDust");
		GameRegistry.registerItem(itemIngot, "itemIngot");
		GameRegistry.registerItem(itemTechniumDust, "itemTechniumDust");
		GameRegistry.registerItem(itemMiningLaser, "itemMiningLaser");
		
		OreDictionary.registerOre("ingotCopper", new ItemStack(itemIngot, 1, 0));
		OreDictionary.registerOre("ingotTin", new ItemStack(itemIngot, 1, 1));
		OreDictionary.registerOre("ingotLead", new ItemStack(itemIngot, 1, 2));
		OreDictionary.registerOre("ingotSilver", new ItemStack(itemIngot, 1, 3));
		
		OreDictionary.registerOre("dustCopper", new ItemStack(itemDust, 1, 0));
		OreDictionary.registerOre("dustTin", new ItemStack(itemDust, 1, 1));
		OreDictionary.registerOre("dustLead", new ItemStack(itemDust, 1, 2));
		OreDictionary.registerOre("dustSilver", new ItemStack(itemDust, 1, 3));
		
		TechniumMod.writeToConsole("Items initialized.");
	}
}
