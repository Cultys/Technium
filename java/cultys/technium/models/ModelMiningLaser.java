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
    ModelRenderer Handle_Right_Arm;
    ModelRenderer Handle_Left_Arm_FP;
    ModelRenderer Handle_Left_Arm;
    ModelRenderer Scope1;
    ModelRenderer Scope2;
    ModelRenderer Scope3;
    ModelRenderer Scope4;
    ModelRenderer ScopeGlass1;
    ModelRenderer ScopeGlass2;
    ModelRenderer ScopeMount;
  
  public ModelMiningLaser()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Barrel_1 = new ModelRenderer(this, 0, 0);
      Barrel_1.addBox(0F, 0F, 0F, 15, 1, 2);
      Barrel_1.setRotationPoint(-3F, 3F, 0F);
      Barrel_1.setTextureSize(64, 64);
      Barrel_1.mirror = true;
      setRotation(Barrel_1, 0F, 0F, 0F);
      Barrel_2 = new ModelRenderer(this, 0, 3);
      Barrel_2.addBox(0F, 0F, 0F, 15, 2, 3);
      Barrel_2.setRotationPoint(-3F, 1F, -1F);
      Barrel_2.setTextureSize(64, 64);
      Barrel_2.mirror = true;
      setRotation(Barrel_2, 0F, 0F, 0F);
      Barrel_3 = new ModelRenderer(this, 0, 8);
      Barrel_3.addBox(0F, 0F, 0F, 15, 2, 1);
      Barrel_3.setRotationPoint(-3F, 1F, 2F);
      Barrel_3.setTextureSize(64, 64);
      Barrel_3.mirror = true;
      setRotation(Barrel_3, 0F, 0F, 0F);
      Barrel_4 = new ModelRenderer(this, 0, 11);
      Barrel_4.addBox(0F, 0F, 0F, 15, 1, 2);
      Barrel_4.setRotationPoint(-3F, 0F, 0F);
      Barrel_4.setTextureSize(64, 64);
      Barrel_4.mirror = true;
      setRotation(Barrel_4, 0F, 0F, 0F);
      Handle_Right_Arm = new ModelRenderer(this, 36, 0);
      Handle_Right_Arm.addBox(0F, 0F, 0F, 1, 4, 1);
      Handle_Right_Arm.setRotationPoint(4F, 3F, 0.5F);
      Handle_Right_Arm.setTextureSize(64, 64);
      Handle_Right_Arm.mirror = true;
      setRotation(Handle_Right_Arm, 0F, 0F, -0.3490659F);
      Handle_Left_Arm_FP = new ModelRenderer(this, 36, 6);
      Handle_Left_Arm_FP.addBox(0F, 0F, 0F, 1, 1, 5);
      Handle_Left_Arm_FP.setRotationPoint(5F, 1.5F, -5F);
      Handle_Left_Arm_FP.setTextureSize(64, 64);
      Handle_Left_Arm_FP.mirror = true;
      setRotation(Handle_Left_Arm_FP, 0F, -0.3490659F, 0F);
      Handle_Left_Arm = new ModelRenderer(this, 36, 6);
      Handle_Left_Arm.addBox(0F, 0F, 0F, 1, 1, 5);
      Handle_Left_Arm.setRotationPoint(3F, 1.5F, 2F);
      Handle_Left_Arm.setTextureSize(64, 64);
      Handle_Left_Arm.mirror = true;
      setRotation(Handle_Left_Arm, 0F, 0.3490659F, 0F);
      Scope1 = new ModelRenderer(this, 0, 14);
      Scope1.addBox(0F, 0F, 0F, 7, 1, 1);
      Scope1.setRotationPoint(2F, -2F, 0.5F);
      Scope1.setTextureSize(64, 64);
      Scope1.mirror = true;
      setRotation(Scope1, 0F, 0F, 0F);
      Scope2 = new ModelRenderer(this, 16, 14);
      Scope2.addBox(0F, 0F, 0F, 7, 1, 1);
      Scope2.setRotationPoint(2F, -3F, -0.5F);
      Scope2.setTextureSize(64, 64);
      Scope2.mirror = true;
      setRotation(Scope2, 0F, 0F, 0F);
      Scope3 = new ModelRenderer(this, 16, 16);
      Scope3.addBox(0F, 0F, 0F, 7, 1, 1);
      Scope3.setRotationPoint(2F, -3F, 1.5F);
      Scope3.setTextureSize(64, 64);
      Scope3.mirror = true;
      setRotation(Scope3, 0F, 0F, 0F);
      Scope4 = new ModelRenderer(this, 0, 16);
      Scope4.addBox(0F, 0F, 0F, 7, 1, 1);
      Scope4.setRotationPoint(2F, -4F, 0.5F);
      Scope4.setTextureSize(64, 64);
      Scope4.mirror = true;
      setRotation(Scope4, 0F, 0F, 0F);
      ScopeGlass1 = new ModelRenderer(this, 0, 18);
      ScopeGlass1.addBox(0F, 0F, 0F, 0, 1, 1);
      ScopeGlass1.setRotationPoint(2.1F, -3F, 0.5F);
      ScopeGlass1.setTextureSize(64, 64);
      ScopeGlass1.mirror = true;
      setRotation(ScopeGlass1, 0F, 0F, 0F);
      ScopeGlass2 = new ModelRenderer(this, 0, 18);
      ScopeGlass2.addBox(0F, 0F, 0F, 0, 1, 1);
      ScopeGlass2.setRotationPoint(8.9F, -3F, 0.5F);
      ScopeGlass2.setTextureSize(64, 64);
      ScopeGlass2.mirror = true;
      setRotation(ScopeGlass2, 0F, 0F, 0F);
      ScopeMount = new ModelRenderer(this, 2, 18);
      ScopeMount.addBox(0F, 0F, -0.03333334F, 3, 1, 1);
      ScopeMount.setRotationPoint(4F, -1F, 0.5F);
      ScopeMount.setTextureSize(64, 64);
      ScopeMount.mirror = true;
      setRotation(ScopeMount, 0F, 0F, 0F);
  }

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean isFirstPerson)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    Barrel_1.render(f5);
	    Barrel_2.render(f5);
	    Barrel_3.render(f5);
	    Barrel_4.render(f5);
	    Handle_Right_Arm.render(f5);
	    Scope1.render(f5);
	    Scope2.render(f5);
	    Scope3.render(f5);
	    Scope4.render(f5);
	    ScopeGlass1.render(f5);
	    ScopeGlass2.render(f5);
	    ScopeMount.render(f5);
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
