package cultys.technium.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cultys.technium.recipes.RecipeHandlerCrusher;

public class TechniumRecipes {

	public static void init () {
		/* ORE TO DUST */
		for (ItemStack item : OreDictionary.getOres("oreCopper")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 2, 0));
		}
		for (ItemStack item : OreDictionary.getOres("oreTin")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 2, 1));
		}
		for (ItemStack item : OreDictionary.getOres("oreLead")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 2, 2));
		}
		for (ItemStack item : OreDictionary.getOres("oreSilver")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 2, 3));
		}
		RecipeHandlerCrusher.getInstance().addRecipe(Blocks.iron_ore, new ItemStack(TechniumItems.itemDust, 2, 4));
		RecipeHandlerCrusher.getInstance().addRecipe(Blocks.gold_ore, new ItemStack(TechniumItems.itemDust, 2, 5));
		
		/* INGOTS TO DUST */
		for (ItemStack item : OreDictionary.getOres("ingotCopper")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 1, 0));
		}
		for (ItemStack item : OreDictionary.getOres("ingotTin")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 1, 1));
		}
		for (ItemStack item : OreDictionary.getOres("ingotLead")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 1, 2));
		}
		for (ItemStack item : OreDictionary.getOres("ingotSilver")) {
			RecipeHandlerCrusher.getInstance().addRecipe(item.copy(), new ItemStack(TechniumItems.itemDust, 1, 3));
		}
		RecipeHandlerCrusher.getInstance().addRecipe(new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(TechniumItems.itemDust, 1, 4));
		RecipeHandlerCrusher.getInstance().addRecipe(new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(TechniumItems.itemDust, 1, 5));
		
		/* DUST TO INGOTS */
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 0), new ItemStack(TechniumItems.itemIngot, 1, 0), 0F);
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 1), new ItemStack(TechniumItems.itemIngot, 1, 1), 0F);
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 2), new ItemStack(TechniumItems.itemIngot, 1, 2), 0F);
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 3), new ItemStack(TechniumItems.itemIngot, 1, 3), 0F);
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 4), new ItemStack(Items.iron_ingot, 1, 0), 0F);
		GameRegistry.addSmelting(new ItemStack(TechniumItems.itemDust, 1, 5), new ItemStack(Items.gold_ingot, 1, 0), 0F);
	}
}
