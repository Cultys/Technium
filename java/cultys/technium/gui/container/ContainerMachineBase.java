package cultys.technium.gui.container;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import cultys.technium.tileentities.TileEntityMachineBase;

public abstract class ContainerMachineBase extends Container {

	protected TileEntityMachineBase tileMachine;
	protected int progress;
	protected int energy;
	
	public ContainerMachineBase(InventoryPlayer playerInventory, TileEntityMachineBase tileEntity) {
		
		tileMachine = tileEntity;
		
		/* -- Player Inventory -- */
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
		 
		 tileMachine.openInventory();
	}
		
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileMachine.isUseableByPlayer(player);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer entityplayer)
	{
		super.onContainerClosed(entityplayer);
		tileMachine.closeInventory();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void detectAndSendChanges()
	{
		 super.detectAndSendChanges();
		 
		 Iterator playersUsing = this.crafters.iterator();
		 while (playersUsing.hasNext())
		 {
			 ICrafting craftingInterface = (ICrafting)playersUsing.next();
			 if (this.progress != tileMachine.getProgress())
			 {
				 craftingInterface.sendProgressBarUpdate(this, 0, tileMachine.getProgress());
			 }
			 if (this.energy != tileMachine.getEnergyStored(null))
			 {
				 craftingInterface.sendProgressBarUpdate(this, 1, tileMachine.getEnergyStored(null));
			 }
		 }
		 
		 progress = tileMachine.getProgress();
		 energy = tileMachine.getEnergyStored(null);
	}

	@Override
	public void updateProgressBar(int pointer, int value)
	{
		 if (pointer == 0)
		 {
			 tileMachine.setProgress(value);
		 }
		 if (pointer == 1)
		 {
			 tileMachine.setEnergyStored(value);
		 }
		 
	}
}
