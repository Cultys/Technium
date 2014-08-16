package cultys.technium.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cultys.technium.recipes.RecipeHandlerCrusher;
import cultys.technium.tileentities.TileEntityMachineBase;

public class ContainerCrusher extends ContainerMachineBase {

	public ContainerCrusher(InventoryPlayer playerInventory, TileEntityMachineBase tileEntity) {
		
		super(playerInventory, tileEntity);
		
		addSlotToContainer(new Slot(tileEntity, 0, 44, 35));
		addSlotToContainer(new SlotResult(tileEntity, 1, 101,35));
		addSlotToContainer(new SlotResult(tileEntity, 2, 119,35));
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

				 slot.onSlotChange(itemstack1, itemstack);
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
