package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    protected ComponentMapper<Transform> tm;
    protected ComponentMapper<Quad> qm;
    private SpriteBatch batch;

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
    }

    @Override
    protected void inserted(int e) {
        Transform tc = tm.get(e);
        Quad qc = qm.get(e);
        TextureRegion region = Assets.INSTANCE.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
        tc.w = region.getRegionWidth();
        tc.h = region.getRegionHeight();
    }

    @Override
    protected void begin() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(Camera.INSTANCE.getCombined());
        batch.begin();
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        batch.setColor(Colors.get("BLUE"));
        batch.draw(getRegion(qm.get(e)), tc.x - tc.w/2, tc.y - tc.h/2, tc.w, tc.h);
    }

    @Override
    protected void end() {
        batch.end();
    }

    private TextureRegion getRegion(Quad qc) {
        return Assets.INSTANCE.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
    }
}
