package com.snake.game.eventos;


public class Cronometro {

	protected int tiempoJuego = 40
			;

	public float getTiempoJuego() {
		return this.tiempoJuego;
	}

	public void setTiempoJuego(float tiempoGastado) {
		if (this.tiempoJuego <= 0) {
			this.tiempoJuego = 0;
		}

		if (this.tiempoJuego >= 0) {
			this.tiempoJuego -= tiempoGastado;
		}
	}

	public void update() {
		this.setTiempoJuego(1f);

	}

}
