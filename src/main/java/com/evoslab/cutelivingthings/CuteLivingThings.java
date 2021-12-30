package com.evoslab.cutelivingthings;

import com.evoslab.cutelivingthings.entities.CuteLivingThingsEntities;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class CuteLivingThings implements ModInitializer {
	public static final String MOD_ID   = "cutelivingthings";
	public static final String MOD_NAME = "Cute Living Things";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "item_group"), () -> new ItemStack(Items.DIAMOND));

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing {}", MOD_NAME);

		GeckoLib.initialize();
		Reflection.initialize(CuteLivingThingsEntities.class);

		LOGGER.info("Initialized {}", MOD_NAME);
	}

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}
}