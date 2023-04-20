package com.snake.game.personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.entradas.Entradas;
import com.snake.game.eventos.Choque;
import com.snake.game.jugadores.JugadorBASE;
import com.snake.game.utiles.ConfiguracionesServer;

public abstract class SnakeServer {

	protected Texture texture;
	protected Entradas entradas = new Entradas();
	protected String tecla;
	protected float posX;
	protected float posY;
	protected Array<Vector2> posicion;
	protected Array<Vector2> posicion2;
	protected String nombreSerpiente;
	protected boolean anchoExcedido = false;
	protected int numeroPlayer = 0;
	protected String ultimaTecla;
	protected int contador = 0;
	protected int contador2 = 0;
	protected boolean estaMoviendo = false;
	protected Texture textura2;
	protected int posicionCorte = 0;
	protected int posicionCorte2 = 0;
	protected int j = 3;
	protected int j2 = 3;

	public int getContador2() {
		return contador2;
	}

	public void setContador2(int contador2) {
		this.contador2 = contador2;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public SnakeServer() {
		definirSerpiente(this.getTexture());
	}

	private void definirSerpiente(Texture texture) {
		this.texture = texture;
	}

	public void setTexture2(Texture texture) {
		this.textura2 = texture;
	}

	public String getTexturaString2() {
		return this.textura2.toString();

	}

	protected void actualizarPosiciones(Array<Vector2> posicion, JugadorBASE jugador, String tecla) {

		if (jugador.getNombre().equals("Tomi")) {
			movimientoPersonaje1(posicion, tecla, jugador);
		}else {
			movimientoPersonaje2(posicion, tecla, jugador);
		}

	}

	private void movimientoPersonaje1(Array<Vector2> posicion, String tecla, JugadorBASE jugador) {
		for (int i = posicion.size - 1; i > 0; i--) {
			posicion.get(i).x = posicion.get(i - 1).x;
			posicion.get(i).y = posicion.get(i - 1).y;

		}
	
		if (j >= posicion.size - 1) {
			j = 3;
		}

		if (posicion.size > 3 && jugador.getPuntos()>=25 && ConfiguracionesServer.timerChoque==5) {
			for (int i = 0; i < ConfiguracionesServer.getListeners().size(); i++) {
				if(i==0) {
					((Tomi) ConfiguracionesServer.getListeners().get(i)).choqueSerpientes(posicion, j);
				}
			}
		}

		// El for sirve para que empiece desde la ultima parte del cuerpo y vaya
		// moviendose hasta la primera.
		// Esto sirve para cuando al moverse entonces:
		// a I se le asigna el máximo cantidad de cabezas que tiene. Entonces, luego que
		// se resta, lo hará hasta que haya una ultima.
		// Por lo tanto, lo primero que hará es mover
		// Pos: 1, 15.0
		// Pos: 0, 15.0
		// Pos: 1, 15.0
		// Pos: 0, 30.0
		// Pos: 1, 30.0
		// Pos: 0, 45.0
		// Pos: 1, 45.0
		// Pos: 0, 60.0
		// Concluimos entonces, que la POSICION 0 hace primero el movimiento y luego la
		// posicion 1 (que seria parte del cuerpo) sigue a esta.

		if (tecla.equals("arriba")) {
			if (posicion.get(0).y < ConfiguracionesServer.ALTO + 20) {
				posicion.get(0).y += 10;
				setY(posicion.get(0).y);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/Y/arriba/" + this.getY() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/Y/arriba/" + this.getY() + "");
				}
			} else {
				posicion.get(0).y = 0;
				setY(posicion.get(0).y);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/Y/arriba/" + this.getY() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/Y/arriba/" + this.getY() + "");
				}
			}
			ultimaTecla = "arriba";
			contador++;
		}

		if (tecla.equals("abajo")) {
			if (posicion.get(0).y > ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) {
				posicion.get(0).y -= 10;
				setY(posicion.get(0).y);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/Y/abajo/" + this.getY() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/Y/abajo/" + this.getY() + "");
				}

			}

			else {
				posicion.get(0).y = ConfiguracionesServer.ALTO + 20;
				setY(posicion.get(0).y);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/Y/abajo/" + this.getY() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/Y/abajo/" + this.getY() + "");
				}
			}
			contador++;
			ultimaTecla = "abajo";
		}

		if (tecla.equals("izquierda")) {
			if (posicion.get(0).x > ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) {
				posicion.get(0).x -= 10;
				setX(posicion.get(0).x);

				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/X/izquierda/" + this.getX() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/X/izquierda/" + this.getX() + "");
				}

			} else {
				posicion.get(0).x = ConfiguracionesServer.ANCHO + 20;
				setX(posicion.get(0).x);
			}
			ultimaTecla = "izquierda";
			contador++;
		}

		if (tecla.equals("derecha")) {
			if (posicion.get(0).x < ConfiguracionesServer.ANCHO + 20) {
				posicion.get(0).x += 10;
				setX(posicion.get(0).x);

				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P1/X/derecha/" + this.getX() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P1/X/derecha/" + this.getX() + "");
				}

			} else {
				posicion.get(0).x = 0;
				setX(posicion.get(0).x);
			}
			ultimaTecla = "derecha";
			contador++;
		}

	}

	private void movimientoPersonaje2(Array<Vector2> posicion2, String tecla, JugadorBASE jugador) {
		for (int i = posicion2.size - 1; i > 0; i--) {
			posicion2.get(i).x = posicion2.get(i - 1).x;
			posicion2.get(i).y = posicion2.get(i - 1).y;

		}
		
		
		if (j2 >= posicion2.size - 1) {
			j2 = 3;
		}

		if (posicion2.size > 3 && jugador.getPuntos()>=25 && ConfiguracionesServer.timerChoque2==5) {
			for (int i = 0; i < ConfiguracionesServer.getListeners().size(); i++) {
				if(i==1) {
					((Tom) ConfiguracionesServer.getListeners().get(i)).choqueSerpientes(posicion2, j2);
				}
			}
			
	
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
			if (posicion2.get(0).y < ConfiguracionesServer.ALTO + 20) {
				posicion2.get(0).y += 10; // Velocidad con la que sube hacia arriba. Al ser un ArrayList, en el indice
											// 0// estaría X e Y de la serpiente ya que
				setY(posicion2.get(0).y); // lo definimos en la clase anterior

				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P2/Y/arriba/" + this.getY() + "");
				} else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P2/Y/arriba/" + this.getY() + "");
				}

			} else {
				posicion2.get(0).y = 0;
				setY(posicion2.get(0).y);
			}
			contador2++;
			ultimaTecla = "arriba";
		}

		if (tecla.equals("abajo")) {
			if (posicion2.get(0).y > ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) {
				posicion2.get(0).y -= 10;
				setY(posicion2.get(0).y);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P2/Y/abajo/" + this.getY() + "");
				}

				else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P2/Y/abajo/" + this.getY() + "");
				}

			}

			else {
				posicion2.get(0).y = ConfiguracionesServer.ALTO + 20;
				setY(posicion2.get(0).y);
			}
			contador2++;
			ultimaTecla = "abajo";
		}

		if (tecla.equals("izquierda")) {
			if (posicion2.get(0).x > ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) {
				posicion2.get(0).x -= 10;
				setX(posicion2.get(0).x);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P2/X/izquierda/" + this.getX() + "");
				}

				else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P2/X/izquierda/" + this.getX() + "");
				}

			}

			else {
				posicion2.get(0).x = ConfiguracionesServer.ANCHO + 20;
				setX(posicion2.get(0).x);
			}
			contador2++;
			ultimaTecla = "izquierda";
		}

		if (tecla.equals("derecha")) {
			if (posicion2.get(0).x < ConfiguracionesServer.ANCHO + 20) {
				posicion2.get(0).x += 10;
				setX(posicion2.get(0).x);
				if (ConfiguracionesServer.estaApretando) {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Actualizar/P2/X/derecha/" + this.getX() + "");
				}

				else {
					ConfiguracionesServer.hs.enviarMensajeParaTodos("DejoDeMoverse/P2/X/derecha/" + this.getX() + "");
				}
			} else {
				posicion2.get(0).x = 0;
				setX(posicion2.get(0).x);
			}
			contador2++;
			ultimaTecla = "derecha";

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

	public void update(float dt, Array<Vector2> posicion, JugadorBASE jugador, String tecla) {
		ConfiguracionesServer.timer -= dt;

		if (ConfiguracionesServer.timer <= 0) {
			ConfiguracionesServer.timer = 0.094f;
			this.actualizarPosiciones(posicion, jugador, tecla);
		}

	}

	public void setestaMoviendo(boolean valor) {
		this.estaMoviendo = valor;
	}

	public boolean getEstaMoviendo()

	{
		return this.estaMoviendo;
	}

	public Array<Vector2> getArray() {
		return this.posicion;
	}

	public Integer getNumeroPlayer() {
		return this.numeroPlayer;
	}

	public String getUltimaTecla() {
		return ultimaTecla;
	}

	public int getContador() {

		return contador;
	}

}
