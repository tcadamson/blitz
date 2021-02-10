package dev.catcat.blitz;

import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private Game blitz;

	@Override
	public void create() {
		blitz = new Game(this);
		setScreen(blitz);
	}

	@Override
	public void dispose() {
		blitz.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
