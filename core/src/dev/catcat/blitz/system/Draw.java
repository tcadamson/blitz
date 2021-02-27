package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    protected ComponentMapper<Transform> tm;
    protected ComponentMapper<Quad> qm;

    @Override
    protected void inserted(int e) {
        Transform tc = tm.get(e);
        Quad qc = qm.get(e);
        Vector2 box = Assets.INSTANCE.getBox(qc.atlas, qc.id);
        tc.w = box.x;
        tc.h = box.y;
    }

    @Override
    protected void begin() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Batch.INSTANCE.begin(Camera.INSTANCE.getCombined());
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        Batch.INSTANCE.setColor(Colors.get("BLUE"));
        Batch.INSTANCE.draw(getRegion(qm.get(e)), tc.x - tc.w/2, tc.y - tc.h/2, tc.w, tc.h);
    }

    @Override
    protected void end() {
        Batch.INSTANCE.end();
    }

    private static TextureRegion getRegion(Quad qc) {
        return Assets.INSTANCE.get(qc.atlas, TextureAtlas.class).findRegion(qc.id);
    }
}
