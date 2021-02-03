package com.github.Evoslab.surfandturfcuties.entity.BigClawedCrab;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BigClawedCrabEntityRender extends MobEntityRenderer<BigClawedCrab, BigClawedCrabEntityModel> {

    public BigClawedCrabEntityRender(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new BigClawedCrabEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(BigClawedCrab entity) {
        return new Identifier("surfandturfcuties", "textures/entity/bigclawedcrab/big_clawed_crab.png");
    }
}
