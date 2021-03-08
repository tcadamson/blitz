package dev.catcat.blitz.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;
import com.maltaisn.msdfgdx.widget.MsdfLabel;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Batch;
import dev.catcat.blitz.Camera;

public class Build implements Screen {
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;
    private static final int FONT_WIDTH = 450;
    private static final int FONT_LINE_HEIGHT = 75;
    private static final Stage ui = new Stage();
    private static final FontStyle f1 = new FontStyle()
        .setFontName("baloo")
        .setColor(Colors.get("BLUE"))
        .setSize(FONT_LARGE);
    private static final FontStyle f2 = new FontStyle(f1).setSize(FONT_SMALL);

    @Override
    public void show() {
        Table root = new Table();
        Skin theme = new Skin();
        Gdx.input.setInputProcessor(ui);
        theme.add("default", new MsdfShader());
        theme.add("baloo", Assets.INSTANCE.get("baloo.fnt", MsdfFont.class));
        theme.get("baloo", MsdfFont.class).getFont().getData().setLineHeight(FONT_LINE_HEIGHT);
        ui.setViewport(Camera.INSTANCE.getViewport());
        ui.addActor(root);
        MsdfLabel t1 = new MsdfLabel("SPINE", theme, f1);
        MsdfLabel t2 = new MsdfLabel("A fibrous, pointy attachment, effective in all close quarters combat", theme, f2);
        t2.setWrap(true);
        root.setFillParent(true);
        root.setDebug(true);
        root.add(t1).left();
        root.row();
        root.add(t2).width(FONT_WIDTH);
    }

    @Override
    public void render(float dt) {
        // Batch isn't actually needed here, but begin calls glClear which we do need
        // UI in general will probably need a refactor later
        Batch.INSTANCE.begin(Camera.INSTANCE.getCombined());
        ui.act(dt);
        ui.draw();
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
