package dev.catcat.blitz.state;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Colors;
import com.maltaisn.msdfgdx.FontStyle;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.ui.Table;
import dev.catcat.blitz.ui.Text;

public class Build implements Screen {
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;
    private static final int FONT_WIDTH = 300;
    private static final FontStyle f1 = new FontStyle()
        .setColor(Colors.get("BLUE"))
        .setSize(FONT_LARGE);
    private static final FontStyle f2 = new FontStyle(f1).setSize(FONT_SMALL);
    private static final Table root = new Table();
    private static final Text t1 = new Text(f1, "SPINE", FONT_WIDTH);
    private static final Text t2 = new Text(f2, "A fibrous, pointy attachment", FONT_WIDTH);

    public Build() {
        root.add(t1);
        root.add(t2);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
        Batch.INSTANCE.begin(Camera.INSTANCE.getCombined());
        root.update();
        root.draw();
        Batch.INSTANCE.end();
    }

    @Override
    public void resize(int w, int h) {
        root.setBox(Camera.INSTANCE.getBox());
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
