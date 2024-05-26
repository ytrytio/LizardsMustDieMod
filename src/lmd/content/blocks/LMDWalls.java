package lmd.content.blocks;

import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.content.*;
import static mindustry.type.ItemStack.with;
//import mindustry.type.Item;

public class LMDWalls {
    public static Block wall;

    public static void load() {
        wall = new Wall("wall") {{
            requirements(Category.defense, with(Items.copper, 6));
            researchCost = with(Items.copper, 100);
            size = 4;
            scaledHealth = 160;
            health = -1; // 160 * size;
            armor = 3f;
            buildCostMultiplier = 6f;
        }};
    }
}