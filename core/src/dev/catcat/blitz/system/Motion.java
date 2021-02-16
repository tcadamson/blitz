package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Steer;

@All({Collider.class, Steer.class})
public class Motion extends IteratingSystem {
    protected ComponentMapper<Collider> cm;
    protected ComponentMapper<Steer> sm;
    private Vector2 dir;

    @Override
    protected void initialize() {
        dir = new Vector2();
    }

    @Override
    protected void process(int e) {
        Collider cc = cm.get(e);
        Steer sc = sm.get(e);
        cc.body.applyForceToCenter(dir.set(sc.x, sc.y).nor().scl(sc.thrust * cc.body.getMass()), true);
    }
}
