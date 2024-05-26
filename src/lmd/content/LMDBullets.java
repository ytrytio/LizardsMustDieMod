package lmd.content;

import lmd.content.LMDFx;
import mindustry.content.*;
import arc.graphics.*;
import mindustry.entities.bullet.*;

public class LMDBullets {
    public static BulletType

    prayerArrow;

    public static void load(){
        prayerArrow = new BulletType(0.0001f, 0f){{
            lifetime = LMDFx.godPower.lifetime;
            hitEffect = LMDFx.godPower;
            despawnEffect = Fx.none;
            status = StatusEffects.shocked;
            statusDuration = 10f;
            hittable = false;
            lightColor = Color.white;
        }};
    }

}
