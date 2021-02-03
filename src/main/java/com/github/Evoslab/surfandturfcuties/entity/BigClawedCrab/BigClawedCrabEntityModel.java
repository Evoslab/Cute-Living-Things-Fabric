// Made with Blockbench 3.7.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

package com.github.Evoslab.surfandturfcuties.entity.BigClawedCrab;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BigClawedCrabEntityModel extends EntityModel<BigClawedCrab> {
private final ModelPart body;
	private final ModelPart leftclaw;
	private final ModelPart rightclaw;
	private final ModelPart leftleg;
	private final ModelPart rightleg;
public BigClawedCrabEntityModel() {
		textureWidth = 64;
		textureHeight = 64;
		body = new ModelPart(this);
		body.setPivot(0.0F, 22.7667F, -0.3333F);
		body.setTextureOffset(22, 22).addCuboid(-3.0F, -2.4667F, -2.6667F, 6.0F, 3.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(21, 0).addCuboid(-3.0F, -5.4667F, -1.6667F, 6.0F, 3.0F, 0.0F, 0.0F, false);

		leftclaw = new ModelPart(this);
		leftclaw.setPivot(-3.1F, -2.4667F, 0.3333F);
		body.addChild(leftclaw);
		leftclaw.setTextureOffset(0, 0).addCuboid(-6.9F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F, false);

		rightclaw = new ModelPart(this);
		rightclaw.setPivot(3.1F, -2.4667F, 0.3333F);
		body.addChild(rightclaw);
		rightclaw.setTextureOffset(0, 14).addCuboid(-0.1F, -3.5F, -3.5F, 7.0F, 7.0F, 7.0F, 0.0F, false);

		leftleg = new ModelPart(this);
		leftleg.setPivot(-2.0F, -0.2667F, 0.8333F);
		body.addChild(leftleg);
		setRotationAngle(leftleg, 0.0F, 0.0F, 0.5236F);
		leftleg.setTextureOffset(21, 10).addCuboid(0.4964F, -0.4402F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);

		rightleg = new ModelPart(this);
		rightleg.setPivot(1.5F, -0.2667F, 0.8333F);
		body.addChild(rightleg);
		setRotationAngle(rightleg, 0.0F, 0.0F, -0.5236F);
		rightleg.setTextureOffset(21, 10).addCuboid(0.1964F, -0.0402F, -3.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);
}
@Override
public void setAngles(BigClawedCrab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	this.leftleg.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
	this.rightleg.pitch = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
}

@Override
public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		body.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

}