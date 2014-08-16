package cultys.technium.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cultys.technium.TechniumMod;

public abstract class TileEntityMachineBase extends TileEntity implements IInventory, ISidedInventory, IEnergyHandler {

	protected EnergyStorage storage;
	protected ItemStack inventory[];
	protected int inventorySize;
	protected int direction;
	protected ResourceLocation guiTexture;
	
	public TileEntityMachineBase (int inventorySize, int maxPower, int maxExtract, int maxRecieve, ResourceLocation guiTextureResource) {
		this.inventorySize = inventorySize;
		guiTexture = guiTextureResource;
		inventory = new ItemStack[inventorySize];
		storage = new EnergyStorage(maxPower, maxExtract, maxRecieve);
	}
	
	public ResourceLocation getGuiTexture() {
		return guiTexture;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("direction", direction);
		nbt.setInteger("inventorySize", inventorySize);
		
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventory.length; ++i)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		nbt.setTag("Items", nbttaglist);
	}
	
	@Override
	public void readFromNBT (NBTTagCompound nbt){
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		direction = nbt.getInteger("direction");
		inventorySize = nbt.getInteger("inventorySize");
		storage.setEnergyStored(storage.getMaxEnergyStored());
		
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.inventory = new ItemStack[inventorySize];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.inventory.length)
			{
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.inventory[slot] != null) {
			ItemStack itemStack;

			if (this.inventory[slot].stackSize <= amount) {
				itemStack = this.inventory[slot];
				this.inventory[slot] = null;
				return itemStack;
			} else {
				itemStack = this.inventory[slot].splitStack(amount);
				if (this.inventory[slot].stackSize == 0) {
					this.inventory[slot] = null;
				}
				return itemStack;
			}

		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.inventory[slot] != null) {
			ItemStack itemStack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		this.inventory[slot] = item;

		if (item != null && item.stackSize > this.getInventoryStackLimit()) {
			item.stackSize = this.getInventoryStackLimit();
		}

	}

	@Override
	public String getInventoryName() {
		return TechniumMod.MODID + ":container.crusher";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return false;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return false;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.getMaxReceive();
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.getMaxExtract();
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}
	
	public void setEnergyStored(int energy) {
		storage.setEnergyStored(energy);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}
	
	public int getPowerScaled(int i) {
		return (storage.getEnergyStored() * i) / storage.getMaxEnergyStored();
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public abstract int getProgress();
	
	public abstract void setProgress(int value);
}
