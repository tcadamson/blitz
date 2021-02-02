package dev.catcat.blitz;

import com.badlogic.gdx.Game;

public class Launcher extends Game {
	private GameState game;

	@Override
	public void create () {
		game = new GameState(this);
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
