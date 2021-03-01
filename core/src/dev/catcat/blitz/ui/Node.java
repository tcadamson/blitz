package dev.catcat.blitz.ui;

import com.badlogic.gdx.math.Vector2;

public interface Node {
    void update();
    void draw();
    Vector2 getPos();
    Vector2 getBox();
}
