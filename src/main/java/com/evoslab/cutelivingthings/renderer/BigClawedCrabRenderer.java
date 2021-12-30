package com.evoslab.cutelivingthings.renderer;

import com.evoslab.cutelivingthings.entities.BigClawedCrabEntity;
import com.evoslab.cutelivingthings.model.BigClawedCrabModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BigClawedCrabRenderer extends GeoEntityRenderer<BigClawedCrabEntity> {
    public BigClawedCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BigClawedCrabModel());
        this.shadowRadius = 0.5F;
        this.addLayer(new BigClawedCrabHeldItemFeatureRenderer(this));
    }

    @Override
    public void applyRotations(BigClawedCrabEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
    }
}