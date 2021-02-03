// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.16
// Paste this class into your mod and generate all required imports

package com.github.Evoslab.surfandturfcuties.entity.Shork;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class ShorkEntityModel extends EntityModel<ShorkEntity> {
private final ModelPart body;
private final ModelPart leftfin;
private final ModelPart rightfin;
private final ModelPart backfin;
public ShorkEntityModel() {
		textureWidth = 32;
		textureHeight = 32;
		body = new ModelPart(this);
		body.setPivot(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addCuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 15).addCuboid(0.0F, -11.0F, -4.0F, 0.0F, 4.0F, 8.0F, 0.0F, false);

		leftfin = new ModelPart(this);
		leftfin.setPivot(-4.0F, 22.0F, 0.0F);
		leftfin.setTextureOffset(0, 15).addCuboid(-3.0F, 0.0F, -4.0F, 3.0F, 0.0F, 8.0F, 0.0F, false);

		rightfin = new ModelPart(this);
		rightfin.setPivot(4.0F, 22.0F, 0.0F);
		rightfin.setTextureOffset(6, 15).addCuboid(0.0F, 0.0F, -4.0F, 3.0F, 0.0F, 8.0F, 0.0F, false);

		backfin = new ModelPart(this);
		backfin.setPivot(0.0F, 21.5F, 4.0F);
		backfin.setTextureOffset(0, 11).addCuboid(0.0F, -3.5F, 0.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);
}

	@Override
	public void setAngles(ShorkEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftfin.render(matrixStack, buffer, packedLight, packedOverlay);
		rightfin.render(matrixStack, buffer, packedLight, packedOverlay);
		backfin.render(matrixStack, buffer, packedLight, packedOverlay);

	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
	}
}