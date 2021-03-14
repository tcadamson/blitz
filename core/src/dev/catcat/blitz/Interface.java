package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
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
        skin.addRegions(Assets.INSTANCE.get("out.atlas", TextureAtlas.class));
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
        body.setAlignment(Align.topLeft);
        return body;
    }

    public Table getRoot() {
        return root;
    }

    public void registerInput() {
        Gdx.input.setInputProcessor(stage);
    }

    public Cell<?> registerImage(Table table, String id, Color color) {
        Image image = new Image(skin.getSprite(id));
        image.setColor(color);
        return resized(table.add(image), id);
    }

    public Cell<?> registerButton(Table table, String id) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = skin.newDrawable(id, Colors.get("BLUE"));
        style.over = skin.newDrawable(id, Colors.get("RED"));
        return resized(table.add(new ImageButton(style)), id);
    }

    private Cell<?> resized(Cell<?> cell, String id) {
        Vector2 box = Assets.INSTANCE.getBox("out.atlas", id);
        return cell.size(box.x, box.y);
    }
}
