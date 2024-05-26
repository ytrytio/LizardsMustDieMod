package lmd.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.entities.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;

public class LMDFx{
    public static final Rand rand = new Rand();
    public static final Vec2 u = new Vec2(), v = new Vec2();

    public static final Effect


    blockCrash = new Effect(90f, e -> {
        Block block = Blocks.router;

        alpha(e.fin() + 0.5f);
        float offset = Mathf.lerp(0f, 180f, e.fout());
        color(0f, 0f, 0f, 0.44f);
        rect(block.fullIcon, e.x - offset * 4f, e.y, (float)block.size * 8f, (float)block.size * 8f);
        color(Color.white);
        rect(block.fullIcon, e.x + offset, e.y + offset * 5f, (float)block.size * 8f, (float)block.size * 8f);
    }),
    dynamicWave = new Effect(30, e -> {
        color(e.color, 0.7f);
        Lines.stroke(e.fout() * 2f);
        Lines.circle(e.x, e.y, 4f + e.finpow() * e.rotation);
    }),
    spiralRotatin = new Effect(1500, e -> {
        color(Pal.bulletYellowBack);
        Vec2 prp1 = new Vec2(e.x,e.y), prp2 = new Vec2(e.x,e.y);
        for(int j = 0; j<600; j++){
            int i = j%3;
            float t = i / 50 * Mathf.PI;
            float dx = t * Mathf.cosDeg(t) * e.fin();
            float dy = t * Mathf.sinDeg(t) * e.fout();
            Lines.line(prp1.x, prp1.y, e.x + dx, e.y + dy);prp1.set(e.x + dx, e.y + dy);
            Lines.line(prp2.x, prp2.y, e.x + dy, e.y + dx);prp2.set(e.x + dy, e.y + dx);
            Lines.line(prp1.x, prp1.y, e.x - dx, e.y - dy);prp1.set(e.x - dx, e.y - dy);
            Lines.line(prp2.x, prp2.y, e.x - dy, e.y - dx);prp2.set(e.x - dy, e.y - dx);
            Lines.poly(e.x, e.y, 8, 5, 0);}
    }){{followParent = false;}},
    waitWhat = new Effect(50, e -> {
        Lines.beginLine();
        int sides = Lines.circleVertices(50);
        float space = 360f / sides;
        for(int i = 0; i < sides; i++){
            float a = space * i;
            u.trns(e.rotation,
            50 * Mathf.cosDeg(a),
            50 * Mathf.sinDeg(a)
            );
            Lines.linePoint(e.x + u.x, e.y + u.y);
        }
        Lines.endLine(true);
    }),
    idkLol = new MultiEffect(Fx.shootSmallColor, new Effect(9, e -> {
        color(Color.white, e.color, e.fin());
        Lines.stroke(0.7f + e.fout());
        Lines.square(e.x, e.y, e.fin() * 5f, e.rotation + 45f);

        Drawf.light(e.x, e.y, 23f, e.color, e.fout() * 0.7f);
    })),

    godPower = new Effect(50, e -> {
        color(Pal.bulletYellowBack);
        Lines.stroke(e.foutpowdown());
        Lines.poly(e.x, e.y, Lines.circleVertices(32), 32, 0);
        int segments = 360/6;
        for(int i = 0;i<6;i++){
            float ang = 90*e.fin() + segments*i;
            Lines.poly(e.x+Mathf.cosDeg(ang+segments*e.fout()/2)*28, e.y+Mathf.sinDeg(ang+segments*e.fout()/2)*28, 3, 3, ang+segments*e.fout()/2+180);
            Lines.poly(e.x+Mathf.cosDeg(ang)*36, e.y+Mathf.sinDeg(ang)*36, 3, 3, ang);
        }
    })
{{followParent = false;}};


}
