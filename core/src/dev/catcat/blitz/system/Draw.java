package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    protected ComponentMapper<Transform> tm;
    protected ComponentMapper<Quad> qm;
    private final AssetManager res;
    private final Camera camera;
    private final Map<String, Color> colors;
    private SpriteBatch batch;

    public Draw(AssetManager res, Camera camera, Map<String, Color> colors) {
        this.res = res;
        this.camera = camera;
        this.colors = colors;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
    }

    @Override
    protected void inserted(int e) {
        Transform tc = tm.get(e);
        Quad qc = qm.get(e);
        TextureRegion region = res.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
        tc.w = region.getRegionWidth();
        tc.h = region.getRegionHeight();
    }

    @Override
    protected void begin() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        batch.setColor(colors.get("body"));
        batch.draw(getRegion(qm.get(e)), tc.x - tc.w/2, tc.y - tc.h/2, tc.w, tc.h);
    }

    @Override
    protected void end() {
        batch.end();
    }

    private TextureRegion getRegion(Quad qc) {
        return res.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
    }
}
