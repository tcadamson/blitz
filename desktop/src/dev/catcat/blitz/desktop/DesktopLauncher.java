package dev.catcat.blitz.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import dev.catcat.blitz.Launcher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("blitz");
		config.setWindowedMode(1480, 720);
		new Lwjgl3Application(new Launcher(), config);
	}
}
