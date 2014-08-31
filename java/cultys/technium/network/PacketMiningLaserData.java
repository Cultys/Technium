package cultys.technium.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketMiningLaserData implements IMessage {

	NBTTagCompound nbtData = new NBTTagCompound();
	
	public PacketMiningLaserData() {}
	
	public PacketMiningLaserData(int x, int y, int z, double hitX, double hitY, double hitZ) {
		nbtData.setInteger("x", x);
		nbtData.setInteger("y", y);
		nbtData.setInteger("z", z);
		nbtData.setDouble("hitX", hitX);
		nbtData.setDouble("hitY", hitY);
		nbtData.setDouble("hitZ", hitZ);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbtData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbtData);
	}
	
	public static class Handler implements IMessageHandler<PacketMiningLaserData, IMessage> {
        
        @Override
        public IMessage onMessage(PacketMiningLaserData message, MessageContext ctx) {
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	ItemStack item = player.getCurrentEquippedItem();

        	item.stackTagCompound.setInteger("blockX", message.nbtData.getInteger("x"));
        	item.stackTagCompound.setInteger("blockY", message.nbtData.getInteger("y"));
        	item.stackTagCompound.setInteger("blockZ", message.nbtData.getInteger("z"));
        	
        	item.stackTagCompound.setDouble("hitX", message.nbtData.getDouble("hitX"));
        	item.stackTagCompound.setDouble("hitY", message.nbtData.getDouble("hitY"));
        	item.stackTagCompound.setDouble("hitZ", message.nbtData.getDouble("hitZ"));
        	
        	item.stackTagCompound.setBoolean("isActive", true);

			return null;
        }
    }

}
