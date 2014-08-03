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
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cultys.technium.TechniumMod;
import cultys.technium.init.TechniumItems;
import cultys.technium.recipes.RecipeHandlerCrusher;

public class TileEntityCrusher extends TileEntity implements IInventory, ISidedInventory, IEnergyHandler {

	/*-------  GLOBALS -------*/
	private static final int maxPower = 2000;
	private static final int maxExtract = 0;
	private static final int maxRecieve = 20;
	private static final int ticksPerItem = 200;
	private static final int ticksPerTextureUpdate = 5;
	private static final int textures = 3;
	
	/*------- VARIABLES ------*/
	private ItemStack inventory[] = new ItemStack[3]; /* 0 INPUT; 1,2 OUTPUT*/
	private EnergyStorage storage = new EnergyStorage(maxPower, maxExtract, maxRecieve);

	private boolean isActive = false;
	private int direction;
	
	private int power = 2000;
	public int progress = 0;
	private int textureUpdateCounter = 0;
	private int currentActiveTexture;
	
	/*--------- CODE ---------*/
	private void resetCrusher() {
		inventory[0].stackSize--;
		if (inventory[0].stackSize <= 0) {
			inventory[0] = null;
		}
		progress = 0;
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (inventory[0] != null) {
				ItemStack result = RecipeHandlerCrusher.getInstance().getCrushingResult(inventory[0]);
				if (result != null) {
					isActive = true;
					progress++;
					
					if (this.progress >= ticksPerItem) {
						int amount = result.stackSize;
						if (inventory[1] == null) {
							inventory[1] = result.copy();
							if (inventory[0].getItem() == TechniumItems.itemTechniumShard) {
								inventory[1].setTagCompound(inventory[0].getTagCompound());
							}
							resetCrusher();
						} else if (inventory[0].getItem() != TechniumItems.itemTechniumShard) {
							if (result.getItem() == inventory[1].getItem() && inventory[1].stackSize + amount <= getInventoryStackLimit()) {
								inventory[1].stackSize = inventory[1].stackSize + amount;
								resetCrusher();
							}
						} else if (inventory[2] == null) {
							inventory[2] = result.copy();
							if (inventory[0].getItem() == TechniumItems.itemTechniumShard) {
								inventory[2].setTagCompound(inventory[0].getTagCompound());
							}
							resetCrusher();
						} else if (inventory[0].getItem() != TechniumItems.itemTechniumShard) {
							if (result.getItem() == inventory[2].getItem() && inventory[2].stackSize + amount <= getInventoryStackLimit()) {
								inventory[2].stackSize = inventory[2].stackSize + amount;
								resetCrusher();
							}
						} else if (inventory[0].getItem() == TechniumItems.itemTechniumShard) {
							if (result.getItem() == inventory[1].getItem() && inventory[1].stackSize + amount <= getInventoryStackLimit() && inventory[1].getTagCompound() == inventory[0].getTagCompound()) {
								inventory[1].stackSize = inventory[1].stackSize + amount;
								resetCrusher();
							}
							if (result.getItem() == inventory[2].getItem() && inventory[2].stackSize + amount <= getInventoryStackLimit() && inventory[2].getTagCompound() == inventory[0].getTagCompound()) {
								inventory[2].stackSize = inventory[2].stackSize + amount;
								resetCrusher();
							}
						}
					}
				} else {
					progress = 0;
					isActive = false;
				}
			} else {
				progress = 0;
				isActive = false;
			}
			
			if (isActive){
				textureUpdateCounter++;
				if (textureUpdateCounter >= ticksPerTextureUpdate) {
					currentActiveTexture++;
					if (currentActiveTexture > textures) {
						currentActiveTexture = 1;
					}
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, currentActiveTexture, 2);
					textureUpdateCounter = 0;
				}
			} else if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) != 0) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("power", this.power);
		nbt.setInteger("progress", this.progress);
		nbt.setInteger("direction", this.direction);

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
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		this.power = nbt.getInteger("power");
		this.progress = nbt.getInteger("progress");
		this.direction = nbt.getInteger("direction");
		this.storage.setEnergyStored(maxPower);
		
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

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

	public boolean canCrush (ItemStack item) {
		return (RecipeHandlerCrusher.getInstance().getCrushingResult(item) != null);
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		if (slot == 0) {
			return canCrush(item);
		}
		return false;
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

	public boolean isActive (){
		return this.isActive;
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}

	public int getCrunchProgressScaled(int i) {
		return (this.progress * i) / ticksPerItem;
	}

	public int getTextureId() {
		return this.currentActiveTexture;
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
		return storage.getMaxEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	public int getPowerScaled(int i) {
		return (storage.getEnergyStored() * i) / storage.getMaxEnergyStored();
	}
}
