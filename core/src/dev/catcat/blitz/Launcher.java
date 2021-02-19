package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private static final float SCALE = 4f;
	private AssetManager res;
	private ExtendViewport viewport;
	private Game blitz;

	@Override
	public void create() {
		Camera camera = new OrthographicCamera();
		res = new AssetManager();
		viewport = new ExtendViewport(Gdx.graphics.getWidth() * SCALE, Gdx.graphics.getHeight() * SCALE, camera);
		blitz = new Game(res, camera);
		// TODO: load these by crawling directory
		res.load("out.atlas", TextureAtlas.class);
		res.load("out1.png", Texture.class);
		res.load("out2.png", Texture.class);
	}

	@Override
	public void dispose() {
		blitz.dispose();
	}

	@Override
	public void render() {
		super.render();
		if (res.update()) {
			setScreen(blitz);
		}
	}

	@Override
	public void resize(int w, int h) {
		viewport.update(w, h);
	}
}
