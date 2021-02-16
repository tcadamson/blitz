package dev.catcat.blitz.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class Debug extends BaseSystem {
    private final Camera camera;
    private Box2DDebugRenderer debug;
    private Matrix4 matrix;
    private Physics physics;

    public Debug(Camera camera) {
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        debug = new Box2DDebugRenderer();
        matrix = new Matrix4();
    }

    @Override
    protected void processSystem() {
        matrix.set(camera.combined);
        debug.render(physics.getBox2DWorld(), matrix.scl(Physics.PPM));
    }
}
