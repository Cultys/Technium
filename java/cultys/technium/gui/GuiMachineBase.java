package cultys.technium.gui;

import java.util.HashSet;
import java.util.Set;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import cultys.technium.tileentities.TileEntityMachineBase;

@SideOnly(Side.CLIENT)
public abstract class GuiMachineBase extends GuiContainer {

	protected TileEntityMachineBase tileMachine;
	protected Set<GuiElement> elements = new HashSet<GuiElement>();
	
	public GuiMachineBase(Container container) {
		super(container);
	}
	
	public GuiMachineBase(TileEntityMachineBase tileEntity, Container container) {
		super(container);
		tileMachine = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		int startX = (width - xSize) / 2;
		int startY = (height - ySize) / 2;
		int cursorX = (mouseX - startX);
		int cursorY = (mouseX - startY);
		
		for (GuiElement element : elements) {
			element.renderForeground(cursorX, cursorY, startX, startY);
		}
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
		
		int startX = (width - xSize) / 2;
		int startY = (height - ySize) / 2;
		int cursorX = (mouseX - startX);
		int cursorY = (mouseX - startY);
		
		for (GuiElement element : elements) {
			element.renderBackground(cursorX, cursorY, startX, startY);
		}
		
	}
}
