package cultys.technium.models;

import cultys.technium.tileentities.TileEntityTechniumSource;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTechniumSource extends ModelBase {

	private ModelRenderer techniumSource;

	public ModelTechniumSource() {
		ModelRenderer childRender;
		
		techniumSource = new ModelRenderer(this, 0, 0);
		
		childRender = new ModelRenderer(this, 0, 0);
		childRender.addBox(0F, 0F, 0F, 4, 14, 4);
		childRender.setRotationPoint(2F, 11F, 2F);
		initModelRenderer(childRender, -0.175F, 0.401F, 0.112F);
		techniumSource.addChild(childRender);
		
		childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 3, 15, 6);
	    childRender.setRotationPoint(-2F, 10F, -6F);
	    initModelRenderer(childRender, 0F, -0.698F, -0.149F);
	    techniumSource.addChild(childRender);
	    
	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 2, 8, 2);
	    childRender.setRotationPoint(-2F, 16F, 2F);
	    initModelRenderer(childRender, -0.074F, -0.436F, 0F);
	    techniumSource.addChild(childRender);
	    
	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 1, 6, 1);
	    childRender.setRotationPoint(-6F, 18.5F, 3F);
	    initModelRenderer(childRender, 0F, 0.446F, -0.269F);
	    techniumSource.addChild(childRender);
	    
	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 2, 5, 1);
	    childRender.setRotationPoint(0F, 13F, -1F);
	    initModelRenderer(childRender, 0.0372F, -0.669F, 0.446F);
	    techniumSource.addChild(childRender);

	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 1, 7, 1);
	    childRender.setRotationPoint(0F, 17F, 6F);
	    initModelRenderer(childRender, -0.186F, 0F, 0F);
	    techniumSource.addChild(childRender);

	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 3, 10, 3);
	    childRender.setRotationPoint(4F, 14.5F, -5F);
	    initModelRenderer(childRender, 0F, 0.558F, 0.297F);
	    techniumSource.addChild(childRender);

	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, -6F, 1, 7, 1);
	    childRender.setRotationPoint(0F, 17F, 0F);
	    initModelRenderer(childRender, 0.112F, 0F, 0F);
	    techniumSource.addChild(childRender);

	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 1, 4, 1);
	    childRender.setRotationPoint(6F, 15F, 1F);
	    initModelRenderer(childRender, 0F, 0F, 0.521F);
	    techniumSource.addChild(childRender);

	    childRender = new ModelRenderer(this, 0, 0);
	    childRender.addBox(0F, 0F, 0F, 2, 9, 3);
	    childRender.setRotationPoint(-5F, 15.5F, -5F);
	    initModelRenderer(childRender, 0F, 0.112F, 0.260F);
	    techniumSource.addChild(childRender);
	}

	private void initModelRenderer(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
		model.mirror = true;
		model.setTextureSize(32, 32);
	}
	
	public void setRotationAngles(float radians) {
		techniumSource.rotateAngleY = radians;
	}

	public void renderModel(TileEntityTechniumSource entity, float f5){
		this.setRotationAngles(entity.rotation);
		techniumSource.render(f5);
	}

	public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
