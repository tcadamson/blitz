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
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    protected ComponentMapper<Transform> tm;
    protected ComponentMapper<Quad> qm;
    private final AssetManager res;
    private final Camera camera;
    private SpriteBatch batch;
    private Map<String, Color> colors;

    public Draw(AssetManager res, Camera camera) {
        this.res = res;
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        colors = new HashMap<>();
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    protected void inserted(int e) {
        Quad qc = qm.get(e);
        TextureRegion region = res.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
        float scale = 0.15f;
        qc.w = region.getRegionWidth() * scale;
        qc.h = region.getRegionHeight() * scale;
    }

    @Override
    protected void begin() {
        Color bg = colors.get("bg");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        Quad qc = qm.get(e);
        batch.setColor(colors.get("body"));
        batch.draw(getRegion(qc), tc.x - qc.w/2, tc.y - qc.h/2, qc.w, qc.h);
    }

    @Override
    protected void end() {
        batch.end();
    }

    private TextureRegion getRegion(Quad qc) {
        return res.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
    }
}
