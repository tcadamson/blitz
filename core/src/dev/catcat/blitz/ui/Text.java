package dev.catcat.blitz.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Batch;

public class Text implements Node {
    private static final MsdfShader shader = new MsdfShader();
    private static final GlyphLayout layout = new GlyphLayout();
    private final Vector2 pos = new Vector2();
    private final Vector2 box = new Vector2();
    private final FontStyle style;
    private final String str;
    private final int w;

    public Text(FontStyle style, String str, int w) {
        this.style = style;
        this.str = str;
        this.w = w;
    }

    @Override
    public void update() {
        resolveLayout();
        box.set(layout.width, layout.height);
    }

    @Override
    public void draw() {
        MsdfFont data = Assets.INSTANCE.get("baloo.fnt", MsdfFont.class);
        Batch.INSTANCE.setShader(shader);
        shader.updateForFont(data, style);
        resolveLayout();
        data.getFont().draw(Batch.INSTANCE.getBatch(), layout, pos.x, pos.y);
        Batch.INSTANCE.setShader(null);
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Vector2 getBox() {
        return box;
    }

    private void resolveLayout() {
        MsdfFont data = Assets.INSTANCE.get("baloo.fnt", MsdfFont.class);
        BitmapFont font = data.getFont();
        font.getData().setScale(style.getSize() / data.getGlyphSize());
        layout.setText(font, str, style.getColor(), w, Align.left, true);
    }
}
