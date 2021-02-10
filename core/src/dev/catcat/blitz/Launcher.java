package dev.catcat.blitz;

import dev.catcat.blitz.state.Game;

public class Launcher extends com.badlogic.gdx.Game {
	private Game game;

	@Override
	public void create() {
		game = new Game(this);
		setScreen(game);
	}

	@Override
	public void dispose() {
		game.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
