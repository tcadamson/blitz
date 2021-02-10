package dev.catcat.blitz;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import java.util.Map;

public class Controller extends InputAdapter {
    private Map<String, Integer> axes;
    private boolean[] pressed;
    private boolean[] released;
    private boolean[] down;
    private final int KEYS = 256;

    public Controller() {
        axes = new HashMap<>();
        pressed = new boolean[KEYS];
        released = new boolean[KEYS];
        down = new boolean[KEYS];
        axes.put("wy", 1);
        axes.put("sy", -1);
        axes.put("ax", -1);
        axes.put("dx", 1);
    }

    @Override
    public boolean keyDown(int key) {
        pressed[key] = !down[key];
        down[key] = true;
        return false;
    }

    @Override
    public boolean keyUp(int key) {
        released[key] = down[key];
        down[key] = false;
        return false;
    }

    public boolean down(char key) {
        return handler(key, down);
    }

    public boolean pressed(char key) {
        return handler(key, pressed);
    }

    public boolean released(char key) {
        return handler(key, released);
    }

    public void update() {
        for (int i = 0; i < KEYS; i++) {
            pressed[i] = false;
            released[i] = false;
        }
    }

    public Vector2 dir() {
        float x = 0;
        float y = 0;
        for (Map.Entry<String, Integer> axis : axes.entrySet()) {
            String k = axis.getKey();
            int v = axis.getValue();
            char c1 = k.charAt(0);
            char c2 = k.charAt(1);
            if (down(c1)) {
                x += c2 == 'x' ? v : 0;
                y += c2 == 'y' ? v : 0;
            }
        }
        return new Vector2(x, y).nor();
    }

    private boolean handler(char key, boolean[] group) {
        return group[Input.Keys.valueOf(String.valueOf(key).toUpperCase())];
    }
}
