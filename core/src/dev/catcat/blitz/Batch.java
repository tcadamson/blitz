package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public enum Batch {
    INSTANCE;
    private final SpriteBatch batch = new SpriteBatch();

    public void begin(Matrix4 projection) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(projection);
        batch.begin();
    }

    public void end() {
        batch.end();
    }

    public void draw(TextureRegion region, float x, float y, float w, float h) {
        batch.draw(region, x, y, w, h);
    }

    public SpriteBatch getBatch() {
        // Only when expected as a function parameter, e.g. BitmapFont.draw
        // USE THE API
        return batch;
    }

    public void setShader(ShaderProgram shader) {
        batch.setShader(shader);
    }

    public void setColor(Color color) {
        batch.setColor(color);
    }
}
