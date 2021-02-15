package dev.catcat.blitz.state;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.Run;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Steer;
import dev.catcat.blitz.component.Transform;
import dev.catcat.blitz.system.Debug;
import dev.catcat.blitz.system.Draw;
import dev.catcat.blitz.system.Motion;
import dev.catcat.blitz.system.Physics;

public class Game implements Screen {
    private World world;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Controller controller;
    private com.artemis.World ecs;

    public Game(AssetManager res) {
        world = new World(new Vector2(), true);
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        controller = new Controller();
        ecs = new com.artemis.World(new WorldConfigurationBuilder()
            .with(
                new Draw(res, camera),
                new Debug(camera),
                new Motion(controller),
                new Physics(world)
            )
            .register(new Run())
            .build());
        Archetype core = new ArchetypeBuilder()
            .add(Transform.class)
            .add(Collider.class)
            .add(Quad.class)
            .add(Steer.class)
            .build(ecs);
        Gdx.input.setInputProcessor(controller);
        // TODO: create a more sophisticated entity builder
        int e = ecs.create(core);
        Collider cc = ecs.getMapper(Collider.class).get(e);
        Quad qc = ecs.getMapper(Quad.class).get(e);
        Steer sc = ecs.getMapper(Steer.class).get(e);
        qc.atlas = "test.atlas";
        qc.region = "a";
        cc.r = 50f;
        cc.damp = 20f;
        sc.thrust = 30f;
        world.setAutoClearForces(false);
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
