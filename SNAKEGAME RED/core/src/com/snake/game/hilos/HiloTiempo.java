package com.snake.game.hilos;

import com.snake.game.eventos.Cronometro;
import com.snake.game.utiles.ConfiguracionesServer;

public class HiloTiempo extends Thread {
	private int tics = 0, tiempo = 0, segundos = 0;
	private boolean fin = false;
	private Cronometro cronometro;

	public HiloTiempo(Cronometro cronometro) {
		this.cronometro = cronometro;
		// le paso la clase cronometro para que acceda al update del mismo y me cambie
		// los valores
	}

	@Override
	public void run() {
		do {
			if (tics++ % 500000000 == 0) {
				tiempo++;
				if (tiempo % 1 == 0) {
					cronometro.update();
				}

				if (tiempo % 1 == 0) {
					ConfiguracionesServer.timerChoque++;

					if (ConfiguracionesServer.timerChoque >= 5) {
						ConfiguracionesServer.timerChoque = 5;
					}

					ConfiguracionesServer.timerChoque2++;

					if (ConfiguracionesServer.timerChoque2 >= 5) {
						ConfiguracionesServer.timerChoque2 = 5;
					}
				}

			}

		} while (!ConfiguracionesServer.finalizarHiloTiempo);
	}

	public int getSegundos() {
		return segundos;
	}
}
