package com.snake.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.snake.game.elementos.Imagen;
import com.snake.game.elementos.Texto;
import com.snake.game.entradas.Entradas;
import com.snake.game.utiles.Configuraciones;
import com.snake.game.utiles.Recursos;
import com.snake.game.utiles.Render;


public class PantallaMenu implements Screen {

	Imagen fondoMenu;
	SpriteBatch sprite;
	SpriteBatch sprite2;
	Texto opciones[] = new Texto[2];
	String textos[] = {"JUGAR", "SALIR" };
	public int opcElegida = 1;
	public float tiempo = 0;
	boolean mouseArriba = false;
	Entradas teclas;
	String nombreJugador;
	ShapeRenderer SR; 


	@Override
	public void show() {

	fondoMenu = new Imagen(Recursos.FONDO);
	fondoMenu.setSize(Configuraciones.ANCHO, Configuraciones.ALTO); // Colocamos que la imagen de fondo tenga el ancho y alto de la pantalla
		sprite = Render.batch; // Decimos que va a haber un sprite pero NO se crea si no que se llama desde
								// render para no ocupar mas espacio de memoria.
		
		teclas = new Entradas();
		Gdx.input.setInputProcessor(teclas);
	SR = new ShapeRenderer(); 

		int avance = 30;
	
		
		for (int i = 0; i < opciones.length; i++) {
			opciones[i] = new Texto(Recursos.FUENTE, 50, Color.WHITE, true);
			opciones[i].setTexto(textos[i]);
			opciones[i].setPosition((Configuraciones.ANCHO/5) - (opciones[i].getAncho() / 2),
					((Configuraciones.ALTO / 1.7f) + (opciones[0].getAlto() / 2f))
							- ((opciones[i].getAlto() + avance) * i));
		}
		
	
	}

	@Override
	public void render(float delta) { // delta son los milisegundos que tarda en mostrarse por completo lo que tiene
										// la funcion
		if(Configuraciones.reinicioJuego)
		{
			sprite2 = new SpriteBatch(); 
			sprite = sprite2;
			
		}
		sprite.begin(); // Comenzamos creando un batch para que sepa que vamos a dibujar la pantalla
		fondoMenu.dibujar(); // Dibujamos el fondo menu!
		// Dibujamos lo que sea texto en la pantalla en este caso "JUGAR"
		for (int i = 0; i < opciones.length; i++) {
			opciones[i].dibujar();
		}
		sprite.end();

		tiempo += delta;

		// control del color de los textos del menu mediante mouse
		int cont = 0;
		for (int i = 0; i < opciones.length; i++) {
			if ((teclas.getMouseX() >= opciones[i].getX())
					&& (teclas.getMouseX() <= (opciones[i].getX() + opciones[i].getAncho()))) {
				
				if ((teclas.getMouseY() >= opciones[i].getY() - opciones[i].getAlto()-29)
						&& (teclas.getMouseY() <= opciones[i].getY()-29)) {
					opcElegida = i + 1;
				
					cont++;
				}
				
				//PUSE EL -29 porque empezaba 29 pixeles por encima del alto (no sé porque) entonces, ahora, ambos textos colocan bien el coloreado
				
				
			}
			else {
				mouseArriba = false;
				cont = 0; 
			}
			
			
		}
		
//		SR.begin(ShapeType.Line);
//		
//		for(int i=0;i<opciones.length;i++)
//		{
//			  SR.setColor(Color.RED); SR.rect(opciones[i].getX(), opciones[i].getY() -
//					  opciones[i].getAlto(), opciones[i].getAncho(), opciones[i].getAlto());
//		}
//		  
//		  SR.end(); //Referencias con el rectangulo en el texto
		 

		

		if (cont != 0) {
			mouseArriba = true;
		} else {
			mouseArriba = false;
		}

		if (teclas.isDown()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opcElegida++;
				if (opcElegida > 4) {
					opcElegida = 1;
				}
			}
		}

		if (teclas.isUp()) {
			if (tiempo > 0.1f) {
				tiempo = 0;
				opcElegida--;
				if (opcElegida < 1) {
					opcElegida = 4;
				}
			}
		}

		if(mouseArriba = true && cont!=0)
		{
			for (int i = 0; i < opciones.length; i++) {
				if (i == (opcElegida - 1)) {
					opciones[i].setColor(Color.PURPLE);
				} else {
					opciones[i].setColor(Color.WHITE);
				}
			}
		}
		else {
			for (int i = 0; i < opciones.length; i++) {
				if (i == (opcElegida - 1)) {
					opciones[i].setColor(Color.WHITE);
				} else {
					opciones[i].setColor(Color.WHITE);
				}
			}
		}
		

		if (teclas.isEnter() || (teclas.isClick()) && mouseArriba) {
			if (opcElegida == 1) {
				this.dispose();
	
				Render.app.setScreen(new PantallaJuego());
			} else {
				this.dispose();
				Gdx.app.exit();// sale del juego
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

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
