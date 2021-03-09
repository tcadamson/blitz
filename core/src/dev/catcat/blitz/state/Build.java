package dev.catcat.blitz.state;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.Interface;

public class Build implements Screen {
    private static final int BOX_WIDTH = 450;
    private static final Interface ui = new Interface();

    @Override
    public void show() {
        Table root = ui.getRoot();
        root.add(ui.title("SPINE")).left();
        root.row();
        root.add(ui.body("A fibrous, pointy attachment, effective in all close quarters combat")).width(BOX_WIDTH);
        ui.registerInput();
    }

    @Override
    public void render(float dt) {
        // Batch isn't actually needed here, but begin calls glClear which we do need
        Batch.INSTANCE.begin(Camera.INSTANCE.getCombined());
        ui.update(dt);
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
