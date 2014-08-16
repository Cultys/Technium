package cultys.technium.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import cultys.technium.TechniumMod;
import cultys.technium.tileentities.TileEntityMachineBase;

@SideOnly(Side.CLIENT)
public class GuiEnergyDisplay extends GuiElement {
	
	private static final ResourceLocation texture = new ResourceLocation(TechniumMod.MODID + ":" + "textures/gui/technium_guiElementPowerBar.png");
	
	private static final int textureHeight = 63;
	private static final int textureWidth = 8;
	private static final int guiXOffset = 160;
	private static final int guiYOffset = 7;
	
	private TileEntityMachineBase tileMachine;
	
	public GuiEnergyDisplay(TileEntityMachineBase tileEntity, GuiMachineBase gui, ResourceLocation parentRes) {
		super(gui, parentRes);
		tileMachine = tileEntity;
	}
	
	public void renderBackground(int cursorX, int cursorY, int guiStartX, int guiStartY)
	{
		mc.renderEngine.bindTexture(texture);
			int update = tileMachine.getPowerScaled(textureHeight);
			int xOffset = guiStartX + guiXOffset;
			int yOffset = guiStartY + guiYOffset;
			
			guiObject.drawTexturedModalRect(xOffset, yOffset, 0, 0, textureWidth, textureHeight);
			guiObject.drawTexturedModalRect(xOffset, yOffset + textureHeight, 0, textureHeight, 8, 8);
			guiObject.drawTexturedModalRect(xOffset, yOffset + (textureHeight - update), textureWidth, textureHeight - update, textureWidth, update);
		mc.renderEngine.bindTexture(parentResource);
	}

	public void renderForeground(int cursorX, int cursorY, int guiStartX, int guiStartY){}
}
