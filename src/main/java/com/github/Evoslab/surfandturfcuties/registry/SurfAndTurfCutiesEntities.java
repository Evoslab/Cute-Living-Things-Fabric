package com.github.Evoslab.surfandturfcuties.registry;

import com.github.Evoslab.surfandturfcuties.SurfAndTurfCuties;
import com.github.Evoslab.surfandturfcuties.entity.BigClawedCrab.BigClawedCrab;
import com.github.Evoslab.surfandturfcuties.entity.Shork.ShorkEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SurfAndTurfCutiesEntities {

    public static final EntityType<ShorkEntity> SHORK = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(SurfAndTurfCuties.MODID, "shork"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, ShorkEntity::new).dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());

    public static final EntityType<BigClawedCrab> BIG_CLAWED_CRAB = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(SurfAndTurfCuties.MODID, "big_clawed_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, BigClawedCrab::new).dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());

    public static void init() {

        FabricDefaultAttributeRegistry.register(SHORK, ShorkEntity.createMobAttributes());

        FabricDefaultAttributeRegistry.register(BIG_CLAWED_CRAB, BigClawedCrab.createMobAttributes());

        BiomeModifications.addSpawn(
                BiomeSelectors.spawnsOneOf(EntityType.TROPICAL_FISH),
                SpawnGroup.WATER_AMBIENT,
                SurfAndTurfCutiesEntities.SHORK,
                1, //weight
                2, //minGroupSize
                3 //maxGroupSize
        );

    }
}
