package lmd.content.blocks;

import lmd.content.*;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;
import static mindustry.type.ItemStack.with;



public class LMDCores {
    public static Block coreNest, coreHut;

    public static void load() {
        coreNest = new CoreBlock("core-nest") {{
            requirements(Category.effect, with(LMDItems.birchWood, 1000, LMDItems.birchPlanks, 500));
            size = 4;
            isFirstTier = true;
            unitType = LMDUnits.custodian;
            health = 3500;
            itemCapacity = 2000;
            armor = 5f;
            alwaysUnlocked = true;
            buildCostMultiplier = 6f;

            unitCapModifier = 16;
        }};

        coreHut = new CoreBlock("core-hut") {{
            requirements(Category.effect, with(LMDItems.birchWood, 1000));
            size = 4;
            isFirstTier = true;
            unitType = LMDUnits.burgher;
            health = 2500;
            itemCapacity = 2500;
            armor = 10f;
            alwaysUnlocked = true;
            buildCostMultiplier = 6f;

            unitCapModifier = 20;
        }};
    }
}