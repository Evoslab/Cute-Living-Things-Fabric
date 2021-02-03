package com.github.Evoslab.surfandturfcuties.registry;

import com.github.Evoslab.surfandturfcuties.SurfAndTurfCuties;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class SurfAndTurfCutiesItems {

    public static final FishBucketItem CYAN_SHORK_BUCKET = new FishBucketItem(SurfAndTurfCutiesEntities.SHORK, Fluids.WATER, (new Item.Settings().maxCount(1).rarity(Rarity.COMMON).group(ItemGroup.MISC)));

    public static void init() {

        Registry.register(Registry.ITEM, new Identifier(SurfAndTurfCuties.MODID, "cyan_shork_bucket"), CYAN_SHORK_BUCKET);

    }
}
