package cultys.technium.gui;

import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.tileentities.TileEntityCrusher;
import cultys.technium.tileentities.TileEntityMachineBase;

@SideOnly(Side.CLIENT)
public class GuiCrusher extends GuiMachineBase {

	private TileEntityMachineBase tileMachine;
	private ResourceLocation resource;
	
	public GuiCrusher(Container inventory, TileEntityMachineBase tileEntity) {
		super(inventory);
		tileMachine = tileEntity;
		resource = tileEntity.getGuiTexture();
		elements.add(new GuiEnergyDisplay(tileEntity, this, resource));
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		mc.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(resource);
		
		int startX = (width - xSize) / 2;
		int startY = (height - ySize) / 2;
		
		drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);
		
		int update = ((TileEntityCrusher) tileMachine).getCrunchProgressScaled(24);
		drawTexturedModalRect(startX+68, startY+35, 176, 14, update, 16);
		
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
	}
}
