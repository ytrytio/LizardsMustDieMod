package lmd.content;

import arc.graphics.Color;
import mindustry.content.*;
import lmd.content.blocks.*;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.type.Planet;
import lmd.maps.planet.*;

public class LMDPlanets{
    public static Planet earth, lazarus;

    public static void load(){
        earth = new Planet("earth", Planets.sun, 1f, 3){{
            generator = new EarthPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 8);
            launchCapacityMultiplier = 0.5f;
      			bloom = true;
      			visible = true;
      			accessible = true;
      			hasAtmosphere = true;
            alwaysUnlocked = true;
            sectorSeed = 3;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            defaultCore = LMDCores.coreNest;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.green;
                r.defaultTeam = Team.blue;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            iconColor = Color.valueOf("80A6FF");
            atmosphereColor = Color.valueOf("1b7c8f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
            hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};
        lazarus = new Planet("lazarus", earth, 1f, 1){{
            generator = new LazarusPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 8);
            launchCapacityMultiplier = 0.5f;
      			bloom = true;
      			visible = true;
      			accessible = true;
      			hasAtmosphere = true;
            alwaysUnlocked = true;
            sectorSeed = 2;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = true;
            allowLaunchSchematics = true;
            enemyCoreSpawnReplace = true;
            allowLaunchLoadout = true;
            defaultCore = LMDCores.coreNest;
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.blue;
                r.defaultTeam = Team.green;
                r.placeRangeCheck = false;
                r.showSpawns = false;
            };
            iconColor = Color.valueOf("4dff00");
            atmosphereColor = Color.valueOf("4dff00");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            alwaysUnlocked = true;
            landCloudColor = Pal.heal.cpy().a(0.5f);
            hiddenItems.addAll(Items.erekirItems).removeAll(Items.serpuloItems);
        }};
    }
}
