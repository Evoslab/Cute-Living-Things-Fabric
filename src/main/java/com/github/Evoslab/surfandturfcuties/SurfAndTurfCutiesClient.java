package com.github.Evoslab.surfandturfcuties;

import com.github.Evoslab.surfandturfcuties.entity.BigClawedCrab.BigClawedCrabEntityRender;
import com.github.Evoslab.surfandturfcuties.entity.Shork.ShorkEntityRender;
import com.github.Evoslab.surfandturfcuties.registry.SurfAndTurfCutiesEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class SurfAndTurfCutiesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(SurfAndTurfCutiesEntities.SHORK, (dispatcher, context) -> {
            return new ShorkEntityRender(dispatcher);
        });

        EntityRendererRegistry.INSTANCE.register(SurfAndTurfCutiesEntities.BIG_CLAWED_CRAB, (dispatcher, context) -> {
            return new BigClawedCrabEntityRender(dispatcher);
        });

    }
}
