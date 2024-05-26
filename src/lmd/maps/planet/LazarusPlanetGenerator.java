package lmd.maps.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.ai.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import lmd.content.blocks.*;

import static mindustry.Vars.*;

public class LazarusPlanetGenerator extends PlanetGenerator{
    public float heightScl = 0.9f, octaves = 8, persistence = 0.7f, heightPow = 3f, heightMult = 1.6f;

    //TODO inline/remove
    public static float arkThresh = 0.28f, arkScl = 0.83f;
    public static int arkSeed = 7, arkOct = 2;
    public static float liqThresh = 0.64f, liqScl = 87f, redThresh = 3.1f, noArkThresh = 0.3f;
    public static int crystalSeed = 8, crystalOct = 2;
    public static float crystalScl = 0.9f, crystalMag = 0.3f;
    public static float airThresh = 0.13f, airScl = 14;

    Block[] terrain = {Blocks.arkyicStone, Blocks.arkyicStone, Blocks.arkyicStone, Blocks.arkyicStone, Blocks.carbonStone, Blocks.beryllicStone, Blocks.beryllicStone, Blocks.carbonStone};

    {
        baseSeed = 2;
        defaultLoadout = Loadouts.basicBastion;
    }

    @Override
    public void generateSector(Sector sector){
        //no bases right now
    }

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = getBlock(position);

        //more obvious color
        if(block == Blocks.carbonStone) block = Blocks.beryllicStone;
        //TODO this might be too green
        //if(block == Blocks.beryllicStone) block = Blocks.arkyicStone;

        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public float getSizeScl(){
        //TODO should sectors be 600, or 500 blocks?
        return 2000 * 1.07f * 6f / 5f;
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    float rawTemp(Vec3 position){
        return position.dst(0, 0, 1)*2.2f - Simplex.noise3d(seed, 8, 0.54f, 1.4f, 10f + position.x, 10f + position.y, 10f + position.z) * 2.9f;
    }

    Block getBlock(Vec3 position){
        float ice = rawTemp(position);
        Tmp.v32.set(position);

        float height = rawHeight(position);
        Tmp.v31.set(position);
        height *= 1.2f;
        height = Mathf.clamp(height);

        Block result = terrain[Mathf.clamp((int)(height * terrain.length), 0, terrain.length - 1)];

        if(ice < 0.3 + Math.abs(Ridged.noise3d(seed + crystalSeed, position.x + 4f, position.y + 8f, position.z + 1f, crystalOct, crystalScl)) * crystalMag){
            return Blocks.carbonStone;
        }

        if(ice < 0.6){
            if(result == Blocks.beryllicStone || result == Blocks.carbonStone || result == Blocks.arkyicStone){
                //TODO bio(?) luminescent stuff? ice?
                return Blocks.carbonStone; //TODO perhaps something else.
            }
        }

        position = Tmp.v32;

        //TODO tweak this to make it more natural
        //TODO edge distortion?
        if(ice < redThresh - noArkThresh && Ridged.noise3d(seed + arkSeed, position.x + 2f, position.y + 8f, position.z + 1f, arkOct, arkScl) > arkThresh){
            //TODO arkyic in middle
            result = Blocks.beryllicStone;
        }

        return result;
    }

    @Override
    public void genTile(Vec3 position, TileGen tile){
        tile.floor = getBlock(position);

        if(tile.floor == Blocks.beryllicStone && rand.chance(0.01)){
            tile.floor = Blocks.beryllicStone;
        }

        tile.block = tile.floor.asFloor().wall;

        if(Ridged.noise3d(seed + 1, position.x, position.y, position.z, 2, airScl) > airThresh){
            tile.block = Blocks.air;
        }

        //TODO only certain places should have carbon stone...
        if(Ridged.noise3d(seed + 2, position.x, position.y + 4f, position.z, 3, 6f) > 0.6){
            tile.floor = Blocks.carbonStone;
        }
    }

    @Override
    protected void generate(){
        float temp = rawTemp(sector.tile.v);

        if(temp > 0.7){

            pass((x, y) -> {
                if(floor != Blocks.redIce){
                    float noise = noise(x + 782, y, 7, 0.8f, 280f, 1f);
                    if(noise > 0.62f){
                        if(noise > 0.635f){
                            floor = LMDEnvironment.lizardsWater;
                        }else{
                            floor = Blocks.carbonStone;
                        }
                        ore = Blocks.air;
                    }

                    //TODO this needs to be tweaked
                    if(noise > 0.55f && floor == Blocks.beryllicStone){
                        floor = Blocks.carbonStone;
                    }
                }
            });
        }

        cells(4);

        //regolith walls for more dense terrain
        pass((x, y) -> {
            if(floor == Blocks.carbonStone && noise(x, y, 3, 0.4f, 13f, 1f) > 0.59f){
                block = Blocks.carbonWall;
            }
        });

        //TODO: yellow regolith biome tweaks
        //TODO ice biome

        float length = width/2.6f;
        Vec2 trns = Tmp.v1.trns(rand.random(360f), length);
        int
        spawnX = (int)(trns.x + width/2f), spawnY = (int)(trns.y + height/2f),
        endX = (int)(-trns.x + width/2f), endY = (int)(-trns.y + height/2f);
        float maxd = Mathf.dst(width/2f, height/2f);

        erase(spawnX, spawnY, 15);
        brush(pathfind(spawnX, spawnY, endX, endY, tile -> (tile.solid() ? 300f : 0f) + maxd - tile.dst(width/2f, height/2f)/10f, Astar.manhattan), 9);
        erase(endX, endY, 15);

        //arkycite
        pass((x, y) -> {
            if(floor != Blocks.beryllicStone) return;

            //TODO bad
            if(Math.abs(noise(x, y + 500f, 5, 0.6f, 40f, 1f) - 0.5f) < 0.09f){
                floor = Blocks.arkyicStone;
            }

            if(nearWall(x, y)) return;

            float noise = noise(x + 300, y - x*1.6f + 100, 4, 0.8f, liqScl, 1f);

            if(noise > liqThresh){
                floor = Blocks.arkyicStone;
            }
        });

        median(2, 0.6, Blocks.arkyicStone);

        blend(Blocks.arkyicStone, Blocks.arkyicStone, 4);

        //TODO may overwrite floor blocks under walls and look bad
        blend(LMDEnvironment.lizardsWater, Blocks.carbonStone, 4);

        distort(10f, 12f);
        distort(5f, 7f);

        //does arkycite need smoothing?
        median(2, 0.6, Blocks.arkyicStone);

        //smooth out slag to prevent random 1-tile patches
        median(3, 0.6, LMDEnvironment.lizardsWater);

        pass((x, y) -> {


            float max = 0;
            for(Point2 p : Geometry.d8){
                //TODO I think this is the cause of lag
                max = Math.max(max, world.getDarkness(x + p.x, y + p.y));
            }
            if(max > 0){
                block = floor.asFloor().wall;
                if(block == Blocks.air) block = Blocks.beryllicStoneWall;
            }

            if(floor == Blocks.carbonStone && noise(x + 78 + y, y, 3, 0.8f, 6f, 1f) > 0.44f){
                floor = Blocks.carbonStone;
            }
        });

        pass((x, y) -> {
          if (block == Blocks.sandWall)
            block = Blocks.air;
        });

        inverseFloodFill(tiles.getn(spawnX, spawnY));

        //TODO veins, blend after inverse flood fill?
        blend(Blocks.beryllicStoneWall, Blocks.beryllicStone, 4);

        //make sure enemies have room
        erase(endX, endY, 6);

        //TODO enemies get stuck on 1x1 passages.

        tiles.getn(endX, endY).setOverlay(Blocks.spawn);

        //ores
        pass((x, y) -> {

            if(block != Blocks.air){
                if(nearAir(x, y)){
                    if(block == Blocks.carbonWall && noise(x + 78, y, 4, 0.7f, 33f, 1f) > 0.52f){
                        block = Blocks.graphiticWall;
                    }else if(block != Blocks.carbonWall && noise(x + 782, y, 4, 0.8f, 38f, 1f) > 0.665f){
                        ore = Blocks.wallOreBeryllium;
                    }

                }
            }else if(!nearWall(x, y)){

                if(noise(x + 150, y + x*2 + 100, 4, 0.8f, 55f, 1f) > 0.76f){
                    ore = Blocks.oreTungsten;
                }

                //TODO design ore generation so it doesn't overlap
                if(noise(x + 999, y + 600 - x, 4, 0.63f, 45f, 1f) < 0.27f && floor == Blocks.carbonStone){
                    ore = Blocks.oreCrystalThorium;
                }

            }

            if(noise(x + 999, y + 600 - x, 5, 0.8f, 45f, 1f) < 0.44f && floor == Blocks.carbonStone){
                floor = Blocks.beryllicStone;
            }


            if(block == Blocks.arkyicWall && rand.chance(0.23) && nearAir(x, y) && !near(x, y, 3, Blocks.crystalOrbs)){
                block = Blocks.crystalOrbs;
                ore = Blocks.air;
            }

            //TODO test, different placement
            //TODO this biome should have more blocks in general
            if(block == Blocks.carbonWall && rand.chance(0.3) && nearAir(x, y) && !near(x, y, 3, Blocks.crystalOrbs)){
                block = Blocks.crystalOrbs;
                ore = Blocks.air;
            }
        });

        //remove props near ores, they're too annoying
        pass((x, y) -> {
            if(ore.asFloor().wallOre || block.itemDrop != null || (block == Blocks.air && ore != Blocks.air)){
                removeWall(x, y, 3, b -> b instanceof TallBlock);
            }
        });

        trimDark();



        for(Tile tile : tiles){
            if(tile.overlay().needsSurface && !tile.floor().hasSurface()){
                tile.setOverlay(Blocks.air);
            }
        }

        decoration(0.017f);

        //it is very hot
        state.rules.env = sector.planet.defaultEnv;
        state.rules.placeRangeCheck = true;

        //TODO remove slag and arkycite around core.
        Schematics.placeLaunchLoadout(spawnX, spawnY);

        //all sectors are wave sectors
        state.rules.waves = false;
        state.rules.showSpawns = true;
    }
}
