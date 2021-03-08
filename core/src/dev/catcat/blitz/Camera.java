package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public enum Camera {
    INSTANCE;
    private final OrthographicCamera camera = new OrthographicCamera();
    private final ExtendViewport viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
    private final Vector2 box = new Vector2();

    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    public ExtendViewport getViewport() {
        // Only for registering with UI scene graph
        return viewport;
    }

    public Matrix4 getCombined() {
        return camera.combined;
    }
}
