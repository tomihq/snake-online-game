package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.eventos.Choque;
import com.snake.game.utiles.ConfiguracionesServer;

public class Tomi extends SnakeServer implements Choque {

	public Tomi() {

		super.setTexture(new Texture("Objetos/serpienteTom.png"));
		super.setNombreSerpiente("Tomi");
		super.numeroPlayer = 1;
		ConfiguracionesServer.addListener(this);
		super.setTexture2(new Texture("Objetos/serpienteTomCabeza.png"));
	}

	@Override
	public void choqueSerpientes(Array<Vector2> posicion, int j) {
		for (int i = posicion.size - 1; i > 3; i--) {
			if (((posicion.get(0).x == posicion.get(j).x) && (posicion.get(0).y == posicion.get(j).y))
					&& j < posicion.size - 1 && (!ConfiguracionesServer.chocandoASiMismo)) {
				ConfiguracionesServer.chocandoASiMismo = true;
				ConfiguracionesServer.timerChoque = 0; 
				ConfiguracionesServer.hs.enviarMensajeParaTodos("Serpiente/ChocaConSiMisma/P1");
				posicionCorte = i;
			}
			j++;
			if (j >= posicion.size - 1) {
				j = 3;
			}
		}
		if (ConfiguracionesServer.chocandoASiMismo) {
			for (int i = posicionCorte; i < posicion.size; i++) {
				posicion.removeIndex(i);
			}
			ConfiguracionesServer.hs.enviarMensajeParaTodos("AchicaSerpiente/P1/" + posicion.size);
		}
	}
}
