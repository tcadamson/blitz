package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Steer;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Collider.class, Steer.class})
public class Motion extends IteratingSystem {
    Controller controller;
    Vector2 dir;
    ComponentMapper<Collider> cm;
    ComponentMapper<Steer> sm;

    public Motion(Controller controller) {
        this.controller = controller;
    }

    @Override
    protected void initialize() {
        dir = new Vector2();
    }

    @Override
    protected void process(int e) {
        Collider cc = cm.get(e);
        Steer sc = sm.get(e);
        dir.set(controller.dirs());
        sc.dx = dir.x;
        sc.dy = dir.y;
        cc.body.applyForceToCenter(dir.scl(sc.thrust * cc.body.getMass()), true);
    }
}
