package com.snake.game.jugadores;

import com.snake.game.eventos.AumentarPuntuacion;
import com.snake.game.eventos.RestarPuntuacion;
import com.snake.game.personajes.SnakeServer;
import com.snake.game.utiles.ConfiguracionesServer;

public abstract class JugadorBASE implements AumentarPuntuacion, RestarPuntuacion{

	protected String nombre;
	protected SnakeServer serpienteJugador;
	protected float puntos;
	protected int i = 0;
	protected int d = 0; 
	protected int acum = 0; 

	public JugadorBASE(String nombre) {
		this.nombre = nombre;
	}

	public void setSnakeJugador(SnakeServer snake) {
		this.serpienteJugador = snake;
	}

	public SnakeServer getSerpienteJugador() {
		return this.serpienteJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPuntos(int puntos2) {
	
		this.puntos += puntos2;
	

		if(this.nombre.equals("Tomi"))
		{
			ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/Puntaje/P1/" +this.puntos);
		}
		else {
			ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/Puntaje/P2/" +this.puntos);
		}

	}

	public float getPuntos() {
		return this.puntos;
	}

}
