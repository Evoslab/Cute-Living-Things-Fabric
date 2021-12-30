package com.evoslab.cutelivingthings;

import com.evoslab.cutelivingthings.entities.CuteLivingThingsEntities;
import com.evoslab.cutelivingthings.renderer.BigClawedCrabRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CuteLivingThingsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(CuteLivingThingsEntities.BIG_CLAWED_CRAB, BigClawedCrabRenderer::new);
    }
}