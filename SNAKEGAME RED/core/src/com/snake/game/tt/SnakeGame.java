package com.snake.game.tt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snake.game.pantallas.PantallaInicio;
import com.snake.game.pantallas.PantallaJuegoServer;
import com.snake.game.pantallas.PantallaMenu;
import com.snake.game.utiles.Render;

public class SnakeGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create() {
		Render.app = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaInicio());	
		}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
	}
}
