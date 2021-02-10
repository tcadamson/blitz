package dev.catcat.blitz;

import com.badlogic.ashley.core.ComponentMapper;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Quad;
import dev.catcat.blitz.component.Steer;
import dev.catcat.blitz.component.Transform;

public class Maps {
    public static final ComponentMapper<Transform> transform = ComponentMapper.getFor(Transform.class);
    public static final ComponentMapper<Collider> collider = ComponentMapper.getFor(Collider.class);
    public static final ComponentMapper<Steer> steer = ComponentMapper.getFor(Steer.class);
    public static final ComponentMapper<Quad> quad = ComponentMapper.getFor(Quad.class);
}
