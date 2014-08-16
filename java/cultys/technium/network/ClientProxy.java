package cultys.technium.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cultys.technium.gui.GuiCrusher;
import cultys.technium.gui.container.ContainerCrusher;
import cultys.technium.renderers.RenderTechnium;
import cultys.technium.renderers.RenderTechniumSource;
import cultys.technium.tileentities.TileEntityCrusher;
import cultys.technium.tileentities.TileEntityTechnium;
import cultys.technium.tileentities.TileEntityTechniumSource;

public class ClientProxy extends CommonProxy {
	
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		 return Minecraft.getMinecraft().thePlayer;
	}
	
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null) {
			switch(ID) {
				case 0:
					return new GuiCrusher(new ContainerCrusher(player.inventory, (TileEntityCrusher) tileEntity), (TileEntityCrusher) tileEntity);
			}
		}
		
		return null;
	}
	
	public void registerRenders(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTechnium.class, new RenderTechnium());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTechniumSource.class, new RenderTechniumSource());
	}
	
	@Override
	public EntityPlayer getPlayer(MessageContext context)
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			return context.getServerHandler().playerEntity;
		}
		else {
			return Minecraft.getMinecraft().thePlayer;
		}
	}
}
