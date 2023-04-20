package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.eventos.Choque;

public class Tom extends Snake implements Choque{

	
	public Tom()
	{
		super.setTexture(new Texture("Objetos/serpienteTomi.png"));
		super.setNombreSerpiente("Tom");
		super.numeroPlayer = 1;
		super.setTexture2(new Texture("Objetos/serpienteTomiCabeza.png"));
	}

	@Override
	public void choqueSerpientes(Array<Vector2> posicion2, int j2) {
		
		
	}

}
