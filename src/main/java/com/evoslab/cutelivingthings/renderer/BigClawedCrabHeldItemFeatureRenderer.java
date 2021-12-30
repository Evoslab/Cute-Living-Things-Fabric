package com.evoslab.cutelivingthings.renderer;

import com.evoslab.cutelivingthings.CuteLivingThings;
import com.evoslab.cutelivingthings.entities.BigClawedCrabEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

@Environment(EnvType.CLIENT)
public class BigClawedCrabHeldItemFeatureRenderer extends GeoLayerRenderer<BigClawedCrabEntity> {
    private static final Identifier LAYER = new Identifier(CuteLivingThings.MOD_ID, "textures/entity/le_glasses.png");
    private static final Identifier MODEL = new Identifier(CuteLivingThings.MOD_ID, "geo/entity/big_clawed_crab.geo.json");

    public BigClawedCrabHeldItemFeatureRenderer(IGeoRenderer<BigClawedCrabEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, BigClawedCrabEntity entity, float f, float g, float h, float j, float k, float l) {
        matrixStack.push();
        var head = this.getEntityModel().getModel(MODEL).getBone("leftclaw").get();
        matrixStack.translate(head.pivotX / 16.0F, head.pivotY / 16.0F, head.pivotZ / 16.0F);

        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(k));
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(l));
        matrixStack.translate(0.05999999865889549D, 0.27000001072883606D, -0.5D);

        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));

        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, itemStack, ModelTransformation.Mode.GROUND, false, matrixStack, vertexConsumerProvider, i);
        matrixStack.pop();
    }
}