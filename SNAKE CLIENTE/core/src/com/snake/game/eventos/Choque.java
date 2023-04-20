package com.snake.game.eventos;

import java.util.EventListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public interface Choque extends EventListener{
	
	public abstract void choqueSerpientes(Array<Vector2> posicion2, int j2); 

}
