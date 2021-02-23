package dev.catcat.blitz;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class Controller extends InputAdapter {
    private static final int KEYS = 256;
    private static final boolean[] pressed = new boolean[KEYS];
    private static final boolean[] released = new boolean[KEYS];
    private static final boolean[] down = new boolean[KEYS];

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

    private boolean handler(char key, boolean[] group) {
        return group[Input.Keys.valueOf(String.valueOf(key).toUpperCase())];
    }
}
