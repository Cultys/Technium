package cultys.technium.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiElement {

	protected static Minecraft mc = Minecraft.getMinecraft();
	protected ResourceLocation parentResource;
	protected GuiMachineBase guiObject;
	
	public GuiElement (GuiMachineBase gui, ResourceLocation parentRes) {
		guiObject = gui;
		parentResource = parentRes;
	}
	
	public abstract void renderBackground(int cursorX, int cursorY, int guiStartX, int guiStartY);
	
	public abstract void renderForeground(int cursorX, int cursorY, int guiStartX, int guiStartY);
	
}
