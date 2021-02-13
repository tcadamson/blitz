package dev.catcat.blitz.state;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.Launcher;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;
import dev.catcat.blitz.system.Draw;

public class Game implements Screen {
    private World world;
    private Box2DDebugRenderer debug;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Controller controller;
    private com.artemis.World ecs;
    private final float PPM = 100f;

    public Game(Launcher game) {
        world = new World(new Vector2(), true);
        debug = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        controller = new Controller();
        ecs = new com.artemis.World(new WorldConfigurationBuilder()
            .with(new Draw())
            .build());
        Archetype core = new ArchetypeBuilder()
            .add(Transform.class)
            .add(Quad.class)
            .build(ecs);
        int e = ecs.create(core);
        Quad q = ecs.getMapper(Quad.class).get(e);
        q.atlas = "test.atlas";
        q.region = "a";
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
        controller.update();
        ecs.setDelta(dt);
        ecs.process();
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
}
