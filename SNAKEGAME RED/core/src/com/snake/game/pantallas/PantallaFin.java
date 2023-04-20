package com.snake.game.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.snake.game.elementos.Imagen;
import com.snake.game.elementos.Texto;
import com.snake.game.utiles.ConfiguracionesServer;
import com.snake.game.utiles.Render;
import com.snake.game.utiles.Recursos;

public class PantallaFin implements Screen {

	Imagen terminoPartida;
	public String nombreJugadorGanadorString;
	private float puntosRealizados;
	private Texto puntosRealizadosText;
	
	public PantallaFin(String nombreJugadorGanadorString, float puntosRealizados) {
		System.out.println(nombreJugadorGanadorString);
		this.nombreJugadorGanadorString = nombreJugadorGanadorString;
		this.puntosRealizados = puntosRealizados;
		crearTexto(); 
		crearImagenFondo();
		


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
			puntosRealizadosText.setPosition((ConfiguracionesServer.ANCHO/2.2f), (ConfiguracionesServer.ALTO-ConfiguracionesServer.ALTO+65));
		}
		else if(nombreJugadorGanadorString.equals("Tomi"))
		{
			terminoPartida = new Imagen(Recursos.FINPARTIDATOMI);
			puntosRealizadosText.setPosition((ConfiguracionesServer.ANCHO/2.2f), (ConfiguracionesServer.ALTO-ConfiguracionesServer.ALTO+65));
		}
		else {
			terminoPartida = new Imagen(Recursos.FINPARTIDAEMPATE);
		}
	
		terminoPartida.setSize(ConfiguracionesServer.ANCHO, ConfiguracionesServer.ALTO);
		terminoPartida.dibujar();

	}


	

	@Override
	public void render(float delta) {
		Render.limpiarPantalla();
	
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
