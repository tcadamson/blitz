package dev.catcat.blitz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import dev.catcat.blitz.Maps;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Transform;

public class Physics extends IteratingSystem {
    private World world;
    private Array<Entity> entities;
    private float accumulator = 0f;
    private final float DT = 1f/60f;
    private final int DX = 6;
    private final int DS = 2;
    private final int MAX_STEPS = 5;

    public Physics(World world) {
        super(Family.all(Transform.class, Collider.class).get());
        this.world = world;
        entities = new Array<>();
    }

    @Override
    protected void processEntity(Entity e, float dt) {
        entities.add(e);
    }

    @Override
    public void update(float dt) {
        // Source: https://www.unagames.com/blog/daniele/2010/06/fixed-time-step-implementation-box2d
        accumulator += dt;
        int steps = (int) Math.floor(accumulator / DT);
        if (steps > 0) {
            accumulator -= steps * DT;
        }
        for (int i = 0; i < Math.min(steps, MAX_STEPS); ++i) {
            for (Entity e : entities) {
                Maps.transform.get(e).pos.set(Maps.collider.get(e).body.getPosition());
            }
            world.step(DT, DX, DS);
        }
        world.clearForces();
        interpolate(accumulator / DT);
        entities.clear();
    }

    private void interpolate(float alpha) {
        for (Entity e : entities) {
            Transform tc = Maps.transform.get(e);
            Vector2 pos = Maps.collider.get(e).body.getPosition();
            float diff = 1f - alpha;
            tc.pos.set(pos.x * alpha + diff * tc.pos.x, pos.y * alpha + diff * tc.pos.y);
        }
    }
}
