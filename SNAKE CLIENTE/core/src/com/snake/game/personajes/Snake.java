package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.eventos.Choque;
import com.snake.game.jugadores.JugadorBASE;
import com.snake.game.utiles.Configuraciones;

public abstract class Snake{

	protected Texture texture;
	protected String tecla;
	protected float posX;
	protected float posY;
	protected Array<Vector2> posicion;
	protected Array<Vector2> posicion2;
	protected String nombreSerpiente;
	protected boolean anchoExcedido = false;
	protected int numeroPlayer = 0;
	protected String ultimaTocada;
	protected int contador;
	protected Texture texture2;
	int j = 3;
	int j2 = 3;
	int posicionCorte; 
	int restaTamaño; 

	public Snake() {

		definirSerpiente(this.getTexture());

	}

	private void definirSerpiente(Texture texture) {
		this.texture = texture;
	}

	public void actualizarPosicionesOnline(Array<Vector2> posicion, JugadorBASE jugador,
			String tecla, float pos) {
		
		
		if (jugador.getNombre().equals("Tomi")) {
			movimientoPersonaje1(posicion, tecla, pos);
		} else {
			movimientoPersonaje2(posicion, tecla, pos);

		}


	}

	private void movimientoPersonaje1(Array<Vector2> posicion, String tecla, float pos) {
		
		for (int i = posicion.size - 1; i > 0; i--) {
			posicion.get(i).x = posicion.get(i - 1).x;
			posicion.get(i).y = posicion.get(i - 1).y;

		}
		
	
		
		
		
	
		// El for sirve para que empiece desde la ultima parte del cuerpo y vaya
		// moviendose hasta la primera.
		// Esto sirve para cuando al moverse entonces:
		// a I se le asigna el máximo cantidad de cabezas que tiene. Entonces, luego que
		// se resta, lo hará hasta que haya una ultima.
		// Por lo tanto, lo primero que hará es mover
//	Pos: 1, 15.0
//	Pos: 0, 15.0
//	Pos: 1, 15.0
//	Pos: 0, 30.0
//	Pos: 1, 30.0
//	Pos: 0, 45.0
//	Pos: 1, 45.0
//	Pos: 0, 60.0
		// Concluimos entonces, que la POSICION 0 hace primero el movimiento y luego la
		// posicion 1 (que seria parte del cuerpo) sigue a esta.
		
		if (tecla.equals("arriba")) {
			if (posicion.get(0).y < Configuraciones.ALTO + 20) {
				posicion.get(0).y = pos;
				setY(posicion.get(0).y);
			} else {
				posicion.get(0).y = 0;
				setY(posicion.get(0).y);
			}
		}
		
		if(tecla.equals("abajo"))
		{
			if(posicion.get(0).y>Configuraciones.ALTO-Configuraciones.ALTO)
			{
				posicion.get(0).y = pos; // Velocidad con la que sube hacia arriba. Al ser un ArrayList, en el indice 0
				// estaría X e Y de la serpiente ya que
				setY(posicion.get(0).y);
			}
			else {
				posicion.get(0).y = Configuraciones.ALTO + 20;
				setY(posicion.get(0).y);
			}
		}
		
		if(tecla.equals("izquierda"))
		{
			if(posicion.get(0).x>Configuraciones.ANCHO - Configuraciones.ANCHO)
			{
				
				posicion.get(0).x = pos; // Velocidad con la que sube hacia arriba. Al ser un ArrayList, en el indice 0
				// estaría X e Y de la serpiente ya que
				setX(posicion.get(0).x);
			}
			else {
				posicion.get(0).x = Configuraciones.ANCHO + 20;
				setX(posicion.get(0).x);
			}
		}
		
		if(tecla.equals("derecha"))
		{
			if(posicion.get(0).x<Configuraciones.ANCHO + 20)
			{
				posicion.get(0).x = pos;
				setX(posicion.get(0).x);
			}
			else {
				posicion.get(0).x = 0;
				setX(posicion.get(0).x);
			}
		}
		
		this.posicion = posicion; 
	

	}

	

	private void movimientoPersonaje2(Array<Vector2> posicion2, String tecla, float pos) {
		for (int i = posicion2.size - 1; i > 0; i--) {
			posicion2.get(i).x = posicion2.get(i - 1).x;
			posicion2.get(i).y = posicion2.get(i - 1).y;
			//System.out.println("Pos: " + (i - 1) + ", " + posicion2.get(i - 1).x);
		}
		this.posicion2 = posicion2;
		
		
		// El for sirve para que empiece desde la ultima parte del cuerpo y vaya
		// moviendose hasta la primera.
		// Esto sirve para cuando al moverse entonces:
		// a I se le asigna el máximo cantidad de cabezas que tiene. Entonces, luego que
		// se resta, lo hará hasta que haya una ultima.
		// Por lo tanto, lo primero que hará es mover
//		Pos: 1, 15.0
//		Pos: 0, 15.0
//		Pos: 1, 15.0
//		Pos: 0, 30.0
//		Pos: 1, 30.0
//		Pos: 0, 45.0
//		Pos: 1, 45.0
//		Pos: 0, 60.0
		// Concluimos entonces, que la POSICION 0 hace primero el movimiento y luego la
		// posicion 1 (que seria parte del cuerpo) sigue a esta.

		if (tecla.equals("arriba")) {
			if (posicion2.get(0).y < Configuraciones.ALTO + 20) {
				// Velocidad con la que sube hacia arriba. Al ser un ArrayList, en el indice 0
				// estaría X e Y de la serpiente ya que
				posicion2.get(0).y = pos;
				setY(posicion2.get(0).y); // lo definimos en la clase anterior
			} else {
				posicion2.get(0).y = 0;
				setY(posicion2.get(0).y);
			}
		} 
		if(tecla.equals("abajo"))
		{
			if(posicion2.get(0).y>Configuraciones.ALTO - Configuraciones.ALTO)
			{
				posicion2.get(0).y = pos;
				setY(posicion2.get(0).y);
				
			}
			else {
				posicion2.get(0).y = Configuraciones.ALTO + 20;
				setY(posicion2.get(0).y);
			}
		}
		
		if(tecla.equals("izquierda"))
		{
			if(posicion2.get(0).x>Configuraciones.ANCHO - Configuraciones.ANCHO)
			{
				posicion2.get(0).x = pos; // Velocidad con la que sube hacia arriba. Al ser un ArrayList, en el indice 0
				// estaría X e Y de la serpiente ya que
				setX(posicion2.get(0).x);
			}
			else {
				posicion2.get(0).x = Configuraciones.ANCHO + 20;
				setX(posicion2.get(0).x);
			}
		}
		
		if(tecla.equals("derecha"))
		{
			if(posicion2.get(0).x<Configuraciones.ANCHO + 20)
			{
				posicion2.get(0).x = pos;
				setX(posicion2.get(0).x);
			}
			else {
				posicion2.get(0).x = 0;
				setX(posicion2.get(0).x);
			}
		}
		 

		
	}

	public float getX() {
		return this.posX;
	}

	public void setX(float x) {
		this.posX = x;
	}

	public void setY(float y) {
		this.posY = y;
	}

	public float getY() {
		return this.posY;
	}

	public String getNombreSerpiente() {
		return nombreSerpiente;
	}

	public void setNombreSerpiente(String nombreSerpiente) {
		this.nombreSerpiente = nombreSerpiente;
	}

	public Texture getTexture() {

		return this.texture;
	}

	public String getTextureString() {
		return this.texture.toString();
	}

	public void setTexture(Texture texture) {
		this.texture = texture;

	}

	public void update(float dt, Array<Vector2> posicion, Array<Vector2> posicion2, JugadorBASE jugador, String tecla) {
		Configuraciones.timer -= dt;

	}

	public String getUltimaTocada() {
		return this.ultimaTocada;
	}

	public Array<Vector2> getArray() {
		return this.posicion;
	}
	public Array<Vector2> getArray2() {
		return this.posicion2;
	}

	public Integer getNumeroPlayer() {
		return this.numeroPlayer;
	}

	public int getContador() {

		return this.contador;
	}
	
	public void setTexture2(Texture texture)
	{
		this.texture2 = texture;
	}
	

	public String getTexturaString2() {
		
		return this.texture2.toString(); 
	}

	public void achicarSerpiente(float posicion4, float jugador) {
		float i = 0; 
	
		if(jugador==1)
		{
			 for(i=posicion4; i<posicion.size;i++)
		        {
		            posicion.removeIndex((int) i);
		            
		        }
		}
		else {
			
			for(i=posicion4; i<posicion2.size;i++)
	        {
	            posicion2.removeIndex((int) i);
	            
	        }
		}
		
	}

}
