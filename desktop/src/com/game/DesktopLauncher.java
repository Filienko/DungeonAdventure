package com.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.game.GameEngine;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Drop");
		config.setWindowedMode(1280, 768);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("Dungeon Adventure");
		new Lwjgl3Application(new GameEngine(), config);
	}
}
