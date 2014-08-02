package cultys.technium.gui.container;

import java.util.Iterator;

import cultys.technium.recipes.RecipeHandlerCrusher;
import cultys.technium.tileentities.TileEntityCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrusher extends Container {

	private TileEntityCrusher tileCrusher;
	private int crusherTime;
	
	public ContainerCrusher(InventoryPlayer playerInventory, TileEntityCrusher tileCrusher) {
		
		this.tileCrusher = tileCrusher;
		this.crusherTime = 0;
		
		addSlotToContainer(new Slot(tileCrusher, 0, 44, 35));
		addSlotToContainer(new SlotResult(tileCrusher, 1, 101,35));
		addSlotToContainer(new SlotResult(tileCrusher, 2, 119,35));
		
		for (int i = 0; i < 3; i++)
		 {
			 for (int k = 0; k < 9; k++)
			 {
				 addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			 }
		 }

		 for (int j = 0; j < 9; j++)
		 {
			 addSlotToContainer(new Slot(playerInventory, j, 8 + j * 18, 142));
		 }
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileCrusher.isUseableByPlayer(player);
	}
	
	@SuppressWarnings("rawtypes")
	public void detectAndSendChanges()
	{
		 super.detectAndSendChanges();
		 Iterator var1 = this.crafters.iterator();
		 while (var1.hasNext())
		 {
			 ICrafting var2 = (ICrafting)var1.next();
			 if (this.crusherTime != this.tileCrusher.progress)
			 {
				 var2.sendProgressBarUpdate(this, 0, this.tileCrusher.progress);
			 }
		 }
		 
		 this.crusherTime = this.tileCrusher.progress;
	}

	public void updateProgressBar(int par1, int par2)
	{
		 if (par1 == 0)
		 {
			 this.tileCrusher.progress = par2;
		 }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotnumber){
		 ItemStack itemstack = null;
		 Slot slot = (Slot)inventorySlots.get(slotnumber);
		 
		 if (slot != null && slot.getHasStack())
		 {
			 ItemStack itemstack1 = slot.getStack();
			 itemstack = itemstack1.copy();

			 if (slotnumber == 1 || slotnumber == 2)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, true))
				 {
					 return null;
				 }

				 //slot.onSlotChange(itemstack1, itemstack);
			 }
			 else if (slotnumber == 0)
			 {
				 if (!mergeItemStack(itemstack1, 3, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (RecipeHandlerCrusher.getInstance().getCrushingResult(itemstack1) != null)
			 {
				 if (!mergeItemStack(itemstack1, 0, 0, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 3 && slotnumber < 30)
			 {
				 if (!mergeItemStack(itemstack1, 30, 39, false))
				 {
					 return null;
				 }
			 }
			 else if (slotnumber >= 30 && slotnumber < 39 && !mergeItemStack(itemstack1, 3, 30, false))
			 {
				 return null;
			 }

			 if (itemstack1.stackSize == 0)
			 {
				 slot.putStack(null);
			 }
			 else
			 {
				 slot.onSlotChanged();
			 }

			 if (itemstack1.stackSize == itemstack.stackSize)
			 {
				 return null;
			 }
			
			 slot.onPickupFromSlot(player, itemstack);
		 }

		 return itemstack;
	}

}
