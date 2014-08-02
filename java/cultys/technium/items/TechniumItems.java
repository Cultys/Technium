package cultys.technium.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.TechniumMod;


public class TechniumItems {
	
	public static Item itemTechnium;
	public static Item itemTechniumShard;
	public static Item itemDust;
	public static Item itemIngot;
	
	public static void init(){
		itemTechnium = new ItemTechnium();
		itemTechniumShard = new ItemTechniumShard();
		itemDust = new ItemDust();
		itemIngot = new ItemIngot();
		
		GameRegistry.registerItem(itemTechnium, "itemTechnium");
		GameRegistry.registerItem(itemTechniumShard, "itemTechniumShard");
		GameRegistry.registerItem(itemDust, "itemDust");
		GameRegistry.registerItem(itemIngot, "itemIngot");
		
		
		TechniumMod.writeToConsole("Items initialized.");
	}
}
