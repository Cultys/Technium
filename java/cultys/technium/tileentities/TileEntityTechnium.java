package cultys.technium.tileentities;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumUtils;
import cultys.technium.init.TechniumRef;
import cultys.technium.network.PacketDispatcher;
import cultys.technium.network.PacketUpgradeTechnium;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityTechnium extends TileEntity {

	private Random rng = new Random();
	private int ticks;
	
	private static final short redBase = 50;
	private static final short greenBase = 150;
	private static final short blueBase = 50;
	
	public float red;
	public float green;
	public float blue;
	public float rotation = (float) Math.PI * (rng.nextFloat() - rng.nextFloat());
	
	public int xParent;
	public int yParent;
	public int zParent;
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
		
		if (ticks > TechniumRef.techniumGrowTicks) {
			if (!worldObj.isRemote){
				if (rng.nextFloat() <= TechniumRef.techniumGrowChance && getStage() < 3) {
					PacketDispatcher.sendToDimension(new PacketUpgradeTechnium(xCoord, yCoord, zCoord), worldObj.provider.dimensionId);
					setStage(getStage() + 1);
				}
				
				if (rng.nextFloat() <= TechniumRef.techniumCorruptChance) {
					TechniumUtils.corruptBlockAt(worldObj, xCoord, yCoord-1, zCoord);
				}
			}
			ticks = 0;
		}
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
	
	public void writeItemNBT (NBTTagCompound nbt) {
		nbt.setBoolean("hidden", false);
		nbt.setInteger("mineralValue", mineralValue);
		
		
		for (Map.Entry<String, Float> entry : mineralComposition.entrySet()){
			nbt.setFloat(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		rotation = nbt.getFloat("rotation");
		xParent = nbt.getInteger("xParent");
		yParent = nbt.getInteger("yParent");
		zParent = nbt.getInteger("zParent");
		mineralValue = nbt.getInteger("mineralValue");
		
		for (String entry : OreDictionary.getOreNames()) {
			if (entry.substring(0, 3).equals("ore")) {
				if (nbt.hasKey(entry)) {
					mineralComposition.put(entry, nbt.getFloat(entry));
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
		nbt.setInteger("xParent", xParent);
		nbt.setInteger("yParent", yParent);
		nbt.setInteger("zParent", zParent);
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
	
	public void setParent(int x, int y, int z) {
		this.xParent = x;
		this.yParent = y;
		this.zParent = z;
		
		TileEntityTechniumSource tileSource = (TileEntityTechniumSource) this.worldObj.getTileEntity(x, y, z);
		if (tileSource != null) {
			mineralComposition = tileSource.getMineralComposition();
			mineralValue = tileSource.getMineralValue();
		} else {
			mineralComposition = TechniumRef.defaultMineralComposition;
			mineralValue = TechniumRef.defaultMineralValue;
		}
	}
	
	public void setStage(int stage) {
		this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, stage, 3);
	}
	
	public int getStage() {
		return this.getBlockMetadata();
	}

	public void shatter(World worldObj) {
		this.removeTechnium(worldObj, xCoord, yCoord, zCoord);
		worldObj.playSoundEffect(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "dig.glass", 1.0F, 0.7F + 0.1F * (rng.nextFloat() - rng.nextFloat()));
	}
	
	public void removeTechnium (World worldObj, int x, int y, int z) {
		TileEntityTechniumSource tileSource;
		TileEntity tileEntity = worldObj.getTileEntity(xParent, yParent, zParent);
		if (tileEntity != null) {
			if (tileEntity instanceof TileEntityTechniumSource) {
				tileSource = (TileEntityTechniumSource) tileEntity;
				tileSource.removeChild();
			}
		}
		worldObj.setBlock(x, y, z, Blocks.air);
		worldObj.notifyBlocksOfNeighborChange(x, y, z, Blocks.air);
	}
}
