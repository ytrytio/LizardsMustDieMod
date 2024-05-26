package lmd;

import arc.*;
import arc.audio.Music;
import arc.struct.Seq;
//import arc.util.*;
//import mindustry.*;
//import mindustry.content.*;
import mindustry.game.EventType.*;
//import mindustry.gen.*;
//import mindustry.ui.dialogs.*;
import mindustry.mod.Mod;
import lmd.content.blocks.*;
import lmd.content.*;
import mindustry.Vars;
import mindustry.audio.SoundControl;
import mindustry.core.GameState;

public class LizardsMustDie extends Mod{ 
    public Seq<Music> vBoss;

    /** A reference to SoundControl instance for easier access. */
    public SoundControl control;

    public LizardsMustDie(){}
    @Override
    public void init(){
        
        Events.on(ClientLoadEvent.class, e -> {
            Vars.ui.showOkText((Core.bundle.get("disclaimerHead")), (Core.bundle.get("disclaimerBody")), () -> {});
        });

        
        // sectorname = *SectorInfo.name;
        // if(sectorname == "sector_name") {
        //
        // }

        LMDMusic.load();

        control = Vars.control.sound;

        Events.on(MusicRegisterEvent.class, e -> {
            vBoss = control.bossMusic.copy();
        });

        Events.on(WorldLoadEvent.class, e -> {
            if (Vars.state.rules.planet == LMDPlanets.earth) {
                control.bossMusic = LMDMusic.modBoss;
            }
        });

        Events.on(StateChangeEvent.class, e -> {
            if (e.from != GameState.State.menu && e.to == GameState.State.menu) {
                control.bossMusic = vBoss;
            }
        });
    }

    @Override
    public void loadContent(){
        LMDItems.load();
        LMDWalls.load();
        LMDUnits.load();
        LMDCores.load();
        LMDPlanets.load();
        LMDEnvironment.load();
    }
}