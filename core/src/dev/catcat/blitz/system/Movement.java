package dev.catcat.blitz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.Maps;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Steer;
import dev.catcat.blitz.component.Transform;

public class Movement extends IteratingSystem {
    private Controller controller;

    public Movement(Controller controller) {
        super(Family.all(Transform.class, Collider.class, Steer.class).get());
        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity e, float dt) {
        Collider cc = Maps.collider.get(e);
        Steer sc = Maps.steer.get(e);
        int thrust = 30;
        sc.dir.set(controller.dir());
        cc.body.applyForceToCenter(sc.dir.cpy().scl(thrust * cc.body.getMass()), true);
    }
}
