package dev.catcat.blitz.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.maltaisn.msdfgdx.FontStyle;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.ui.Bound;
import dev.catcat.blitz.ui.Text;

public class Build implements Screen {
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;
    private static final FontStyle f1 = new FontStyle()
        .setColor(Colors.get("BLUE"))
        .setSize(FONT_LARGE);
    private static final FontStyle f2 = new FontStyle(f1).setSize(FONT_SMALL);
    private static final Bound bound = new Bound();
    private static final Text t1 = new Text(f1);
    private static final Text t2 = new Text(f2);

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bound.setBox(Camera.INSTANCE.getBox());
        Batch.INSTANCE.begin(Camera.INSTANCE.getCombined());
        t1.draw("SPINE", 0, 50);
        t2.draw("A fibrous, pointy attachment", 0, -50);
        Batch.INSTANCE.end();
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
}
