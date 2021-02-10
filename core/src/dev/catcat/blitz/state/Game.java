package dev.catcat.blitz.state;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.Launcher;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Steer;
import dev.catcat.blitz.component.Transform;
import dev.catcat.blitz.system.Draw;
import dev.catcat.blitz.system.Movement;
import dev.catcat.blitz.system.Physics;

public class Game implements Screen {
    private World world;
    private Box2DDebugRenderer debug;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Controller controller;
    private PooledEngine ecs;
    private Array<EntitySystem> sys;
    private final float PPM = 100f;

    public Game(Launcher game) {
        world = new World(new Vector2(), true);
        debug = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        controller = new Controller();
        ecs = new PooledEngine();
        sys = new Array<>();
        sys.add(new Draw(camera, PPM));
        sys.add(new Movement(controller));
        sys.add(new Physics(world));
        for (int i = 0; i < sys.size; i++) {
            EntitySystem s = sys.get(i);
            s.priority = i;
            ecs.addSystem(s);
        }
        init(ecs.createEntity());
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
        controller.update();
        ecs.update(dt);
        debug.render(world, camera.combined.cpy().scl(PPM));
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
        Steer sc = ecs.createComponent(Steer.class);
        Quad qc = ecs.createComponent(Quad.class);
        CircleShape shape = new CircleShape();
        TextureAtlas atlas = new TextureAtlas("test.atlas");
        BodyDef def = new BodyDef();
        float damp = 20f;
        float scale = 0.15f;
        qc.region = atlas.findRegion("a");
        qc.color = "body";
        qc.box.set(qc.region.getRegionWidth(), qc.region.getRegionHeight()).scl(scale);
        shape.setRadius(qc.box.len()/2/PPM);
        def.type = BodyDef.BodyType.DynamicBody;
        def.linearDamping = damp;
        cc.body = world.createBody(def);
        cc.body.createFixture(shape, 1);
        cc.body.setTransform(tc.pos.x, tc.pos.y, 0);
        cc.body.setUserData(e);
        shape.dispose();
        e.add(cc);
        e.add(tc);
        e.add(sc);
        e.add(qc);
        ecs.addEntity(e);
    }
}
