package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Screen {
    private World world;
    private Box2DDebugRenderer debug;
    private Camera camera;
    private Viewport viewport;
    private Map<String, Color> colors;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private TextureRegion quad;
    private Vector2 size;
    private Vector2 dir;
    private Vector2 pos1;
    private Vector2 pos2;
    private Vector2 pos3;
    private Body collider;
    private float accumulator;
    private final float PPM = 100f;
    private final float SCALE = 0.15f;
    private final float MAX = 0.25f;
    private final float DT = 1/60f;
    private final int DX = 6;
    private final int DS = 2;
    private final int DAMP = 20;
    private final int THRUST = 25;

    GameState(Launcher game) {
        world = new World(new Vector2(), true);
        debug = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        colors = new HashMap<>();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("test.atlas");
        quad = atlas.findRegion("a");
        size = new Vector2(quad.getRegionWidth(), quad.getRegionHeight()).scl(SCALE);
        dir = new Vector2();
        pos1 = new Vector2();
        pos2 = new Vector2();
        pos3 = new Vector2();
        collider = circle(size.len());
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int key) {
                switch(key) {
                    case Input.Keys.W:
                        dir.add(0, 1);
                        break;
                    case Input.Keys.S:
                        dir.add(0, -1);
                        break;
                    case Input.Keys.A:
                        dir.add(-1, 0);
                        break;
                    case Input.Keys.D:
                        dir.add(1, 0);
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int key) {
                switch(key) {
                    case Input.Keys.W:
                        dir.add(0, -1);
                        break;
                    case Input.Keys.S:
                        dir.add(0, 1);
                        break;
                    case Input.Keys.A:
                        dir.add(1, 0);
                        break;
                    case Input.Keys.D:
                        dir.add(-1, 0);
                        break;
                }
                return true;
            }
        });
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Color bg = colors.get("bg");
        Color body = colors.get("body");
        Vector2 draw = pos2.cpy().scl(PPM).sub(size.x/2, size.y/2);
        Gdx.graphics.setTitle(Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(body);
        batch.draw(quad, draw.x, draw.y, size.x, size.y);
        batch.end();
        debug.render(world, camera.combined.scl(PPM));
        collider.applyForceToCenter(dir.cpy().scl(THRUST * collider.getMass()), true);
        accumulator += Math.min(delta, MAX);
        while (accumulator >= DT) {
            pos3.set(collider.getPosition());
            pos1.set(pos2);
            world.step(DT, DX, DS);
            accumulator -= DT;
        }
        interpolate(accumulator / DT);
        camera.update();
    }

    @Override
    public void resize(int w, int h) {
        viewport.update(w, h);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private Body circle(float d) {
        CircleShape shape = new CircleShape();
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearDamping = DAMP;
        Body body = world.createBody(def);
        shape.setRadius(d/2/PPM);
        body.createFixture(shape, 1);
        body.setTransform(0, 0, 0);
        shape.dispose();
        return body;
    }

    private void interpolate(float alpha) {
        pos2.set(pos3.x * alpha + pos1.x * (1f - alpha), pos3.y * alpha + pos1.y * (1f - alpha));
        // Alternative that doesn't seem to work too well...
        //pos2.set(Interpolation.linear.apply(pos1.x, pos3.x, alpha), Interpolation.linear.apply(pos1.y, pos3.y, alpha));
    }
}
