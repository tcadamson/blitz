package dev.catcat.blitz.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Transform extends Component {
    public float x;
    public float y;
    public float w;
    public float h;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 pos) {
        x = pos.x;
        y = pos.y;
    }
}
