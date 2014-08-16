package cultys.technium.tileentities;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.blocks.BlockTechnium;
import cultys.technium.init.TechniumBlocks;
import cultys.technium.init.TechniumRef;
import cultys.technium.network.PacketDispatcher;
import cultys.technium.network.PacketSpawnTechnium;

public class TileEntityTechniumSource extends TileEntity {

	private Random rng = new Random();
	private int ticks;
	
	private static final short redBase = 50;
	private static final short greenBase = 150;
	private static final short blueBase = 50;
	
	public float red;
	public float green;
	public float blue;
	public float rotation = (float) Math.PI * (rng.nextFloat() - rng.nextFloat());
	
	private int children = 0;
	private Map<String, Float> mineralComposition = new HashMap<String, Float>();
	private int mineralValue = 0;
	
	@SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 64516.0D;
    }
	
	@Override
	public void updateEntity() {
		ticks++;
		
		if (ticks > TechniumRef.techniumSourceSproutTicks) {
			if (rng.nextFloat() <= TechniumRef.techniumSourceSproutChance && this.children < TechniumRef.techniumSourceMaxChildren) {
				if (!this.worldObj.isRemote) {
					if (this.sprout()) {
						children++;
					}
				}
			}
			
			ticks = 0;
		}
	}
	
	public boolean sprout(){
		Block block = TechniumBlocks.blockTechnium;
		
		for (int i = 0; i < TechniumRef.techniumSourceSproutAttempts; i++) {
			int dx = this.xCoord + (int) (TechniumRef.techniumSourceSproutRadius * (rng.nextFloat() - rng.nextFloat()));
			int dz = this.zCoord + (int) (TechniumRef.techniumSourceSproutRadius * (rng.nextFloat() - rng.nextFloat()));
			
			//Lets check if we can get it on y = yParent
			if (this.worldObj.getBlock(dx, this.yCoord, dz) == Blocks.tallgrass && BlockTechnium.canSustainTechnium(this.worldObj, dx, this.yCoord, dz)) {
				PacketDispatcher.sendToDimension(new PacketSpawnTechnium(dx, this.yCoord, dz, this.xCoord, this.yCoord, this.zCoord), worldObj.provider.dimensionId);
				return BlockTechnium.placeTechniumAndSetParent(this.worldObj, block, dx, this.yCoord, dz, this.xCoord, this.yCoord, this.zCoord, 0);
			}
			if (this.worldObj.getBlock(dx, this.yCoord, dz) == Blocks.air && BlockTechnium.canSustainTechnium(this.worldObj, dx, this.yCoord, dz)) {
				PacketDispatcher.sendToDimension(new PacketSpawnTechnium(dx, this.yCoord, dz, this.xCoord, this.yCoord, this.zCoord), worldObj.provider.dimensionId);
				return BlockTechnium.placeTechniumAndSetParent(this.worldObj, block, dx, this.yCoord, dz, this.xCoord, this.yCoord, this.zCoord, 0);
			}
			
			//y = yParent isn't a suitable location!
			for (int y = this.yCoord + TechniumRef.techniumSourceSproutDY; y > this.yCoord - TechniumRef.techniumSourceSproutDY; y--) {
				if (y != this.yCoord) {
					if (this.worldObj.getBlock(dx, this.yCoord, dz) == Blocks.tallgrass && BlockTechnium.canSustainTechnium(this.worldObj, dx, y, dz)) {
						PacketDispatcher.sendToDimension(new PacketSpawnTechnium(dx, y, dz, this.xCoord, this.yCoord, this.zCoord), worldObj.provider.dimensionId);
						return BlockTechnium.placeTechniumAndSetParent(this.worldObj, block, dx, y, dz, this.xCoord, this.yCoord, this.zCoord, 0);
					}
					
					//Is block above target air?
					if (this.worldObj.getBlock(dx, y+1, dz) == Blocks.air && BlockTechnium.canSustainTechnium(this.worldObj, dx, y+1, dz)) {
						PacketDispatcher.sendToDimension(new PacketSpawnTechnium(dx, y+1, dz, this.xCoord, this.yCoord, this.zCoord), worldObj.provider.dimensionId);
						return BlockTechnium.placeTechniumAndSetParent(this.worldObj, block, dx, y+1, dz, this.xCoord, this.yCoord, this.zCoord, 0);
					}
				}
			}
		}
		
		return false;
	}
	
	public void calculateColors() {
		short red = redBase;
		short green = greenBase;
		short blue = blueBase;
		
		for (Map.Entry<String, Float> entry : mineralComposition.entrySet()){
			if (TechniumRef.mineralRedValue.containsKey(entry.getKey())) {
				red = (short) (red + entry.getValue() * TechniumRef.techniumMaxColoring * (TechniumRef.mineralRedValue.get(entry.getKey())/255));	
			}
			if (TechniumRef.mineralGreenValue.containsKey(entry.getKey())) {
				green = (short) (green + entry.getValue() * TechniumRef.techniumMaxColoring * (TechniumRef.mineralGreenValue.get(entry.getKey())/255));	
			}
			if (TechniumRef.mineralBlueValue.containsKey(entry.getKey())) {
				blue = (short) (blue + entry.getValue() * TechniumRef.techniumMaxColoring * (TechniumRef.mineralBlueValue.get(entry.getKey())/255));	
			}
		}
		
		this.red = (float) red/255;
		this.green = (float) green/255;
		this.blue = (float) blue/255;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		rotation = nbt.getFloat("rotation");
		children = nbt.getInteger("children");
		mineralValue = nbt.getInteger("mineralValue");
		
		for (String entry : OreDictionary.getOreNames()) {
			if (entry.substring(0, 3).equals("ore")) {
				if (nbt.hasKey(entry)) {
					this.mineralComposition.put(entry, nbt.getFloat(entry));
				}
			}
		}
		
		calculateColors();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setFloat("rotation", rotation);
		nbt.setInteger("children", children);
		nbt.setInteger("mineralValue", mineralValue);
		
		for (Map.Entry<String, Float> entry : mineralComposition.entrySet()){
		    nbt.setFloat(entry.getKey(), entry.getValue());
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

	public void shatter(World worldObj, int x, int y, int z) {
		worldObj.setBlock(x, y, z, Blocks.air);
		worldObj.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "dig.glass", 1.0F, 0.7F + 0.1F * (rng.nextFloat() - rng.nextFloat()));
	}

	public void generateMineralComposition() {
		String key;
		int[] oreids;
		Block block;
		for (int x =  this.xCoord - (int) TechniumRef.techniumSourceSproutRadius; x < this.xCoord + (int)TechniumRef.techniumSourceSproutRadius; x++) {
			for (int z = this.zCoord - (int) TechniumRef.techniumSourceSproutRadius; z < this.zCoord + (int)TechniumRef.techniumSourceSproutRadius; z++) {
				for (int y = 0; y < this.yCoord; y++) {
					block = this.worldObj.getBlock(x, y, z);
					
					if (!(block instanceof BlockContainer)) {
						oreids = OreDictionary.getOreIDs(new ItemStack(block));

						if (oreids.length > 0) {
							key = OreDictionary.getOreName(oreids[0]);
							if (key.substring(0, 3).equals("ore")) {
								if (mineralComposition.containsKey(key)){
									mineralComposition.put(key, mineralComposition.get(key) + 1F);
								} else {
									mineralComposition.put(key,  1F);
								}
								mineralValue++;
							}
						}
					}
				}
			}
		}
		TechniumRef.mineralCompositionRefractor(mineralComposition);
		calculateColors();
	}
	
	public void outputComposition() {
		for (Map.Entry<String, Float> entry : this.mineralComposition.entrySet()){
		    TechniumMod.writeToConsole(entry.getKey() + ": " + Math.round(entry.getValue()*100) + "%");
		}
	}

	public Map<String, Float> getMineralComposition() {
		return mineralComposition;
	}

	public void removeChild() {
		children--;
		
	}

	public int getMineralValue() {
		return mineralValue;
	}
}
