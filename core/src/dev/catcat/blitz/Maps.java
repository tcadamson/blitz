package dev.catcat.blitz;

import com.badlogic.ashley.core.ComponentMapper;
import dev.catcat.blitz.component.Collider;
import dev.catcat.blitz.component.Transform;

public class Maps {
    public static final ComponentMapper<Transform> transform = ComponentMapper.getFor(Transform.class);
    public static final ComponentMapper<Collider> collider = ComponentMapper.getFor(Collider.class);
}
