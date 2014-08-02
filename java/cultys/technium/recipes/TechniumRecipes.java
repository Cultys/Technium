package cultys.technium.recipes;

import cultys.technium.blocks.TechniumBlocks;
import cultys.technium.items.TechniumItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TechniumRecipes {

	public static void init () {
		RecipeHandlerCrusher.getInstance().addRecipe(TechniumBlocks.blockCopperOre, new ItemStack(TechniumItems.itemDust, 2, 0));
		RecipeHandlerCrusher.getInstance().addRecipe(TechniumBlocks.blockTinOre, new ItemStack(TechniumItems.itemDust, 2, 1));
		RecipeHandlerCrusher.getInstance().addRecipe(TechniumBlocks.blockSilverOre, new ItemStack(TechniumItems.itemDust, 2, 3));
		RecipeHandlerCrusher.getInstance().addRecipe(TechniumBlocks.blockLeadOre, new ItemStack(TechniumItems.itemDust, 2, 2));
		RecipeHandlerCrusher.getInstance().addRecipe(Blocks.iron_ore, new ItemStack(TechniumItems.itemDust, 2, 4));
		RecipeHandlerCrusher.getInstance().addRecipe(Blocks.gold_ore, new ItemStack(TechniumItems.itemDust, 2, 5));		
	}
}
