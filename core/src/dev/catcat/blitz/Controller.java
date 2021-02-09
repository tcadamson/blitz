package dev.catcat.blitz;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class Controller extends InputAdapter {
    private final int KEYS = 256;
    private boolean[] pressed = new boolean[KEYS];
    private boolean[] released = new boolean[KEYS];
    private boolean[] down = new boolean[KEYS];

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

    public boolean pressed(String key) {
        return pressed[Input.Keys.valueOf(key)];
    }

    public boolean released(String key) {
        return released[Input.Keys.valueOf(key)];
    }

    public boolean down(String key) {
        return down[Input.Keys.valueOf(key)];
    }

    public void update() {
        for (int i = 0; i < KEYS; i++) {
            pressed[i] = false;
            released[i] = false;
        }
    }
}
