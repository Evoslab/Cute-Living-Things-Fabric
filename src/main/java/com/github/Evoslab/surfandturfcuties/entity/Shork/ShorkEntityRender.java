package com.github.Evoslab.surfandturfcuties.entity.Shork;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ShorkEntityRender extends MobEntityRenderer<ShorkEntity, ShorkEntityModel> {
    
    public ShorkEntityRender(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new ShorkEntityModel(), 0.4f);
    }

    @Override
    public Identifier getTexture(ShorkEntity entity) {
        return new Identifier("surfandturfcuties", "textures/entity/shork/cyan_shork.png");
    }
}
