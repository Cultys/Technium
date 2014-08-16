package cultys.technium.tileentities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import cultys.technium.TechniumMod;
import cultys.technium.init.TechniumItems;
import cultys.technium.recipes.RecipeHandlerCrusher;

public class TileEntityCrusher extends TileEntityMachineBase {

	/*-------  GLOBALS -------*/
	private static final int rfPerTick = 10;
	private static final int ticksPerItem = 200;
	private static final int ticksPerTextureUpdate = 5;
	private static final int blockAnimationTextures = 3;
	private static final String guiTexturePath = TechniumMod.MODID + ":" + "textures/gui/technium_guiCrusher.png";
	
	/*------- VARIABLES ------*/
	private boolean isActive = false;
	
	public int progress = 0;
	private int textureUpdateCounter = 0;
	private int currentActiveTexture;
	
	/*--------- CODE ---------*/
	public TileEntityCrusher (){
		super(3, 2000, 0, 20, new ResourceLocation(guiTexturePath));
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
		super.updateEntity();
		
		if (!this.worldObj.isRemote) {
			if (inventory[0] != null) {
				ItemStack result = RecipeHandlerCrusher.getInstance().getCrushingResult(inventory[0]);
				if (result != null) {
					if (storage.getEnergyStored() >= rfPerTick) {
						storage.modifyEnergyStored(-rfPerTick);
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
					} else { isActive = false; }
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
					if (currentActiveTexture > blockAnimationTextures) {
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
		nbt.setInteger("progress", this.progress);
	}


	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.progress = nbt.getInteger("progress");
	}
	
	public boolean canCrush (ItemStack item) {
		return (RecipeHandlerCrusher.getInstance().getCrushingResult(item) != null);
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		if (slot == 0) {
			return canCrush(item);
		}
		return false;
	}

	public boolean isActive (){
		return this.isActive;
	}
	
	public int getCrunchProgressScaled(int i) {
		return (this.progress * i) / ticksPerItem;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int value){
		progress = value;
	}

	public int getBlockTextureId() {
		if (this.isActive) {
			return this.currentActiveTexture;
		} else { return 0; }
	}
}
