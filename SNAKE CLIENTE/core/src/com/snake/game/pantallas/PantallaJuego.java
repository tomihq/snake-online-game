package com.snake.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.elementos.Imagen;
import com.snake.game.elementos.Texto;
import com.snake.game.entradas.Entradas;
import com.snake.game.eventos.Cronometro;
import com.snake.game.hilos.HiloTiempo;
import com.snake.game.items.Comida;
import com.snake.game.jugadores.Jugador1;
import com.snake.game.jugadores.Jugador2;
import com.snake.game.jugadores.JugadorBASE;
import com.snake.game.personajes.Snake;
import com.snake.game.personajes.Tom;
import com.snake.game.personajes.Tomi;
import com.snake.game.red.HiloCliente;
import com.snake.game.utiles.Configuraciones;
import com.snake.game.utiles.Recursos;
import com.snake.game.utiles.Render;

public class PantallaJuego implements Screen {

	JugadorBASE jugador1;
	JugadorBASE jugador2;
	Snake serpiente1;
	Snake serpiente2;
	Texture texture;
	Comida comida;
	private Array<Vector2> posicion;
	private Array<Vector2> posicion2;
	private int num2 = 3;
	private Vector2 posicionComida;
	private int num = 3;
	SpriteBatch sb;
	private Texto puntuacionJugador2;
	private Texto puntuacionJugador1;
	private Texto tiempoRestante;
	private Texto esperandoRival;
	private String textos[] = { "PUNTUACION JUGADOR 1:", "TIEMPO", "PUNTUACION JUGADOR 2:" };
	private Texto[] opcion = new Texto[3];
	private Cronometro cronometro;
	private HiloTiempo ht;
	private String nombreJugadorGanadorString;
	private float puntosRealizados;
	private Entradas teclas;
	private Texto puntuacion3;

	@Override
	public void show() {
		Render.limpiarPantalla();
		Render.batch.begin();
		Configuraciones.hc = new HiloCliente(this);
		Configuraciones.hc.start();
		crearObjetos();
		crearJugadores();
		crearMensajesPantalla();

		// P1
		posicion = new Array<Vector2>(); // Se usa un VECTOR2 porque es un vector bidimensional y tenemos valores en x e
											// y.
		for (int i = 0; i < num; i++) {
			posicion.add(new Vector2((Configuraciones.ALTO - Configuraciones.ALTO) + 15,
					(Configuraciones.ANCHO - Configuraciones.ANCHO) + 40)); // Posicion donde empieza la serpiente
		}

		// P2
		posicion2 = new Array<Vector2>(); // Se usa un VECTOR2 porque es un vector bidimensional y tenemos valores en x
											// e y.
		for (int i = 0; i < num2; i++) {
			posicion2.add(new Vector2((Configuraciones.ALTO - Configuraciones.ALTO) + 15, 90)); // Posicion donde
																								// empieza la serpiente
			// ES AL REVÉS, ANCHO - ALTO

		}
		teclas = new Entradas();
		Gdx.input.setInputProcessor(teclas);
	}

	private void crearCronometro() {
		Configuraciones.finalizarHiloTiempo=false;
		cronometro = new Cronometro();
		ht = new HiloTiempo(cronometro);
		ht.start();
		Configuraciones.cronometroCreado = true;

	}

	public void crearCronometroOnline() {
		crearCronometro();
	}

	private void crearMensajesPantalla() {
		for (int i = 0; i < opcion.length; i++) {
			opcion[i] = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			opcion[i].setTexto(textos[i]);
			if (i == 0) {
				opcion[i].setPosition((Configuraciones.ANCHO - Configuraciones.ANCHO) + 5,
						(Configuraciones.ALTO - Configuraciones.ALTO) + 50);
			} else if (i == 1) {
				opcion[i].setPosition((Configuraciones.ANCHO / 2.5f) + 260,
						(Configuraciones.ALTO - Configuraciones.ALTO) + 29);
			} else {
				opcion[i].setPosition((Configuraciones.ANCHO - Configuraciones.ANCHO) + 5,
						(Configuraciones.ALTO - Configuraciones.ALTO) + 25);
			}
		}
	}

	private void crearObjetos() {
		sb = Render.batch;
	}

	private void crearComida(float posXComida2, float posYComida2, float posXComidaVector, float posYComidaVector) {
		final float posXComidaVector2 = posXComidaVector;
		final float posYComidaVector2 = posYComidaVector;

		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				comida = new Comida();
				posicionComida = new Vector2(posXComidaVector2, posYComidaVector2);
			}
		});
		Configuraciones.hc.enviarMensaje("creoLaComida");
	}

	private void crearJugadores() {

		jugador1 = new Jugador1("Tomi"); // SI tnemeos tiempo hacert una pantalla que el jugaodr pueda elegir entre
		// serpiente 1 o 2
		serpiente1 = new Tomi();
		jugador1.setSnakeJugador(serpiente1);

		jugador2 = new Jugador2("Tom");
		serpiente2 = new Tom();
		jugador2.setSnakeJugador(serpiente2);

		// Aca luego debería ser que al momento de tener pantalla de eleccion, se le
		// mande por constructor a jugador que indice (serpiente) eligió entonces
		// en un metodo que sea asignarSerpiente, segun sea el indice se le asigna la
		// serpiente.-

	}

	private void pintarPantalla() {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render(float delta) {

		pintarPantalla();
		if (!Configuraciones.empiezaOnlineC) {

			esperandoRival = new Texto(Recursos.FUENTE, 34, Color.YELLOW, true);
			esperandoRival.setTexto("ESPERANDO QUE TU RIVAL SE CONECTE");
			esperandoRival.setPosition((Configuraciones.ANCHO / 2) - 230, (Configuraciones.ALTO) - 40);
			esperandoRival.dibujar();

		}

		else {

			if (Configuraciones.dispose) {
				this.dispose();
				Configuraciones.dispose = false;

			}

			for (int i = 0; i < posicion.size - 1; i++) {

				if (i < 1) {
					Render.batch.draw(new Texture(jugador1.getSerpienteJugador().getTexturaString2()),
							posicion.get(i).x, posicion.get(i).y);
				} else {
					Render.batch.draw(new Texture(jugador1.getSerpienteJugador().getTextureString()), posicion.get(i).x,
							posicion.get(i).y);
				}

			}

			for (int i = 0; i < posicion2.size - 1; i++) {

				if (i < 1) {
					Render.batch.draw(new Texture(jugador2.getSerpienteJugador().getTexturaString2()),
							posicion2.get(i).x, posicion2.get(i).y);
				} else {
					Render.batch.draw(new Texture(jugador2.getSerpienteJugador().getTextureString()),
							posicion2.get(i).x, posicion2.get(i).y);
				}

			}

			dibujarMensajes();
			dibujarHud();
			if (Configuraciones.tiempoRestanteCreado) {
				tiempoRestante.dibujar();

			}
			if (Configuraciones.puntuacionJugador1Creada) {
				puntuacionJugador1.dibujar();
			}

			if (Configuraciones.puntuacionJugador2Creada) {
				puntuacionJugador2.dibujar();
			}

			update(delta);

		}
	}



	public void dibujarComidaOnline(float x, float y) {
		final float x2 = x;
		final float y2 = y;
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				comida.dibujarComida(x2, y2);// llama a la funcion dibujar comida de comida
			}
		});
	}

	public void crearComidaOnline(float posXComida, float posYComida, float posXComidaVector, float posYComidaVector) {
		crearComida(posXComida, posYComida, posXComidaVector, posYComidaVector);
	}

	private void dibujarMensajes() {
//		for (int i = 0; i < opcion.length; i++) {
//			opcion[i].dibujar();
//		}
		for(int i = 0; i<opcion.length ; i++)
		{
			if(!(opcion[i].toString().isEmpty()))
			{
				opcion[i].dibujar();
			
			}
		}
	

	}

	private void dibujarHud() {
		if (!Configuraciones.puntuacionJugador1Creada) {
			puntuacionJugador1 = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			puntuacionJugador1.setPosition((Configuraciones.ANCHO - Configuraciones.ANCHO) + 245,
					(Configuraciones.ALTO - Configuraciones.ALTO) + 50);
			Configuraciones.puntuacionJugador1Creada = true;
		}
		
		puntuacionJugador1.setTexto(String.valueOf(jugador1.getPuntos()));

		if (!Configuraciones.puntuacionJugador2Creada) {
			puntuacionJugador2 = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			puntuacionJugador2.setPosition((Configuraciones.ANCHO - Configuraciones.ANCHO) + 245,
					(Configuraciones.ALTO - Configuraciones.ALTO) + 26);
			Configuraciones.puntuacionJugador2Creada = true;
			puntuacion3 = new Texto(Recursos.FUENTE, 23, Color.YELLOW, false);
			puntuacion3.setPosition((Configuraciones.ANCHO - Configuraciones.ANCHO) + 245,
					(Configuraciones.ALTO - Configuraciones.ALTO) + 26);
		}

		puntuacionJugador2.setTexto(".");

		if (!Configuraciones.tiempoRestanteCreado) {
			tiempoRestante = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			tiempoRestante.setPosition((Configuraciones.ANCHO / 2.5f) + 350,
					(Configuraciones.ALTO - Configuraciones.ALTO) + 30);
		}

		Configuraciones.tiempoRestanteCreado = true;

		puntuacion3.setTexto(String.valueOf(jugador2.getPuntos()));
		puntuacion3.dibujar();

	}

	private void actualizarCronometro(float tiempoJuego) {
		String tiempoJuego2 = String.valueOf(tiempoJuego);
		if (Configuraciones.tiempoRestanteCreado && !(tiempoJuego2.isEmpty())) {

			
			tiempoRestante.setTexto(tiempoJuego2);

		}

	}

	private void coaliciones(int personaje) {

		realizarCoalicion(personaje);

	}

	private void realizarCoalicion(int personaje) {

		if (personaje == 1) {

			Configuraciones.hc.enviarMensaje("Suma puntos P1");
			cambiarPosicionComidayTamañoSerpiente(1);
			Configuraciones.realizarCoalicion = false;
		} else {

			Configuraciones.hc.enviarMensaje("Suma puntos P2");
			cambiarPosicionComidayTamañoSerpiente(2);
			Configuraciones.realizarCoalicion = false;
		}

	}

	private void cambiarPosicionComidayTamañoSerpiente(Integer serpiente) {
		if (num > 1) {
			num = 0;
		}
		num++;
		if (serpiente == 1) {
			for (int i = 0; i < num; i++) {
				// Acá tuve que blanquear numero, porque al spawnear otra cabeza o parte de la
				// serpiente al num ser 3 spawneaba tres.
				// Por o tanto cambié la del spawn, y cuando toca la comida solo spawnea una
				// debido a que la posicion se le agrega la ultima de la serpiente
				// una sola vez
				posicion.add(new Vector2(jugador1.getSerpienteJugador().getX(), jugador1.getSerpienteJugador().getY())); // Posicion
																															// donde
																															// empieza
																															// la
																															// serpiente
			}
		}

		else {
			for (int i = 0; i < num; i++) {
				// Acá tuve que blanquear numero, porque al spawnear otra cabeza o parte de la
				// serpiente al num ser 3 spawneaba tres.
				// Por o tanto cambié la del spawn, y cuando toca la comida solo spawnea una //
				// debido a que la posicion se le agrega la ultima de la serpiente
				// una sola vez
				posicion2
						.add(new Vector2(jugador2.getSerpienteJugador().getX(), jugador2.getSerpienteJugador().getY())); // Posicion
																															// donde
																															// empieza
																															// la
																															// serpiente

			}
		}

	}


	private void setGanador(String nombreJugador, float puntosRealizados2) {
		cronometro.setTiempoJuego(0);
		nombreJugadorGanadorString = nombreJugador;
		puntosRealizados = puntosRealizados2;
		this.dispose();
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				Render.limpiarPantalla();

				Render.app.setScreen(new PantallaFin(nombreJugadorGanadorString, puntosRealizados));
			}
		});

	}
	
	public void spawnearComidaOnline(float posComidaX, float posComidaY) {
		final float posComidaX2 = posComidaX;
		final float posComidaY2 = posComidaY;
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				comida.spawnearComidaOnline(posComidaX2, posComidaY2, jugador1);// llama a la funcion
																				// spawnearcomidaonline de comida
			}
		});

	}

	public void coalicionesOnline(int personaje) {
		coaliciones(personaje);
	}

	public void actualizarTiempoJuegoOnline(float tiempoJuego) {
		actualizarCronometro(tiempoJuego);
	}

	public void setGanadorOnline(String nombreJugador, float puntosRealizados2) {
		setGanador(nombreJugador, puntosRealizados2);
	}

	public JugadorBASE getJugador1() {
		return this.jugador1;
	}

	public JugadorBASE getJugador2() {
		return this.jugador2;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {

	}

	public Array<Vector2> getPosicion() {

		return this.posicion;
	}

	public Array<Vector2> getPosicion2() {

		return this.posicion2;
	}

	

	public void setJugador1(JugadorBASE object) {
		this.jugador1 = object;
	}

	public void setJugador2(JugadorBASE object) {
		this.jugador2 = object;
	}

}