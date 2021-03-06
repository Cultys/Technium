package cultys.technium.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.models.ModelTechniumSource;
import cultys.technium.tileentities.TileEntityTechniumSource;

@SideOnly(Side.CLIENT)
public class RenderTechniumSource extends TileEntitySpecialRenderer {
	
	ResourceLocation texture = new ResourceLocation(TechniumMod.MODID + ":" + "textures/technium_modelTechnium.png");

	private ModelTechniumSource model;
	
	public RenderTechniumSource() {
		this.model = new ModelTechniumSource();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		TileEntityTechniumSource entity = (TileEntityTechniumSource) te;
		this.model.setRotationAngles(entity.rotation);
		
		GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GL11.glRotatef(180, 0F, 0F, 1F);
			this.bindTexture(texture);
			
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor3f(entity.red, entity.green, entity.blue);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glPushMatrix();
				this.model.renderModel(entity, 0.0625F);
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}
}
