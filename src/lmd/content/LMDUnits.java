package lmd.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.ai.types.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.graphics.*;
import mindustry.entities.pattern.*;

public class LMDUnits{
    public static UnitType
    //lizards
    custodian, uterus, emperor,

    //rus
    burgher, vigilante;

    public static void load(){
        custodian = new UnitType("custodian"){{
            constructor = LegsUnit::create;

            aiController = BuilderAI::new;
            isEnemy = false;
            hovering = true;
            speed = 1f;
            hitSize = 13f;
            rotateSpeed = 6f;
            accel = 0.1f;
            drag = 0.05f;
            mineSpeed = 8f;
            mineTier = 1;
            outlineRadius = 0;
            buildSpeed = 0.4f;
            health = 500f;
            itemCapacity = 50;
            alwaysUnlocked = true;
            weapons.add(new Weapon("lizards-must-die-custodian-weapon"){{
                shootSound = Sounds.bolt;
                reload = 15f;
                x = 10f;
                y = 3f;
                shootX = -1f;
                shootY = 2f;
                top = true;
                rotate = false;
                alternate = true;
                ejectEffect = Fx.casing1;
                mirror = true;
                bullet = new LaserBoltBulletType(2.5f, 10){{
                    healPercent = 5f;
                    collidesTeam = true;
                    lifetime = 60f;
                }};
            }});
        }};
        uterus = new UnitType("uterus"){{
            constructor = LegsUnit::create;

            aiController = BuilderAI::new;
            isEnemy = false;

            legCount = 4;
            legLength = 16f;
            hovering = true;

            speed = 1f;
            hitSize = 13f;
            rotateSpeed = 6f;
            accel = 0.1f;
            drag = 0.05f;
            mineSpeed = 8f;
            mineTier = 1;
            outlineRadius = 0;
            buildSpeed = 0.4f;
            health = 500f;
            itemCapacity = 50;
            alwaysUnlocked = true;
            weapons.add(new Weapon(){{
                shootSound = Sounds.place;
                reload = 400f;
                x = 0f;
                y = 15f;
                top = false;
                rotate = false;
                ejectEffect = Fx.casing1;
                mirror = false;
                bullet = new BasicBulletType(0f, 150, "lizards-must-die-uterus-mine"){{
                    backColor = Pal.heal;
                    frontColor = Pal.gray;

                    hitSound = Sounds.explosionbig;
                    height = width = 15f;
                    ignoreRotation = true;
                    shootCone = 1f;
                    splashDamage = 250;
                    splashDamageRadius = 75;
                    lifetime = 360f;
                    statusDuration = 240f;
                    keepVelocity = false;

                    hitSize = 7f;
                    hitShake = 4f;
                    collidesTeam = false;
                    collidesAir = false;
                    collidesGround = true;
                    collidesTiles = false;
                    hittable = false;
                    absorbable = false;

                    pierce = false;
                    pierceBuilding = false;
                    pierceCap = 1;
                    layer = 0f;

                    shrinkX = shrinkY = 0f;

                    shootEffect = LMDFx.spiralRotatin;
                    hitEffect = Fx.flakExplosion;
                    despawnEffect = Fx.none;
                    status = StatusEffects.sapped;
                }};/*
                bullet = new BasicBulletType(){{
                    sprite = "large-bomb";
                    width = height = 120/4f;

                    maxRange = 30f;
                    ignoreRotation = true;

                    backColor = Pal.heal;
                    frontColor = Color.white;
                    mixColorTo = Color.white;

                    hitSound = Sounds.plasmaboom;

                    shootCone = 180f;
                    ejectEffect = Fx.none;
                    hitShake = 4f;

                    collidesAir = false;

                    lifetime = 70f;

                    despawnEffect = Fx.greenBomb;
                    hitEffect = Fx.massiveExplosion;
                    keepVelocity = false;
                    spin = 2f;

                    shrinkX = shrinkY = 0.7f;

                    collides = false;

                    healPercent = 15f;
                    splashDamage = 220f;
                    splashDamageRadius = 80f;
                }};*/
            }});
        }};

        emperor = new UnitType("emperor"){{
            constructor = LegsUnit::create;

            aiController = BuilderAI::new;
            isEnemy = false;
            hovering = true;
            speed = 1f;
            hitSize = 13f;
            rotateSpeed = 6f;
            accel = 0.1f;
            drag = 0.05f;
            mineSpeed = 8f;
            mineTier = 1;
            outlineRadius = 0;
            buildSpeed = 0.4f;
            health = 500f;
            itemCapacity = 50;
            alwaysUnlocked = true;
            weapons.add(
            new Weapon(){{
              y = 5f;
              x = 0f;
              shootY = 7f;
              reload = 20;
              shake = 4f;
              ejectEffect = Fx.casing1;
              shootSound = Sounds.shootBig;
              rotate = false;
              shadow = 12f;
              recoil = 3f;
              mirror = false;

              shoot = new ShootSpread(3, 10f);

              bullet = new ShrapnelBulletType(){{
                  shootEffect = Fx.sparkShoot;
                  length = 15f;
                  damage = 200f;
                  width = 5f;
                  serrationLenScl = 3f;
                  serrationSpaceOffset = 30f;
                  serrationFadeOffset = 0f;
                  serrations = 8;
                  serrationWidth = 3f;
                  fromColor = Pal.heal;
                  toColor = Pal.heal;
              }};
            }}
            );
        }};


        burgher = new UnitType("burgher"){{
            constructor = MechUnit::create;
            aiController = BuilderAI::new;
            isEnemy = false;
            hovering = true;
            speed = 1.5f;
            rotateSpeed = 5f;
            accel = 0.1f;
            hitSize = 13f;
            drag = 0.05f;
            mineSpeed = 6f;
            mineTier = 1;
            outlineRadius = 0;
            buildSpeed = 0.4f;
            health = 500f;
            itemCapacity = 50;
            alwaysUnlocked = true;
            weapons.add(new Weapon("lizards-must-die-burgher-weapon"){{
                shootSound = Sounds.bolt;
                reload = 100f;
                x = 10f;
                y = 0f;
                top = true;
                rotate = false;
                ejectEffect = Fx.casing1;
                mirror = true;
                alternate = true;
                bullet = new ArtilleryBulletType(25f, 100f){{
                    lifetime = 20f;
                    despawnEffect = LMDFx.godPower;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    collides = false;
                    splashDamageRadius = 40f;
                    splashDamage = 500f;
                    lightColor = Color.white;
                }};
            }});
        }};
        vigilante = new UnitType("vigilante"){{
            constructor = MechUnit::create;
            aiController = BuilderAI::new;
            isEnemy = false;
            hovering = false;
            speed = 1.5f;
            rotateSpeed = 5f;
            accel = 0.1f;
            hitSize = 13f;
            drag = 0.05f;
            mineSpeed = 6f;
            mineTier = 1;
            outlineRadius = 0;
            buildSpeed = 0.4f;
            health = 500f;
            itemCapacity = 50;
            alwaysUnlocked = true;
            weapons.add(new Weapon("lizards-must-die-vigilante-weapon"){{
                shootSound = Sounds.shootSnap;
                reload = 15f;
                x = 12f;
                y = 4f;
                top = true;
                rotate = true;
                ejectEffect = Fx.casing1;
                mirror = true;
                alternate = true;
                bullet = new LaserBoltBulletType(2.5f, 50){{
                    shootEffect = LMDFx.idkLol;
                    lifetime = 60f;
                }};
            }});
            weapons.add(new Weapon("lizards-must-die-vigilante-weapon"){{
                shootSound = Sounds.shootSnap;
                reload = 15f;
                x = 12f;
                y = -8f;
                top = true;
                rotate = true;
                ejectEffect = Fx.casing1;
                mirror = true;
                alternate = true;
                bullet = new LaserBoltBulletType(2.5f, 50){{
                    lifetime = 60f;
                }};
            }});
        }};

    }
}
