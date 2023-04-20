package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.eventos.Choque;

public class Tomi extends Snake implements Choque{
	
	public Tomi()
	{
		
		super.setTexture(new Texture("Objetos/serpienteTom.png"));
		super.setNombreSerpiente("Tomi");
		super.numeroPlayer = 1;
		super.setTexture2(new Texture("Objetos/serpienteTomCabeza.png"));
	}

	@Override
	public void choqueSerpientes(Array<Vector2> posicion2, int j2) {
		// TODO Auto-generated method stub
		
	}
}
