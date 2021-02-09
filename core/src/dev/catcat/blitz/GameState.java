package dev.catcat.blitz;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Transform;
import dev.catcat.blitz.system.Physics;

public class GameState implements Screen {
    private World world;
    private Box2DDebugRenderer debug;
    private Camera camera;
    private Viewport viewport;
    private Controller controller;
    private Map<String, Color> colors;
    private PooledEngine ecs;
    private final float PPM = 100f;

    GameState(Launcher game) {
        world = new World(new Vector2(), true);
        debug = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        controller = new Controller();
        colors = new HashMap<>();
        ecs = new PooledEngine();
        ecs.addSystem(new Physics(world));
        init(ecs.createEntity());
        Gdx.input.setInputProcessor(controller);
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Color bg = colors.get("bg");
        Gdx.graphics.setTitle(Integer.toString(Gdx.graphics.getFramesPerSecond()) + " FPS");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debug.render(world, camera.combined.cpy().scl(PPM));
        ecs.update(dt);
        controller.update();
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

    private void init(Entity e) {
        Transform tc = ecs.createComponent(Transform.class);
        Collider cc = ecs.createComponent(Collider.class);
        CircleShape shape = new CircleShape();
        BodyDef def = new BodyDef();
        int r = 50;
        shape.setRadius(r/PPM);
        def.type = BodyDef.BodyType.DynamicBody;
        cc.body = world.createBody(def);
        cc.body.createFixture(shape, 1);
        cc.body.setTransform(tc.pos.x, tc.pos.y, 0);
        cc.body.setUserData(e);
        shape.dispose();
        e.add(cc);
        e.add(tc);
        ecs.addEntity(e);
    }
}
