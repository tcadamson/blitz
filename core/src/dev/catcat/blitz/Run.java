package dev.catcat.blitz;

import com.artemis.BaseSystem;
import com.artemis.SystemInvocationStrategy;
import com.badlogic.gdx.physics.box2d.World;
import dev.catcat.blitz.system.Physics;

public class Run extends SystemInvocationStrategy {
    private static final float DT = 1/240f;
    private static final int DX = 6;
    private static final int DS = 2;
    private static final int MAX_STEPS = 20;
    private static float accumulator;

    @Override
    protected void process() {
        BaseSystem[] systemsData = systems.getData();
        for (int i = 0, s = systems.size(); s > i; i++) {
            BaseSystem sys = systemsData[i];
            if (disabled.get(i))
                continue;
            if (sys instanceof Physics) {
                // Source: https://www.unagames.com/blog/daniele/2010/06/fixed-time-step-implementation-box2d
                Physics physics = (Physics) sys;
                World world = Physics.getBox2DWorld();
                accumulator += this.world.getDelta();
                int steps = (int) Math.floor(accumulator / DT);
                if (steps > 0) {
                    accumulator -= steps * DT;
                }
                for (int j = 0; j < Math.min(steps, MAX_STEPS); ++j) {
                    updateEntityStates();
                    physics.process();
                    world.step(DT, DX, DS);
                }
                world.clearForces();
                physics.interpolate(accumulator / DT);
            } else {
                updateEntityStates();
                sys.process();
            }
        }
        updateEntityStates();
    }
}
