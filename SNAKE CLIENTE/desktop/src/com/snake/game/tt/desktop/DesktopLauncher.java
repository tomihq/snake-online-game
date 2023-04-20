package com.snake.game.tt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.snake.game.tt.SnakeGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SnakeGame(), config);
		config.title = "Snake Game";
		config.resizable = false;
		config.fullscreen = false;
	}
}
