package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.eventos.Choque;
import com.snake.game.utiles.ConfiguracionesServer;

public class Tom extends SnakeServer implements Choque{

	
	public Tom()
	{
		super.setTexture(new Texture("Objetos/serpienteTomi.png"));
		super.setNombreSerpiente("Tom");
		super.numeroPlayer = 1;
		ConfiguracionesServer.addListener(this);
		super.setTexture2(new Texture("Objetos/serpienteTomiCabeza.png"));
		
	}

	@Override
	public void choqueSerpientes(Array<Vector2> posicion2, int j2) {
		for (int i = posicion2.size - 1; i > 3; i--) {

			if (((posicion2.get(0).x == posicion2.get(j2).x) && (posicion2.get(0).y == posicion2.get(j2).y))
					&& j2 < posicion2.size - 1 && (!ConfiguracionesServer.chocandoASiMismo2)) {
				ConfiguracionesServer.chocandoASiMismo2 = true;
				ConfiguracionesServer.timerChoque2 = 0; 
				ConfiguracionesServer.hs.enviarMensajeParaTodos("Serpiente/ChocaConSiMisma/P2");
				posicionCorte2 = i;

			}
			j2++;
			if (j2 >= posicion2.size - 1) {
				j2 = 3;
			}

		}

		if (ConfiguracionesServer.chocandoASiMismo2) {
			for (int i = posicionCorte2; i < posicion2.size; i++) {
				posicion2.removeIndex(i);
			}

			ConfiguracionesServer.hs.enviarMensajeParaTodos("AchicaSerpiente/P2/" + posicion2.size);
		}

		
	}

}
