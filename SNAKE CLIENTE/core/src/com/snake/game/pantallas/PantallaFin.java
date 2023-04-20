package com.snake.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.snake.game.elementos.Imagen;
import com.snake.game.elementos.Texto;
import com.snake.game.entradas.Entradas;
import com.snake.game.utiles.Configuraciones;
import com.snake.game.utiles.Render;
import com.snake.game.utiles.Recursos;

public class PantallaFin implements Screen {

	Imagen terminoPartida;
	private String textos[] = { "FIN DEL JUEGO", "EL GANADOR ES:" };
	private Texto[] opcion = new Texto[2];
	public String nombreJugadorGanadorString;
	private float puntosRealizados;
	private Entradas teclas;
	private Texto puntosRealizadosText; 

	public PantallaFin(String nombreJugadorGanadorString, float puntosRealizados) {

		this.nombreJugadorGanadorString = nombreJugadorGanadorString;
		this.puntosRealizados = puntosRealizados;
		Configuraciones.finalizarJuego = true;
		teclas = new Entradas();
		crearTexto(); 
		crearImagenFondo();
		
;

	}

	private void crearTexto() {
		puntosRealizadosText = new Texto(Recursos.FUENTE, 50, Color.WHITE, true);
		if(!(String.valueOf(puntosRealizados).toString().isEmpty()))
		{
			puntosRealizadosText.setTexto(String.valueOf(puntosRealizados));
		}
	
		
	}

	private void crearImagenFondo() {
		if(nombreJugadorGanadorString.equals("Tom"))
		{
			terminoPartida = new Imagen(Recursos.FINPARTIDATOM);
			puntosRealizadosText.setPosition((Configuraciones.ANCHO/2.2f), (Configuraciones.ALTO-Configuraciones.ALTO+65));
		}
		else if(nombreJugadorGanadorString.equals("Tomi"))
		{
			terminoPartida = new Imagen(Recursos.FINPARTIDATOMI);
			puntosRealizadosText.setPosition((Configuraciones.ANCHO/2.2f), (Configuraciones.ALTO-Configuraciones.ALTO+65));
		}
		else {
			terminoPartida = new Imagen(Recursos.FINPARTIDAEMPATE);
		}
	
		terminoPartida.setSize(Configuraciones.ANCHO, Configuraciones.ALTO);
		terminoPartida.dibujar();

	}




	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
		

		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			teclas.setR(true);
			Configuraciones.hc.enviarMensaje("Aprete la R");
		}
		puntosRealizadosText.dibujar();
		terminoPartida.dibujar();
		
	}

	@Override
	public void resize(int width, int height) {

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

	@Override
	public void show() {

	}

}
