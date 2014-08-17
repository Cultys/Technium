package cultys.technium.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMiningLaser extends ModelBase
{
	ModelRenderer Barrel_1;
	ModelRenderer Barrel_2;
	ModelRenderer Barrel_3;
	ModelRenderer Barrel_4;
	ModelRenderer TechniumCrystal;
	ModelRenderer Handle_Right_Arm;
	ModelRenderer Handle_Left_Arm;
	ModelRenderer Handle_Left_Arm_FP;

	public ModelMiningLaser()
	{
		textureWidth = 32;
		textureHeight = 32;

		Barrel_1 = new ModelRenderer(this, 0, 0);
		Barrel_1.addBox(0F, 0F, 0F, 10, 1, 1);
		Barrel_1.setRotationPoint(-3F, 1F, 1F);
		Barrel_1.setTextureSize(32, 32);
		Barrel_1.mirror = true;
		setRotation(Barrel_1, 0F, 0F, 0F);
		Barrel_2 = new ModelRenderer(this, 0, 0);
		Barrel_2.addBox(0F, 0F, 0F, 10, 1, 1);
		Barrel_2.setRotationPoint(-3F, 0F, 0F);
		Barrel_2.setTextureSize(32, 32);
		Barrel_2.mirror = true;
		setRotation(Barrel_2, 0F, 0F, 0F);
		Barrel_3 = new ModelRenderer(this, 0, 0);
		Barrel_3.addBox(0F, 0F, 0F, 10, 1, 1);
		Barrel_3.setRotationPoint(-3F, 0F, 2F);
		Barrel_3.setTextureSize(32, 32);
		Barrel_3.mirror = true;
		setRotation(Barrel_3, 0F, 0F, 0F);
		Barrel_4 = new ModelRenderer(this, 0, 0);
		Barrel_4.addBox(0F, 0F, 0F, 10, 2, 1);
		Barrel_4.setRotationPoint(-3F, -1F, 1F);
		Barrel_4.setTextureSize(32, 32);
		Barrel_4.mirror = true;
		setRotation(Barrel_4, 0F, 0F, 0F);
		TechniumCrystal = new ModelRenderer(this, 0, 3);
		TechniumCrystal.addBox(0F, 0F, 0F, 3, 1, 1);
		TechniumCrystal.setRotationPoint(-6F, 0F, 1F);
		TechniumCrystal.setTextureSize(32, 32);
		TechniumCrystal.mirror = true;
		setRotation(TechniumCrystal, 0F, 0F, 0F);
		Handle_Right_Arm = new ModelRenderer(this, 0, 0);
		Handle_Right_Arm.addBox(0F, 0F, 0F, 1, 2, 1);
		Handle_Right_Arm.setRotationPoint(3F, 2F, 1F);
		Handle_Right_Arm.setTextureSize(32, 32);
		Handle_Right_Arm.mirror = true;
		setRotation(Handle_Right_Arm, 0F, 0F, 0F);
		Handle_Left_Arm = new ModelRenderer(this, 0, 0);
		Handle_Left_Arm.addBox(0F, 0F, 0F, 1, 1, 2);
		Handle_Left_Arm.setRotationPoint(2F, 0F, 3F);
		Handle_Left_Arm.setTextureSize(32, 32);
		Handle_Left_Arm.mirror = true;
		setRotation(Handle_Left_Arm, 0F, 0F, 0F);
		Handle_Left_Arm_FP = new ModelRenderer(this, 0, 0);
		Handle_Left_Arm_FP.addBox(0F, 0F, 0F, 1, 1, 2);
		Handle_Left_Arm_FP.setRotationPoint(2F, 0F, -2F);
		Handle_Left_Arm_FP.setTextureSize(32, 32);
		Handle_Left_Arm_FP.mirror = true;
		setRotation(Handle_Left_Arm_FP, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean isFirstPerson)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Barrel_1.render(f5);
		Barrel_2.render(f5);
		Barrel_3.render(f5);
		Barrel_4.render(f5);
		TechniumCrystal.render(f5);
		Handle_Right_Arm.render(f5);
		if (isFirstPerson)
		{
			Handle_Left_Arm_FP.render(f5);
		}
		else
		{
			Handle_Left_Arm.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
