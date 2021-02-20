package dev.catcat.blitz.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;

public class Build implements Screen {
    private final AssetManager res;
    private final Camera camera;
    private final SpriteBatch batch;
    private final MsdfShader shader;
    private final FontStyle f1;
    private final FontStyle f2;
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;

    public Build(AssetManager res, Camera camera) {
        this.res = res;
        this.camera = camera;
        batch = new SpriteBatch();
        shader = new MsdfShader();
        f1 = new FontStyle()
            .setColor(Colors.get("BLUE"))
            .setSize(FONT_LARGE);
        f2 = new FontStyle(f1).setSize(FONT_SMALL);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        text(f1, "SPINE", 0, 50);
        text(f2, "A fibrous, pointy attachment", 0, -50);
        batch.end();
    }

    @Override
    public void resize(int w, int h) {
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

    private void text(FontStyle style, String str, int x, int y) {
        MsdfFont font = res.get("baloo.fnt");
        BitmapFont bm = font.getFont();
        batch.setShader(shader);
        shader.updateForFont(font, style);
        bm.getData().setScale(style.getSize() / font.getGlyphSize());
        bm.draw(batch, str, x, y);
        batch.setShader(null);
    }
}
