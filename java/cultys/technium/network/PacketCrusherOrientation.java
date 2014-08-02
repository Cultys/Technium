package cultys.technium.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cultys.technium.tileentities.TileEntityCrusher;

public class PacketCrusherOrientation implements IMessage {

	NBTTagCompound nbtData = new NBTTagCompound();
	
	public PacketCrusherOrientation() {}
	
	public PacketCrusherOrientation(int x, int y, int z, int direction) {
		nbtData.setInteger("x", x);
		nbtData.setInteger("y", y);
		nbtData.setInteger("z", z);
		nbtData.setInteger("direciton", direction);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbtData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbtData);
	}
	
	public static class Handler implements IMessageHandler<PacketCrusherOrientation, IMessage> {
        
        @Override
        public IMessage onMessage(PacketCrusherOrientation message, MessageContext ctx) {
        	World worldObj = Minecraft.getMinecraft().theWorld;
        	int x = message.nbtData.getInteger("x");
        	int y = message.nbtData.getInteger("y");
        	int z = message.nbtData.getInteger("z");
        	int direction = message.nbtData.getInteger("direction");
        	TileEntityCrusher tileCrusher = (TileEntityCrusher) worldObj.getTileEntity(x, y, z);
        	
        	if (tileCrusher != null) {
        		tileCrusher.setDirection(direction);
        	}
        	
            return null;
        }
    }

}
