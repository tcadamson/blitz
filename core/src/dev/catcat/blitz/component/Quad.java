package dev.catcat.blitz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Quad implements Component {
    public Vector2 box = new Vector2();
    public TextureRegion region;
    public String color;
}
