package dev.catcat.blitz.system;

import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Transform;

@All({Transform.class, Quad.class})
public class Draw extends IteratingSystem {
    private Map<String, Color> colors;

    @Override
    protected void initialize() {
        colors = new HashMap<>();
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    protected void process(int id) {
        Color bg = colors.get("bg");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
