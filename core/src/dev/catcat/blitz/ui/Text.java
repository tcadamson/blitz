package dev.catcat.blitz.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.maltaisn.msdfgdx.FontStyle;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfShader;
import dev.catcat.blitz.Assets;
import dev.catcat.blitz.Batch;

public class Text {
    private static final MsdfShader shader = new MsdfShader();
    private final FontStyle style;

    public Text(FontStyle style) {
        this.style = style;
    }

    public void draw(String str, float x, float y) {
        MsdfFont data = Assets.INSTANCE.get("baloo.fnt", MsdfFont.class);
        BitmapFont font = data.getFont();
        Batch.INSTANCE.setShader(shader);
        shader.updateForFont(data, style);
        font.getData().setScale(style.getSize() / data.getGlyphSize());
        font.draw(Batch.INSTANCE.getBatch(), str, x, y);
        Batch.INSTANCE.setShader(null);
    }
}
