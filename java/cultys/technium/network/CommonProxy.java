package cultys.technium.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cultys.technium.gui.container.ContainerCrusher;
import cultys.technium.tileentities.TileEntityCrusher;

public class CommonProxy implements IGuiHandler{

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
	
	public void registerRenders(){
		
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null) {
			switch(ID) {
				case 0:
					return new ContainerCrusher(player.inventory, (TileEntityCrusher) tileEntity);
			}
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}
