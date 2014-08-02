package cultys.technium.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cultys.technium.tileentities.TileEntityTechnium;

public class PacketUpgradeTechnium implements IMessage {

	NBTTagCompound nbtData = new NBTTagCompound();
	
	public PacketUpgradeTechnium() {}
	
	public PacketUpgradeTechnium(int x, int y, int z) {
		nbtData.setInteger("x", x);
		nbtData.setInteger("y", y);
		nbtData.setInteger("z", z);
		
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbtData = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbtData);
	}
	
	public static class Handler implements IMessageHandler<PacketUpgradeTechnium, IMessage> {
        
        @Override
        public IMessage onMessage(PacketUpgradeTechnium message, MessageContext ctx) {
        	World worldObj = Minecraft.getMinecraft().theWorld;
        	int x = message.nbtData.getInteger("x");
        	int y = message.nbtData.getInteger("y");
        	int z = message.nbtData.getInteger("z");
        	TileEntity tileEntity = worldObj.getTileEntity(x,y,z);
        	
        	if (tileEntity != null) {
        		if (tileEntity instanceof TileEntityTechnium) {
        			TileEntityTechnium tileTechnium = (TileEntityTechnium) tileEntity;
        			
        			tileTechnium.setStage(tileTechnium.getStage()+1);
        		}
        	}
        	
            return null;
        }
    }

}
