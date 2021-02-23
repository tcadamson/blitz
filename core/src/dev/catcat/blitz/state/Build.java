package dev.catcat.blitz.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Camera;

public class Build implements Screen {
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;
    private static final SpriteBatch batch = new SpriteBatch();
    private static final MsdfShader shader = new MsdfShader();
    private static final FontStyle f1 = new FontStyle()
        .setColor(Colors.get("BLUE"))
        .setSize(FONT_LARGE);
    private static final FontStyle f2 = new FontStyle(f1).setSize(FONT_SMALL);

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(Camera.INSTANCE.getCombined());
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

    private static void text(FontStyle style, String str, int x, int y) {
        MsdfFont data = Assets.INSTANCE.get("baloo.fnt", MsdfFont.class);
        BitmapFont font = data.getFont();
        batch.setShader(shader);
        shader.updateForFont(data, style);
        font.getData().setScale(style.getSize() / data.getGlyphSize());
        font.draw(batch, str, x, y);
        batch.setShader(null);
    }
}
