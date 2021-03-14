package dev.catcat.blitz.state;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;
import dev.catcat.blitz.Interface;

public class Build implements Screen {
    private static final int ICONS = 3;
    private static final int ICONS_PAD = 25;
    private static final int PANEL_PAD = 60;
    private static final int LABEL_WIDTH = 450;
    private static final int LABEL_HEIGHT = 200;
    private static final Interface ui = new Interface();

    @Override
    public void show() {
        Table root = ui.getRoot();
        Table panel = new Table();
        Table icons = new Table();
        ui.registerInput();
        ui.registerImage(root, "m1", Colors.get("FG")).expand();
        for (int i = 1; i <= ICONS; i++) {
            ui.registerButton(icons, "i" + i).padRight(ICONS_PAD);
        }
        panel.add(icons).left().padBottom(PANEL_PAD);
        panel.row();
        panel.add(ui.title("SPINE")).left();
        panel.row();
        panel.add(ui.body("A fibrous, pointy attachment, effective in all close quarters combat")).width(LABEL_WIDTH).height(LABEL_HEIGHT);
        root.add(panel).right().pad(PANEL_PAD);
        ui.registerImage(root, "t1", Colors.get("RED")).bottom();
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
