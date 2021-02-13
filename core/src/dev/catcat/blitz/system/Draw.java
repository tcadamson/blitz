package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    private AssetManager res;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Map<String, Color> colors;
    private Vector2 box;
    private ComponentMapper<Transform> tm;
    private ComponentMapper<Quad> qm;
    private final float SCALE = 0.15f;

    public Draw(AssetManager res, OrthographicCamera camera) {
        this.res = res;
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        colors = new HashMap<>();
        box = new Vector2();
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        Quad qc = qm.get(e);
        Color bg = colors.get("bg");
        TextureRegion region = res.get(qc.atlas, TextureAtlas.class).findRegion(qc.region);
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        box.set(region.getRegionWidth(), region.getRegionHeight()).scl(SCALE);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(colors.get("body"));
        batch.draw(region, tc.x - box.x/2, tc.y - box.y/2, box.x, box.y);
        batch.end();
    }
}
