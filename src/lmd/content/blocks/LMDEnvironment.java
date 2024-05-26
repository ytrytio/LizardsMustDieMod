package lmd.content.blocks;

import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.graphics.*;
import mindustry.content.*;

public class LMDEnvironment {

    public static Block lizardsSpawn, lizardsWater;

    public static void load() {
        lizardsSpawn = new SpawnBlock("lizards-spawn");

        lizardsWater = new Floor("lizards-water"){{
            speedMultiplier = 0.5f;
            variants = 0;
            status = StatusEffects.wet;
            statusDuration = 90f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
    }
}
