package com.snake.game.jugadores;

import com.snake.game.utiles.ConfiguracionesServer;

public class Jugador2 extends JugadorBASE{

	public Jugador2(String nombre) {
		super(nombre);
		
	}

	@Override
	public void sumarPuntuacion(float puntuacion) {
		super.puntos+=puntuacion;
		ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/Puntaje/P2/" +super.puntos);
		
	}

	@Override
	public void restarPuntuacion(float puntuacion) {
		super.puntos -= puntuacion;
		if(super.puntos<0)
		{
			super.puntos = 0; 
		}
	
		ConfiguracionesServer.hs.enviarMensajeParaTodos("Restar/Puntaje/P2/" +super.puntos);
	
	}

}
