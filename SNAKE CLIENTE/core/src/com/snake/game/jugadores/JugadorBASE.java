package com.snake.game.jugadores;

import com.snake.game.eventos.AumentarPuntuacion;
import com.snake.game.personajes.Snake;

public abstract class JugadorBASE implements AumentarPuntuacion{

	protected String nombre;
	protected Snake serpienteJugador;
	protected float puntos;
	protected int i = 0;

	public JugadorBASE(String nombre) {
		this.nombre = nombre;
	}

	public void setSnakeJugador(Snake snake) {
		this.serpienteJugador = snake;
	}

	public Snake getSerpienteJugador() {
		return this.serpienteJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPuntos(float puntosASumar) {

		this.puntos += puntosASumar;

	}

	public float getPuntos() {
		return puntos;
	}

	public void setPuntosOnline(float puntos2) {
		this.puntos = puntos2;
		
	}

}
