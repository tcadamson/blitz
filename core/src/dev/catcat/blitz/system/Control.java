package dev.catcat.blitz.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.Controller;
import dev.catcat.blitz.component.Steer;

@All(Steer.class)
public class Control extends IteratingSystem {
    private static final Map<String, Integer> axes = new HashMap<>();
    private static final Controller controller = new Controller();
    protected ComponentMapper<Steer> sm;

    @Override
    protected void initialize() {
        Gdx.input.setInputProcessor(controller);
        // TODO: import these from some external config file
        axes.put("wy", 1);
        axes.put("sy", -1);
        axes.put("ax", -1);
        axes.put("dx", 1);
    }

    @Override
    protected void process(int e) {
        controller.update();
        float x = 0;
        float y = 0;
        for (Map.Entry<String, Integer> axis : axes.entrySet()) {
            String k = axis.getKey();
            int v = axis.getValue();
            char c1 = k.charAt(0);
            char c2 = k.charAt(1);
            if (controller.down(c1)) {
                x += c2 == 'x' ? v : 0;
                y += c2 == 'y' ? v : 0;
            }
        }
        sm.get(e).set(x, y);
    }
}
