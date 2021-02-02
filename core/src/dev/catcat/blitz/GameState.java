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
import java.util.HashMap;
import java.util.Map;

public class GameState implements Screen {
    private Camera camera;
    private Map<String, Color> colors;
    private Vector2 box;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private TextureRegion player;

    GameState(Launcher game) {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        colors = new HashMap<>();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("test.atlas");
        player = atlas.findRegion("4");
        camera.position.scl(1 / 2);
        colors.put("bg", Color.valueOf("DCE0DA"));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Color bg = colors.get("bg");
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(player, camera.position.x - player.getRegionWidth() / 2, camera.position.y - player.getRegionHeight() / 2);
        batch.end();
    }

    @Override
    public void resize(int w, int h) {
        box = new Vector2(w, h);
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
