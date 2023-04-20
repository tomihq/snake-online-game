package com.snake.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.jugadores.JugadorBASE;
import com.snake.game.utiles.ConfiguracionesServer;
import com.snake.game.utiles.Render;

public class Comida {

	Texture texturaComida;
	float posX;
	float posY;

	public Comida() {
		this.texturaComida = new Texture("Objetos/comida.png");
	}

	public Texture getTexture() {
		return this.texturaComida;
	}

	public void setTexture(Texture texturaComida2) {
		this.texturaComida = texturaComida2;
	}

	public void update(float delta) {

	}

	public String getTextureString() {

		return texturaComida.toString();
	}

	public float getX() {

		return this.posX;
	}

	public float getY() {

		return this.posY;
	}

	public void setX(float x) {
		this.posX = x;
	}

	public void setY(float y) {
		this.posY = y;
	}

	

	public void spawnearComidaOnline(Vector2 posicionComida, JugadorBASE jugador1) {
		float posComidaX = posicionComida.x;
		float posComidaY = posicionComida.y;
	

		ConfiguracionesServer.hs.enviarMensajeParaTodos("spawneeComida/" + posComidaX + "/" + posComidaY);
	}

	public void dibujarComida(float posXComida, float posYComida) {
		final float posXComida2 = posXComida;
		final float posYComida2 = posYComida;
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				Render.batch.draw(new Texture(getTextureString()), posXComida2, posYComida2);
			}
		});

		setX(posXComida);
		setY(posYComida);

		ConfiguracionesServer.hs.enviarMensajeParaTodos("dibujeComida/" + getX() + "/" + getY());
	}
	// array.add(new Vector2(array.get(array.size - 1).x, array.get(array.size -
	// 1).y));
	// Este array.add dentro del if hacia que a veces se bugueen los valores al
	// dibujar la comida y cuando bajabamos a la pantalla crecia siempre la
	// serpiente
	// porque se agregaba una nueva parte tomando en cuenta el ultimo vlaor
	// restandole uno
}
