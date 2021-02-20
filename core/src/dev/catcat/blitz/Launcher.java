package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.maltaisn.msdfgdx.MsdfFont;
import com.maltaisn.msdfgdx.MsdfFontLoader;
import java.util.HashMap;
import java.util.Map;
import dev.catcat.blitz.state.Build;
import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private AssetManager res;
	private ExtendViewport viewport;
	private Game game;
	private Build build;

	@Override
	public void create() {
		Camera camera = new OrthographicCamera();
		Map<String, Color> colors = new HashMap<>();
		// TODO: import these from some external config file
		colors.put("bg", Color.valueOf("DCE0E0"));
		colors.put("body", Color.valueOf("1D3557"));
		colors.put("dark", Color.valueOf("E63946"));
		colors.put("red", Color.valueOf("BDC1C6"));
		Color bg = colors.get("bg");
		Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
		res = new AssetManager();
		viewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		game = new Game(res, camera, colors);
		build = new Build(res, camera, colors);
		// TODO: load these by crawling directory
		res.setLoader(MsdfFont.class, new MsdfFontLoader(new InternalFileHandleResolver()));
		res.load("out.atlas", TextureAtlas.class);
		res.load("out1.png", Texture.class);
		res.load("out2.png", Texture.class);
		res.load("baloo.fnt", MsdfFont.class);
	}

	@Override
	public void render() {
		super.render();
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
		if (res.update()) {
			setScreen(build);
		}
	}

	@Override
	public void resize(int w, int h) {
		viewport.update(w, h);
	}
}
