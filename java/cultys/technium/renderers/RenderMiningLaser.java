package cultys.technium.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cultys.technium.TechniumMod;
import cultys.technium.models.ModelMiningLaser;

@SideOnly(Side.CLIENT)
public class RenderMiningLaser implements IItemRenderer 
{

	public static final ResourceLocation texture = new ResourceLocation(TechniumMod.MODID + ":" + "textures/technium_modelMiningLaser.png");
	public static final ResourceLocation textureLaser = new ResourceLocation(TechniumMod.MODID + ":" + "textures/technium_lightningEffectLaser.png");
	private static Minecraft mc = Minecraft.getMinecraft();
	
	private ModelMiningLaser model = new ModelMiningLaser();
	
	public RenderMiningLaser(){}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		switch(type) 
		{
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		boolean isFirstPerson = false;
		float scale = 1.3F;
		
		switch(type)
		{
			case EQUIPPED: 
			{
				mc.renderEngine.bindTexture(texture);

				GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_BLEND);
					
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glRotatef(3F, 1.0F, 0.0F, 0.0F); //X
					GL11.glRotatef(-6F, 0.0F, 1.0F, 0.0F); //Y
					GL11.glRotatef(-145F, 0.0F, 0.0F, 1.0F); //Z

					GL11.glTranslatef(-1.1F, -0.30F, -0.2F);
					GL11.glScalef(scale, scale, scale);
					
					model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, isFirstPerson);
					
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					
					if (item.stackTagCompound != null && item.stackTagCompound.getBoolean("isActive"))
					{					
						GL11.glTranslatef(-0.19F, 0.03F, 0.06F);
						RenderLightningEffect.doRenderLaser(-item.stackTagCompound.getFloat("length"), 2, textureLaser);
					}
				GL11.glPopMatrix();
				break;
			}
			case EQUIPPED_FIRST_PERSON:
			{
				if (data[1] != null && data[1] instanceof EntityPlayer) 
				{
					if(((EntityPlayer)data[1] == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
					{
						isFirstPerson = true;
					}
				}
				
				mc.renderEngine.bindTexture(texture);
				
				GL11.glPushMatrix();
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					GL11.glEnable(GL11.GL_BLEND);
					
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glRotatef(3F, 1.0F, 0.0F, 0.0F); //X
					GL11.glRotatef(2F, 0.0F, 1.0F, 0.0F); //Y
					GL11.glRotatef(-150F, 0.0F, 0.0F, 1.0F); //Z	
					
					GL11.glTranslatef(-1.1F, -0.10F, -0.2F);
					GL11.glScalef(scale, scale, scale);
					
					model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, isFirstPerson);
					
					GL11.glDisable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_ALPHA_TEST);
					
					if (item.stackTagCompound != null && item.stackTagCompound.getBoolean("isActive"))
					{					
						GL11.glTranslatef(-0.19F, 0.03F, 0.06F);
						RenderLightningEffect.doRenderLaser(-6F, 2, textureLaser);
					}
				GL11.glPopMatrix();
				break;
			}
			default: break;
		}
	}
}
