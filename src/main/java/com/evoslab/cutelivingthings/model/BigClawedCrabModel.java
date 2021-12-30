package com.evoslab.cutelivingthings.model;

import com.evoslab.cutelivingthings.CuteLivingThings;
import com.evoslab.cutelivingthings.entities.BigClawedCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BigClawedCrabModel extends AnimatedGeoModel<BigClawedCrabEntity> {
    @Override
    public Identifier getModelLocation(BigClawedCrabEntity object) {
        return new Identifier(CuteLivingThings.MOD_ID, "geo/entity/big_clawed_crab.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BigClawedCrabEntity object) {
        return new Identifier(CuteLivingThings.MOD_ID, "textures/entity/big_clawed_crab.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BigClawedCrabEntity animatable) {
        return new Identifier(CuteLivingThings.MOD_ID, "animations/entity/bigclawedcrab.animation.json");
    }
}