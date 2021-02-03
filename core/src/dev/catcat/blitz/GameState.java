package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Screen {
    private Camera camera;
    private Viewport viewport;
    private Map<String, Color> colors;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private TextureRegion player;
    private final float SCALE = 0.15f;

    GameState(Launcher game) {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        colors = new HashMap<>();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("test.atlas");
        player = atlas.findRegion("a");
        // TODO: import these from some external config file
        colors.put("bg", Color.valueOf("DCE0E0"));
        colors.put("body", Color.valueOf("1D3557"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Color bg = colors.get("bg");
        Color body = colors.get("body");
        Vector2 size = new Vector2(player.getRegionWidth(), player.getRegionHeight()).scl(SCALE);
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.setColor(body);
        batch.draw(player, camera.position.x - size.x / 2, camera.position.y - size.y / 2, size.x, size.y);
        batch.setColor(Color.WHITE);
        batch.end();
    }

    @Override
    public void resize(int w, int h) {
        viewport.update(w, h, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
