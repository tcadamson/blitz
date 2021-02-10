package dev.catcat.blitz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.Maps;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

public class Draw extends IteratingSystem {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Array<Entity> entities;
    private Map<String, Color> colors;
    private final float PPM;

    public Draw(OrthographicCamera camera, float PPM) {
        super(Family.all(Transform.class, Quad.class).get());
        this.camera = camera;
        this.PPM = PPM;
        batch = new SpriteBatch();
        entities = new Array<>();
        colors = new HashMap<>();
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    protected void processEntity(Entity e, float dt) {
        entities.add(e);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        Color bg = colors.get("bg");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (Entity e : entities) {
            Quad qc = Maps.quad.get(e);
            Vector2 box = qc.box;
            Vector2 pos = Maps.transform.get(e).pos.cpy().scl(PPM).sub(box.x/2, box.y/2);
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.setColor(colors.get(qc.color));
            batch.draw(qc.region, pos.x, pos.y, box.x, box.y);
            batch.end();
        }
        entities.clear();
    }
}
