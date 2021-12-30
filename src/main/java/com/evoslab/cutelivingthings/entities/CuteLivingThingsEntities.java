package com.evoslab.cutelivingthings.entities;

import com.evoslab.cutelivingthings.CuteLivingThings;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

public class CuteLivingThingsEntities {
    public static final EntityType<BigClawedCrabEntity> BIG_CLAWED_CRAB = register(
            "big_clawed_crab",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(BigClawedCrabEntity::new)
                    .defaultAttributes(BigClawedCrabEntity::createBigClawedCrabAttributes)
                    .dimensions(EntityDimensions.changing(0.6f, 0.6f))
                    .spawnGroup(SpawnGroup.CREATURE)
                    .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PathAwareEntity::canMobSpawn),
            new Pair<>(0xc2cbce, 0x32383c)
    );

    static {
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.BEACH), SpawnGroup.CREATURE, BIG_CLAWED_CRAB, 10, 3, 7);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, Pair<Integer, Integer> spawnEggColors) {
        EntityType<T> builtEntityType = entityType.build();

        if (spawnEggColors != null) {
            Registry.register(Registry.ITEM, new Identifier(CuteLivingThings.MOD_ID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends MobEntity>) builtEntityType, spawnEggColors.getLeft(), spawnEggColors.getRight(), new FabricItemSettings().group(CuteLivingThings.ITEM_GROUP)));
        }

        return Registry.register(Registry.ENTITY_TYPE, new Identifier(CuteLivingThings.MOD_ID, id), builtEntityType);
    }
}