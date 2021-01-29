package dev.catcat.blitz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dev.catcat.blitz.Launcher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "blitz";
		config.width = 1480;
		config.height = 720;
		new LwjglApplication(new Launcher(), config);
	}
}
