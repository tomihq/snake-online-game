package com.snake.game.jugadores;

public class Jugador1 extends JugadorBASE{

	public Jugador1(String nombre) {
		super(nombre);
		
	}
	
	@Override
	public void sumarPuntuacion(float puntuacion) {
		super.puntos=puntuacion;
		
	}

}
