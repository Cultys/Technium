package cultys.technium.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cultys.technium.blocks.BlockTechnium;
import cultys.technium.blocks.TechniumBlocks;

public class PacketSpawnTechnium implements IMessage {

	NBTTagCompound nbtData = new NBTTagCompound();
	
	public PacketSpawnTechnium() {}
	
	public PacketSpawnTechnium(int x, int y, int z, int xParent, int yParent, int zParent) {
		nbtData.setInteger("x", x);
		nbtData.setInteger("y", y);
		nbtData.setInteger("z", z);
		nbtData.setInteger("xParent", xParent);
		nbtData.setInteger("yParent", yParent);
		nbtData.setInteger("zParent", zParent);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbtData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbtData);
	}
	
	public static class Handler implements IMessageHandler<PacketSpawnTechnium, IMessage> {
        
        @Override
        public IMessage onMessage(PacketSpawnTechnium message, MessageContext ctx) {
        	World worldObj = Minecraft.getMinecraft().theWorld;
        	int x = message.nbtData.getInteger("x");
        	int y = message.nbtData.getInteger("y");
        	int z = message.nbtData.getInteger("z");
        	int xParent = message.nbtData.getInteger("xParent");
        	int yParent = message.nbtData.getInteger("yParent");
        	int zParent = message.nbtData.getInteger("zParent");
        	
            BlockTechnium.placeTechniumAndSetParent(worldObj, TechniumBlocks.blockTechnium, x, y, z, xParent, yParent, zParent, 0);
            return null;
        }
    }

}
