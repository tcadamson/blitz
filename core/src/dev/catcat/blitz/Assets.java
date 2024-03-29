package dev.catcat.blitz;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfFontLoader;

public enum Assets {
    INSTANCE;
    private final AssetManager res = new AssetManager();
    private final Vector2 box = new Vector2();

    Assets() {
        // TODO: load these by crawling directory
        res.setLoader(MsdfFont.class, new MsdfFontLoader(new InternalFileHandleResolver()));
        res.load("out.atlas", TextureAtlas.class);
        res.load("out1.png", Texture.class);
        res.load("out2.png", Texture.class);
        res.load("baloo.fnt", MsdfFont.class);
    }

    public <T> T get(String file, Class<T> type) {
        return res.get(file, type);
    }

    public boolean update() {
        return res.update();
    }

    public Vector2 getBox(String atlas, String id) {
        TextureRegion region = get(atlas, TextureAtlas.class).findRegion(id);
        float scale = 0.25f;
        return box.set(region.getRegionWidth(), region.getRegionHeight()).scl(scale);
    }
}
