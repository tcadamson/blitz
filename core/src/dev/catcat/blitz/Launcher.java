package dev.catcat.blitz;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private AssetManager res;
	private Game blitz;

	@Override
	public void create() {
		res = new AssetManager();
		blitz = new Game(res);
		// TODO: load these by crawling directory
		res.load("test.atlas", TextureAtlas.class);
		res.load("test.png", Texture.class);
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
}
