package com.github.Evoslab.surfandturfcuties;

import com.github.Evoslab.surfandturfcuties.registry.SurfAndTurfCutiesEntities;
import com.github.Evoslab.surfandturfcuties.registry.SurfAndTurfCutiesItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SurfAndTurfCuties implements ModInitializer {

	public static final String MODID = "surfandturfcuties";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Override
	public void onInitialize() {

		SurfAndTurfCutiesItems.init();
		SurfAndTurfCutiesEntities.init();
		new SurfAndTurfCutiesItems();
		new SurfAndTurfCutiesEntities();

	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
