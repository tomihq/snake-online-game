package com.snake.game.elementos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.snake.game.utiles.Render;

public class Texto {

	public BitmapFont fuente;
	GlyphLayout glyphLayout;
	private float x = 0, y = 0;
	private String texto = ""; // Lo que le entrará por parametro
	
	public Texto() 
	{
		
	}

	public Texto(String rutaFuente, int dimension, Color color, boolean sombra) {

		generarTexto(rutaFuente, dimension, color, sombra);
	}

	public void generarTexto(String rutaFuente, int dimension, Color color, boolean sombra) {

		FreeTypeFontGenerator generadorFuente = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
		FreeTypeFontParameter parametrosFuente = new FreeTypeFontGenerator.FreeTypeFontParameter();
		// Los parametros nos permite cambiar la configuracion de la fuente.
		parametrosFuente.size = dimension;
		parametrosFuente.color = color;
		if (sombra) {
			parametrosFuente.shadowColor = Color.BLACK;
			parametrosFuente.shadowOffsetX = 1;
			parametrosFuente.shadowOffsetY = 1;
		}

		fuente = generadorFuente.generateFont(parametrosFuente);
		glyphLayout = new GlyphLayout();
		// generamos la fuente.
	}

	
	

	public void dibujar() {
		fuente.draw(Render.batch, texto, x, y); // Dibuja el texto
	}

	public void setTexto(String texto) {
		this.texto = texto;
		glyphLayout.setText(fuente, texto);
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setColor(Color color) {
		fuente.setColor(color);
	}

	public float getAncho() {

		return glyphLayout.width;
	}

	public float getAlto() {
		return glyphLayout.height;

	}

	public String getTexto() {
		
		return this.texto;
	}

}
