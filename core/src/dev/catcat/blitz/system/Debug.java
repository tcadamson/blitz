package dev.catcat.blitz.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import dev.catcat.blitz.Camera;

public class Debug extends BaseSystem {
    private Box2DDebugRenderer debug;
    private Matrix4 combined;

    @Override
    protected void initialize() {
        debug = new Box2DDebugRenderer();
        combined = new Matrix4();
    }

    @Override
    protected void processSystem() {
        debug.render(Physics.getBox2DWorld(), combined.set(Camera.INSTANCE.getCombined()).scl(Physics.PPM));
    }
}
