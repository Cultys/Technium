package cultys.technium.tileentities;

import cultys.technium.TechniumMod;
import cultys.technium.recipes.RecipeHandlerCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCrusher extends TileEntity implements IInventory, ISidedInventory {

	//0 input
	//1,2 output	
	private ItemStack inventory[] = new ItemStack[3];

	private boolean isActive = false;
	private boolean isMaster = false;
	private boolean isMultiblock = false;
	private int masterX = 0;
	private int masterY = 0;
	private int masterZ = 0;
	private byte direction; //0 north, 1 east, 2 south, 3 west
	
	private boolean requiresMetadataUpdate = true;

	@SuppressWarnings("unused")
	private static final int maxPower = 2000;
	private static final int ticksPerItem = 200;
	private static final int ticksPerTextureUpdate = 10;
	private static final int textures = 3;
	
	public int power = 2000;
	public int progress = 0;
	private int textureUpdateCounter = 0;
	
	private static void setMultiblock(TileEntityCrusher master, TileEntityCrusher slave) {
		master.isMultiblock = true;		
		master.isMaster = true;
		slave.isMultiblock = true;
		slave.masterX = master.xCoord;
		slave.masterY = master.yCoord;
		slave.masterZ = master.zCoord;
		master.requiresMetadataUpdate = true;
		slave.requiresMetadataUpdate = true;
	}
	
	private void checkForMultiblock (){
		
		TileEntity check = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
		
		if (check != null && check instanceof TileEntityCrusher) {
			TileEntityCrusher topCrusher = (TileEntityCrusher) check;
			
			if (!topCrusher.isMultiblock) {
				setMultiblock(this, topCrusher);
			}
		}		
	}
	
	private void resetCrusher() {
		inventory[0].stackSize--;
		if (inventory[0].stackSize <= 0) {
			inventory[0] = null;
		}
		progress = 0;
	}

	@Override
	public void updateEntity() {
		if (this.isMultiblock) {
			if (this.inventory[0] != null) {
				ItemStack result = RecipeHandlerCrusher.getInstance().getCrushingResult(inventory[0]);
				if (result != null) {
					this.isActive = true;
					progress++;
					if (this.progress >= ticksPerItem) {
						int amount = result.stackSize;
						if (inventory[1] == null) {
							inventory[1] = result.copy();
							this.resetCrusher();
						} else if (result.getItem() == inventory[1].getItem() && inventory[1].stackSize + amount <= this.getInventoryStackLimit()) {
							inventory[1].stackSize = inventory[1].stackSize + amount;
							this.resetCrusher();
						} else if (inventory[2] == null) {
							inventory[2] = result.copy();
							this.resetCrusher();
						} else if (result.getItem() == inventory[2].getItem() && inventory[2].stackSize + amount <= this.getInventoryStackLimit()) {
							inventory[2].stackSize = inventory[2].stackSize + amount;
							this.resetCrusher();
						} 
					}
				} else {
					progress = 0;
					this.isActive = false;
				}
			} else {
				progress = 0;
				this.isActive = false;
			}
			
			textureUpdateCounter++;
			if (this.worldObj.isRemote) {

			}
			if (textureUpdateCounter >= (ticksPerTextureUpdate * textures - 1)) {
				textureUpdateCounter = 0;
			}
		} else { this.checkForMultiblock(); }
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("power", this.power);
		nbt.setInteger("progress", this.progress);
		nbt.setByte("direction", this.direction);
		
		if (this.isMultiblock) {
			nbt.setBoolean("multiblock", true);
			nbt.setBoolean("master", this.isMaster);
			if (!this.isMaster) {
				nbt.setInteger("masterX", this.masterX);
				nbt.setInteger("masterY", this.masterY);
				nbt.setInteger("masterZ", this.masterZ);
			}
		}

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
		this.power = nbt.getInteger("power");
		this.progress = nbt.getInteger("progress");
		this.direction = nbt.getByte("direction");

		if (nbt.hasKey("multiblock")) {
			this.isMultiblock = nbt.getBoolean("multiblock");
			this.isMaster = nbt.getBoolean("master");
			if (!this.isMaster) {
				this.masterX = nbt.getInteger("masterX");
				this.masterY = nbt.getInteger("masterY");
				this.masterZ = nbt.getInteger("masterZ");
			}
		}
		
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
	
	public boolean isMultiblock() {
		return this.isMultiblock;
	}
	
	public boolean isMaster() {
		return this.isMaster;
	}
	
	public int getDirection(){
		return this.direction;
	}

	public int getCrunchProgressScaled(int i) {
		return (this.progress * i) / ticksPerItem;
	}

	public int getTextureId() {
		TechniumMod.writeToConsole("get text id");
		return (this.textureUpdateCounter / ticksPerTextureUpdate);
	}
}
