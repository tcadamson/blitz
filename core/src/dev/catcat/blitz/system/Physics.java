package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Collider.class})
public class Physics extends IteratingSystem {
    World world;
    Vector2 pos;
    ComponentMapper<Transform> tm;
    ComponentMapper<Collider> cm;
    private final float PPM = 100f;

    public Physics(World world) {
        this.world = world;
    }

    @Override
    protected void initialize() {
        pos = new Vector2();
    }

    @Override
    protected void inserted(int e) {
        Transform tc = tm.get(e);
        Collider cc = cm.get(e);
        BodyDef def = new BodyDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(cc.r/PPM);
        def.linearDamping = cc.damp;
        def.type = BodyDef.BodyType.DynamicBody;
        cc.body = world.createBody(def);
        cc.body.createFixture(shape, 1);
        cc.body.setTransform(tc.x, tc.y, 0);
        cc.body.setUserData(e);
        shape.dispose();
    }

    @Override
    protected void process(int e) {
        Transform tc = tm.get(e);
        pos.set(cm.get(e).body.getPosition()).scl(PPM);
        tc.x = pos.x;
        tc.y = pos.y;
    }

    public World getBox2DWorld() {
        return world;
    }

    public void interpolate(float alpha) {
        IntBag entities = subscription.getEntities();
        int[] ids = entities.getData();
        for (int i = 0; i < entities.size(); i++) {
            int e = ids[i];
            float diff = 1 - alpha;
            Transform tc = tm.get(ids[i]);
            pos.set(cm.get(e).body.getPosition().scl(PPM));
            tc.set(pos.x * alpha + diff * tc.x, pos.y * alpha + diff * tc.y);
        }
    }
}
