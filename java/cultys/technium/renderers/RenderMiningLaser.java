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

import cultys.technium.TechniumMod;
import cultys.technium.models.ModelMiningLaser;

public class RenderMiningLaser implements IItemRenderer 
{

	public static final ResourceLocation texture = new ResourceLocation(TechniumMod.MODID + ":" + "textures/technium_modelMiningLaser.png");
	private static Minecraft mc = Minecraft.getMinecraft();
	
	private ModelMiningLaser model;
	
	public RenderMiningLaser() 
	{
		model = new ModelMiningLaser();
	}
	
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
		switch(type)
		{
			case EQUIPPED: 
			{
				GL11.glPushMatrix();
					mc.renderEngine.bindTexture(texture);
					
					GL11.glRotatef(3F, 1.0F, 0.0F, 0.0F); //X
					GL11.glRotatef(-3F, 0.0F, 1.0F, 0.0F); //Y
					GL11.glRotatef(-145F, 0.0F, 0.0F, 1.0F); //Z
	
					boolean isFirstPerson = false;
					
					if (data[1] != null && data[1] instanceof EntityPlayer) 
					{
						if(!((EntityPlayer)data[1] == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
						{
							GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
						}
						else
						{
							isFirstPerson = true;
							GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
						}
					}
					else
					{
						GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
					}
					
					float scale = 1.8F;
					GL11.glScalef(scale, scale, scale);
					
					model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, isFirstPerson);
				GL11.glPopMatrix();
			}
			case EQUIPPED_FIRST_PERSON:
			{
				GL11.glPushMatrix();
					mc.renderEngine.bindTexture(texture);
					
					GL11.glRotatef(3F, 1.0F, 0.0F, 0.0F); //X
					GL11.glRotatef(-3F, 0.0F, 1.0F, 0.0F); //Y
					GL11.glRotatef(-145F, 0.0F, 0.0F, 1.0F); //Z

					boolean isFirstPerson = false;
					
					if (data[1] != null && data[1] instanceof EntityPlayer) 
					{
						if(!((EntityPlayer)data[1] == mc.renderViewEntity && mc.gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F)))
						{
							GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
						}
						else
						{
							isFirstPerson = true;
							GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
						}
					}
					else
					{
						GL11.glTranslatef(-1.1F, -0.15F, -0.2F);
					}
					
					float scale = 1.8F;
					GL11.glScalef(scale, scale, scale);
					
					model.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, isFirstPerson);
				GL11.glPopMatrix();
			}
			default: break;
		}
		
	}
}
