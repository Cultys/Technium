package cultys.technium.models;

import cultys.technium.tileentities.TileEntityTechnium;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTechnium extends ModelBase {

	private ModelRenderer[] stage = new ModelRenderer[4];

	public ModelTechnium() {
		ModelRenderer childRender;
		
		
		//Technium stage 1
		stage[0] = new ModelRenderer(this, 0, 0);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 6, 1);
		childRender.setRotationPoint(4F, 18.23333F, -1F);
		initModelRenderer(childRender, 0F, 0.349F,0.227F);
		stage[0].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 7, 1);
		childRender.setRotationPoint(-4F, 17.53333F, 2F);
		initModelRenderer(childRender, -0.226F, -0.785F, -0.052F);
		stage[0].addChild(childRender);

		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 8, 1);
		childRender.setRotationPoint(-2F, 16.5F, -5F);
		initModelRenderer(childRender, 0.2276F, 0.489F, 0F);
		stage[0].addChild(childRender);

		//Technium stage 2
		stage[1] = new ModelRenderer(this, 0, 0);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 1);
		childRender.setRotationPoint(4F, 16.2F, -1F);
		initModelRenderer(childRender, 0F, 0.349F, 0.227F);
		stage[1].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 11, 2);
		childRender.setRotationPoint(-4F, 13.5F, 2F);
		initModelRenderer(childRender, -0.227F, -0.785F, -0.052F);
		stage[1].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 1);
		childRender.setRotationPoint(-2F, 16.5F, -5F);
		initModelRenderer(childRender, 0.227F, 0.489F, 0F);
		stage[1].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 6, 1);
		childRender.setRotationPoint(0F, 18.5F, 0F);
		initModelRenderer(childRender, -0.035F, 0.367F, 0F);
		stage[1].addChild(childRender);

		//Technium stage 3
		stage[2] = new ModelRenderer(this, 0, 0);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 3);
		childRender.setRotationPoint(4F, 16.2F, -1F);
		initModelRenderer(childRender, 0F, 0.349F, 0.227F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 3, 13, 2);
		childRender.setRotationPoint(-4F, 12.5F, 2F);
		initModelRenderer(childRender, -0.227F, -0.785F, -0.052F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 2);
		childRender.setRotationPoint(-2F, 16.96667F, -5F);
		initModelRenderer(childRender, 0.227F, 0.489F, 0F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 9, 2);
		childRender.setRotationPoint(0F, 15.5F, 0F);
		initModelRenderer(childRender, -0.035F, 0.367F, 0F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 4, 1);
		childRender.setRotationPoint(-2F, 20F, -1F);
		initModelRenderer(childRender, 0F, -0.260F, -0.663F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 5, 1);
		childRender.setRotationPoint(-4F, 14F, 6F);
		initModelRenderer(childRender, -0.644F, -0.332F, 0F);
		stage[2].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 1, 4, 1);
		childRender.setRotationPoint(4F, 19F, -3F);
		initModelRenderer(childRender, 0.332F, 0.873F, 0.663F);
		stage[2].addChild(childRender);


		//Technium stage 4
		stage[3] = new ModelRenderer(this, 0, 0);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 3);
		childRender.setRotationPoint(4F, 16.2F, -1F);
		initModelRenderer(childRender, 0F, 0.349F, 0.227F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 4, 13, 3);
		childRender.setRotationPoint(-4F, 12.5F, 2F);
		initModelRenderer(childRender, -0.227F, -0.785F, -0.052F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 8, 2);
		childRender.setRotationPoint(-2F, 16.96667F, -5F);
		initModelRenderer(childRender, 0.227F, 0.489F, 0F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 3, 12, 3);
		childRender.setRotationPoint(-1F, 13.5F, 0F);
		initModelRenderer(childRender, -0.0356F, 0.367F, 0F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 7, 1);
		childRender.setRotationPoint(-5F, 18F, -1.5F);
		initModelRenderer(childRender, 0F, -0.260F, -0.663F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 6, 1);
		childRender.setRotationPoint(4F, 19F, -3F);
		initModelRenderer(childRender, 0.332F, 0.873F, 0.663F);
		stage[3].addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 2, 12, 2);
		childRender.setRotationPoint(-4F, 14.5F, 6.2F);
		initModelRenderer(childRender, -0.644F, -0.332F, 0F);
		stage[3].addChild(childRender);
	}

	private void initModelRenderer(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
		model.mirror = true;
		model.setTextureSize(32, 32);
	}
	
	public void setRotationAngles(float radians) {
		for (int i=0; i < stage.length; i++) {
			stage[i].rotateAngleY = radians;
		}
	}

	public void renderModel(TileEntityTechnium entity, float f5){
		this.setRotationAngles(entity.rotation);
		stage[entity.getStage()].render(f5);
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
