package com.snake.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snake.game.utiles.Render;
import com.snake.game.utiles.Recursos;
import com.snake.game.elementos.Imagen;
import com.snake.game.utiles.ConfiguracionesServer; 

public class PantallaInicio implements Screen {

	SpriteBatch sprite;
	float cont = 0;
	Imagen logo;
	boolean terminaFadeIn = false, terminaFadeOut = false;
	float tiempoEspera = 5f, tiempo = 0f, fade = 0.009f;


	@Override
	public void show() {
		
		sprite = Render.batch;
	//	Recursos.cargarTema(Recursos.MUSICAMENU);	
		//Recursos.musica.start();
		int avance = 10;
	

		logo = new Imagen(Recursos.LOGO);
		logo.setSize(300, 200);
		logo.setTransparencia(cont);
		logo.setPosition((ConfiguracionesServer.ANCHO/2) - (logo.getAncho() / 2)+avance,
				((ConfiguracionesServer.ALTO / 2) + (logo.getAlto() / 2)) - ((logo.getAlto() + avance) * 1));
	}

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		sprite.begin();
		logo.dibujar();
		sprite.end();
		
		if (!terminaFadeIn) {
			cont += fade;

			if (cont > 1) {
				cont = 1;
				terminaFadeIn = true;
			}
		} else {
			tiempo += 0.1f;
			if (tiempo >= tiempoEspera) {
				cont -= fade;
				if (cont < 0) {
					cont = 0;
					tiempo = 0;
					terminaFadeOut = true;
				}
			}
		}

		if (!terminaFadeOut) {
			logo.setTransparencia(cont);
		} else if (terminaFadeOut) {
			tiempo+=0.5f;
			if (tiempo > tiempoEspera) {
				this.dispose();
				Render.app.setScreen(new PantallaMenu());
				
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		

	}

}
