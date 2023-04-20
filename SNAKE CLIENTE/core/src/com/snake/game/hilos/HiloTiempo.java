package com.snake.game.hilos;

import com.snake.game.eventos.Cronometro;
import com.snake.game.utiles.Configuraciones;

public class HiloTiempo extends Thread {
	private int tics = 0, tiempo = 0, segundos = 0;
	private Cronometro cronometro;

	public HiloTiempo(Cronometro cronometro) {
		this.cronometro = cronometro;
		//le paso la clase cronometro para que acceda al update del mismo y me cambie los valores
	}

	@Override
	public void run() {
		do {
			if (tics++ % 500000000 == 0) {
				tiempo++;
				if (tiempo % 2 == 0) {
// El valor %2 que puse es mas que nada aproximado, no son un segundo preciso pero se acerca demasiado a eso
					cronometro.update();
				}
			}
		} while (!Configuraciones.finalizarHiloTiempo);
	}

	public int getSegundos() {
		return segundos;
	}
}
