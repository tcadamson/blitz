package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;
import com.maltaisn.msdfgdx.widget.MsdfLabel;

public class Interface {
    private static final int FONT_LARGE = 80;
    private static final int FONT_SMALL = 43;
    private static final int FONT_LINE_HEIGHT = 75;
    private static final MsdfFont font = Assets.INSTANCE.get("baloo.fnt", MsdfFont.class);
    private static final FontStyle f1 = new FontStyle()
        .setFontName("baloo")
        .setColor(Colors.get("BLUE"))
        .setSize(FONT_LARGE);
    private static final FontStyle f2 = new FontStyle(f1).setSize(FONT_SMALL);
    private final Stage stage = new Stage();
    private final Skin skin = new Skin();
    private final Table root = new Table();

    public Interface() {
        stage.setViewport(Camera.INSTANCE.getViewport());
        stage.addActor(root);
        root.setFillParent(true);
        root.setDebug(true);
        skin.add("default", new MsdfShader());
        skin.add("baloo", font);
        font.getFont().getData().setLineHeight(FONT_LINE_HEIGHT);
    }

    public void update(float dt) {
        stage.act(dt);
        stage.draw();
    }

    public MsdfLabel title(String str) {
        return new MsdfLabel(str, skin, f1);
    }

    public MsdfLabel body(String str) {
        MsdfLabel body = new MsdfLabel(str, skin, f2);
        body.setWrap(true);
        return body;
    }

    public Table getRoot() {
        return root;
    }

    public void registerInput() {
        Gdx.input.setInputProcessor(stage);
    }
}
