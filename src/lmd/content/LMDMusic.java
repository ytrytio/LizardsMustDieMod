package lmd.content;

import arc.audio.Music;
import arc.files.Fi;
import arc.struct.Seq;
import mindustry.Vars;

public class LMDMusic {
    public static Seq<Music> modBoss = new Seq<>();

    public static void load() {
        // Music categories: ambient, dark, boss.
        Fi musicRoot = Vars.mods.locateMod("lizards-must-die").root.child("music");
        for (var cat : musicRoot.list()) {
            for (var mFile : cat.findAll(f -> f.extEquals("ogg") || f.extEquals("mp3"))) {
                var music = loadMusic(cat.name() + "/" + mFile.nameWithoutExtension());
                switch (cat.name()) {
                    case "boss" -> modBoss.add(music);
                }
            }
        }
    }

    private static Music loadMusic(String name) {
        return Vars.tree.loadMusic(name);
    }
} 
