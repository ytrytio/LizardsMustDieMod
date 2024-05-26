package lmd.content;

import arc.graphics.*;
import mindustry.type.*;

public class LMDItems {
    public static Item birchWood, birchPlanks;

    //public static final Seq<Item> serpuloItems = new Seq<>();

    public static void load(){
        birchWood = new Item("birch-wood", Color.valueOf("ffd3b0")){{
            explosiveness = 0.1f;
            flammability = 0.7f;
            hardness = 1;
            cost = 0.5f;
            alwaysUnlocked = true;
            buildable = true;
        }};

        birchPlanks = new Item("birch-planks", Color.valueOf("ffcca3")){{
            explosiveness = 0.1f;
            flammability = 0.9f;
            hardness = 1;
            cost = 1f;
            buildable = true;
        }};
    }
}