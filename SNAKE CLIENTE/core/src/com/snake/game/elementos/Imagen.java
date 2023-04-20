package com.snake.game.elementos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.snake.game.utiles.Render;

public class Imagen {

	private Texture tex;
	private Sprite spr;
	private float x = 0, y = 0;
	private float ancho;
	private int alto; 
	GlyphLayout glyphLayout = new GlyphLayout();

	public Imagen(String direccion) {
		tex = new Texture(direccion);
		spr = new Sprite(tex);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void dibujar() {
		spr.draw(Render.batch);
	}

	public void setSize(float ancho, int alto) {
		spr.setSize(ancho, alto); // Seteamos que todas las imagenes tengan el ancho y alto de la pantalla.
		this.ancho = ancho;
		this.alto = alto;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		spr.setPosition(x, y);
	}

	public float getAncho() {

		return ancho;
	}

	public float getAlto() {
		return alto;

	}

		public void setTransparencia(float transparencia) {
		spr.setAlpha(transparencia);
	}
	

}
