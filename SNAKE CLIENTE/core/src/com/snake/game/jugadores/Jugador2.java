package com.snake.game.jugadores;

public class Jugador2 extends JugadorBASE{

	public Jugador2(String nombre) {
		super(nombre);
		
	}

	@Override
	public void sumarPuntuacion(float puntuacion) {
		super.puntos=puntuacion;
		
	}

}
