package dev.catcat.blitz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import dev.catcat.blitz.state.Build;
import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private static Game game;
	private static Build build;

	@Override
	public void create() {
		// TODO: import these from some external config file
		Colors.put("BG", Color.valueOf("DCE0E0"));
		Colors.put("FG", Color.valueOf("BDC1C6"));
		Colors.put("BLUE", Color.valueOf("1D3557"));
		Colors.put("RED", Color.valueOf("E63946"));
		Color bg = Colors.get("BG");
		Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
		game = new Game();
		build = new Build();
	}

	@Override
	public void render() {
		super.render();
		Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
		if (Assets.INSTANCE.update()) {
			setScreen(build);
		}
	}

	@Override
	public void resize(int w, int h) {
		Camera.INSTANCE.resize(w, h);
	}
}
