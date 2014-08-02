package cultys.technium.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeHandlerCrusher
{
    private static final RecipeHandlerCrusher instance = new RecipeHandlerCrusher();
    @SuppressWarnings("rawtypes")
	private Map crushingList = new HashMap();
    @SuppressWarnings("rawtypes")
	private Map sideProductList = new HashMap();
    @SuppressWarnings("rawtypes")
	private Map sideProductChanceList = new HashMap();

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static RecipeHandlerCrusher getInstance()
    {
        return instance;
    }
    
    public void addRecipe(Block block, ItemStack product)
    {
        this.addRecipe(Item.getItemFromBlock(block), product);
    }
    
    public void addRecipe(Item item, ItemStack product)
    {
        this.addRecipe(new ItemStack(item, 1, 32767), product);
    }

    @SuppressWarnings("unchecked")
	public void addRecipe(ItemStack item, ItemStack product)
    {
    	this.crushingList.put(item, product);
    }
    
    public void addSideProduct(Block block, ItemStack sideProduct, float sideProductChance)
    {
        this.addSideProduct(Item.getItemFromBlock(block), sideProduct, sideProductChance);
    }
    
    public void addSideProduct(Item item, ItemStack sideProduct, float sideProductChance)
    {
        this.addSideProduct(new ItemStack(item, 1, 32767), sideProduct, sideProductChance);
    }
    
    @SuppressWarnings("unchecked")
	public void addSideProduct(ItemStack item, ItemStack sideProduct, float sideProductChance)
    {
        this.sideProductList.put(item, sideProduct);
        this.sideProductChanceList.put(item, sideProductChance);
    }

    /**
     * Returns the smelting result of an item.
     */
    @SuppressWarnings("rawtypes")
    public ItemStack getCrushingResult(ItemStack item)
    {
		Iterator iterator = this.crushingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.isEntryItem(item, (ItemStack)entry.getKey()));
        
        return (ItemStack)entry.getValue();
    }

    private boolean isEntryItem(ItemStack item, ItemStack entry)
    {
        return entry.getItem() == item.getItem() && (entry.getItemDamage() == 32767 || entry.getItemDamage() == item.getItemDamage());
    }

    @SuppressWarnings("rawtypes")
    public Map getCrushingList()
    {
        return this.crushingList;
    }

    @SuppressWarnings("rawtypes")
    public ItemStack getCrushingSideProduct(ItemStack item, Random random)
    {

    	Iterator iterator = this.sideProductList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.isEntryItem(item, (ItemStack)entry.getKey()));

        ItemStack result = (ItemStack)entry.getValue();
        iterator = this.sideProductChanceList.entrySet().iterator();
        float chance;
        
        do
        {
            if (!iterator.hasNext())
            {
                chance = 1.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.isEntryItem(item, (ItemStack)entry.getKey()));
        
        chance = ((Float) entry.getValue()).floatValue();
        
        if (random.nextFloat() < chance) {
        	return result;
        } else {
        	return null;
        }
    }
}