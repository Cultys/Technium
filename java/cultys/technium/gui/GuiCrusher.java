package cultys.technium.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cultys.technium.TechniumMod;
import cultys.technium.tileentities.TileEntityCrusher;

public class GuiCrusher extends GuiContainer {

	private TileEntityCrusher tileCrusher;
	private static final ResourceLocation guiTexture = new ResourceLocation(TechniumMod.MODID + ":" + "textures/gui/technium_guiCrusher.png");
	
	public GuiCrusher(Container inventory, TileEntityCrusher tileCrusher) {
		super(inventory);
		this.tileCrusher = tileCrusher;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		mc.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0xffffff);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiTexture);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

		int update = tileCrusher.getCrunchProgressScaled(24);
		drawTexturedModalRect(j+68, k+35, 176, 14, update, 16);
		
		update = tileCrusher.getPowerScaled(61);
		drawTexturedModalRect(j+7, k+7, 176, 30, 7, update);
	}

}
