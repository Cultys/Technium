package cultys.technium.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumUtils;

@SideOnly(Side.CLIENT)
public class RenderLightningEffect
{
	public static void doRenderLaser (float length, int faces, ResourceLocation texture)
	{
		float deg = 180 / faces;
		float rad = (float) TechniumUtils.deg2Rad(deg);
		double y;
		double z;
		Tessellator tessellator = Tessellator.instance;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		GL11.glPushMatrix();
			GL11.glScalef(length, 0.1F, 0.1F);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			for (int i = 0; i < faces; i++) 
			{
				GL11.glPushMatrix();
					GL11.glRotatef(deg * i, 1.0F, 0.0F, 0.0F); //X
					y = Math.sin(rad * i + rad);
					z = Math.cos(rad * i + rad);
					GL11.glTranslated(0.0D, y, z);
					tessellator.startDrawingQuads();
						tessellator.addVertexWithUV(0.0D, -1.0D, 0.0D, 0.0D, 0.0D);
						tessellator.addVertexWithUV(1.0D, -1.0D, 0.0D, 1.0D, 0.0D);
						tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, 1.0D, 1.0D);
						tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, 0.0D, 1.0D);
					tessellator.draw();
				GL11.glPopMatrix();
			}
			
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
