package dev.catcat.blitz.component;

import com.artemis.Component;

public class Steer extends Component {
    public float x;
    public float y;
    public float thrust;

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
