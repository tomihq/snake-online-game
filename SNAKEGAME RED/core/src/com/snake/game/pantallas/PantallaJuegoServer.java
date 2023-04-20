package com.snake.game.pantallas;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.snake.game.elementos.Texto;
import com.snake.game.eventos.Cronometro;
import com.snake.game.hilos.HiloTiempo;
import com.snake.game.items.Comida;
import com.snake.game.jugadores.Jugador1;
import com.snake.game.jugadores.Jugador2;
import com.snake.game.jugadores.JugadorBASE;
import com.snake.game.personajes.SnakeServer;
import com.snake.game.personajes.Tom;
import com.snake.game.personajes.Tomi;
import com.snake.game.red.HiloServidor;
import com.snake.game.utiles.ConfiguracionesServer;
import com.snake.game.utiles.Recursos;
import com.snake.game.utiles.Render;

public class PantallaJuegoServer implements Screen {

	protected JugadorBASE jugador1;
	protected JugadorBASE jugador2;
	SnakeServer serpiente1;
	SnakeServer serpiente2;
	Texture texture;
	Comida comida;
	private Texto puntuacion3;
	private Array<Vector2> posicion;
	private Array<Vector2> posicion2;
	private int num2 = 3,num = 3;
	private String tecla;
	private Vector2 posicionComida;
	private float posicionComidaXVector2;
	private float posicionComidaYVector2;
	SpriteBatch sb;
	private float posXComida;
	private float posYComida;
	private Texto puntuacionJugador2,puntuacionJugador1,tiempoRestante,esperandoClientes;
	private Cronometro cronometro;
	private String valor;
	private HiloTiempo ht;
	private String nombreJugadorGanadorString;
	private float puntosRealizados=0,cantJugadores;
	private boolean sumarPuntos1 = false;
	private boolean sumarPuntos2 = false;
	private String textos[] = { "PUNTUACION JUGADOR 1:", "TIEMPO", "PUNTUACION JUGADOR 2:" };
	private Texto[] opcion = new Texto[3];

	private boolean isUp1=false, isUp2=false, isDown1=false, isDown2=false, isLeft1=false, isLeft2=false, isRight1=false, isRight2=false;

	@Override
	public void show() {
		Render.limpiarPantalla();
		Render.batch.begin();
		if (!ConfiguracionesServer.reinicioJuego) {
			ConfiguracionesServer.hs = new HiloServidor(this);
			ConfiguracionesServer.hs.start();
		}
		crearObjetos();
		crearJugadores();
		crearMensajesPantalla();

		// P1

		posicion = new Array<Vector2>(); // Se usa un VECTOR2 porque es un vector bidimensional y tenemos valores en x e
											// y.
		for (int i = 0; i < num; i++) {
			posicion.add(new Vector2((ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 15,
					(ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 40)); // Posicion donde empieza la
																						// serpiente
		}

		// P2
		posicion2 = new Array<Vector2>(); // Se usa un VECTOR2 porque es un vector bidimensional y tenemos valores en x
											// e y.
		for (int i = 0; i < num2; i++) {
			posicion2.add(new Vector2((ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 15, 90)); // Posicion
																											// donde
																											// empieza
																											// la
																											// serpiente
			// ES AL REVÉS, ANCHO - ALTO
		}

	}

	private void crearCronometro() {
		ConfiguracionesServer.finalizarHiloTiempo=false;
		cronometro = new Cronometro();
		ht = new HiloTiempo(cronometro);
		ht.start();
		ConfiguracionesServer.hs.enviarMensajeParaTodos("CreaElCronometro/unicaVez");
	}

	private void crearMensajesPantalla() {
		for (int i = 0; i < opcion.length; i++) {
			opcion[i] = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			opcion[i].setTexto(textos[i]);
			if (i == 0) {
				opcion[i].setPosition((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 5,
						(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 50);

			}

			else if (i == 1) {
				opcion[i].setPosition((ConfiguracionesServer.ANCHO / 2.5f) + 260,
						(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 29);
			} else {
				opcion[i].setPosition((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 5,
						(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 25);
			}

		}

	}

	private void crearObjetos() {
		sb = Render.batch;

	}

	private void crearComida() {

		comida = new Comida();
		posicionComida = new Vector2((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO + 5),
				(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO + 10));
		posicionComidaXVector2 = posicionComida.x;
		posicionComidaYVector2 = posicionComida.y;

		posXComida = ConfiguracionesServer.r.nextInt(ConfiguracionesServer.ANCHO) + 1;
		posYComida = ThreadLocalRandom.current().nextInt((ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 70,
				(ConfiguracionesServer.ALTO - 40));
		ConfiguracionesServer.creoComida = true;
		ConfiguracionesServer.hs.enviarMensajeParaTodos("Elservidor/creo/lacomida/" + posXComida + "/" + posYComida
				+ "/" + posicionComidaXVector2 + "/" + posicionComidaYVector2);

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
		if (!ConfiguracionesServer.empiezaOnlineC) {
			esperandoClientes = new Texto(Recursos.FUENTE, 34, Color.YELLOW, true);
			esperandoClientes.setTexto("PARTIDA HOSTEADA, ESPERANDO CLIENTES");
			esperandoClientes.setPosition((ConfiguracionesServer.ANCHO / 2) - 255, (ConfiguracionesServer.ALTO) - 40);
			esperandoClientes.dibujar();
		} else {
			if (ConfiguracionesServer.dispose) {
				this.dispose();
				ConfiguracionesServer.dispose = false;
			}
			if (!ConfiguracionesServer.creoComida) {
				crearComida();
			}
			if (!ConfiguracionesServer.creoCronometro) {
				crearCronometro();
				ConfiguracionesServer.creoCronometro = true;
			}
			
			for (int i = 0; i < posicion.size -1; i++) {
				if (i < 1) {
					Render.batch.draw(new Texture(jugador1.getSerpienteJugador().getTexturaString2()),
							posicion.get(i).x, posicion.get(i).y);
				} else {
					Render.batch.draw(new Texture(jugador1.getSerpienteJugador().getTextureString()), posicion.get(i).x,
							posicion.get(i).y);
				}
			}
			// realizar un for en que compruebe que la posicion 0 (cabeza) sea distinta
			// tanto en x como en y del resto del cuerpo (1/size-1)
			// utilizar evento choque para cuando sucede eso
			// probar utilizar el posicion.removeIndex, y con un for eliminar la posicion en
			// la que choca en adelante (del for)
			for (int i = 0; i < posicion2.size -1; i++) {
				if (i < 1) {
					Render.batch.draw(new Texture(jugador2.getSerpienteJugador().getTexturaString2()),
							posicion2.get(i).x, posicion2.get(i).y);
				} else {
					Render.batch.draw(new Texture(jugador2.getSerpienteJugador().getTextureString()),
							posicion2.get(i).x, posicion2.get(i).y);
				}
			}
			
			dibujarHud();
			dibujarComida();
			dibujarMensajes();
			tiempoRestante.dibujar();
			puntuacionJugador1.dibujar();
			puntuacionJugador2.dibujar();
			puntuacion3.dibujar();
			coaliciones();
			verificarPantallaFin();
			this.update(delta);

		}
	}

	private void dibujarComida() {
		comida.dibujarComida(posXComida, posYComida);
	}

	private void dibujarMensajes() {
		for(int i = 0; i<opcion.length ; i++)
		{
			if(!(opcion[i].toString().isEmpty()))
			{
				opcion[i].dibujar();
			
			}
		}

	}

	private void dibujarHud() {
		if (!ConfiguracionesServer.puntuacionJugador1noCreada) {
			puntuacionJugador1 = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			puntuacionJugador1.setPosition((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 245,
					(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 50);
			ConfiguracionesServer.puntuacionJugador1noCreada = true;
		}
		puntuacionJugador1.setTexto(String.valueOf(jugador1.getPuntos()));
		if (!ConfiguracionesServer.puntuacionJugador2noCreada) {
			puntuacionJugador2 = new Texto(Recursos.FUENTE, 23, Color.RED, false);
			puntuacionJugador2.setPosition((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 245,
					(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) - 100);
			ConfiguracionesServer.puntuacionJugador2noCreada = true;
		}
		puntuacionJugador2.setTexto(".");
		if (!ConfiguracionesServer.crearCronometroTexto) {
			tiempoRestante = new Texto(Recursos.FUENTE, 23, Color.YELLOW, true);
			ConfiguracionesServer.crearCronometroTexto = true;
		}
		valor = String.valueOf(cronometro.getTiempoJuego());
		tiempoRestante.setTexto(valor);
		tiempoRestante.setPosition((ConfiguracionesServer.ANCHO / 2.5f) + 350,
				(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 30);
		ConfiguracionesServer.hs.enviarMensajeParaTodos("ActualizarCronometro/" + valor);

		if (!ConfiguracionesServer.puntuacion3noCreada) {
			puntuacion3 = new Texto(Recursos.FUENTE, 23, Color.YELLOW, false);
			puntuacion3.setPosition((ConfiguracionesServer.ANCHO - ConfiguracionesServer.ANCHO) + 245,
					(ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 26);
			ConfiguracionesServer.puntuacion3noCreada = true;
		}

		puntuacion3.setTexto(String.valueOf(jugador2.getPuntos()));
		puntuacion3.dibujar();

	}

	private void coaliciones() {
		int posicionInicioYComida = (int) (comida.getY() - comida.getTexture().getHeight());
		//Resté el Y (porque toma la parte de arriba de la comida) con el alto de la textura. Así puedo
		//contemplar en que coordenada empieza la comida (200-100) empieza en 100 por ejemplo. Entonces si va desde 100 hasta 200 significa que coaliciona
		int posicionInicioXComida = (int) (comida.getX() - comida.getTexture().getWidth());
		// LOS IFS DE ARRIBA DEBERIAN HACERSE CUANDO SUCEDA LA COALICION. (OSEA SUMAR
		// LOS VALORES) ESO SÍ, UTILIZAR ESOS MENSJAES LUEGO YA QUE ESTAN PROGRAMADOS.
		
		
		if ((jugador1.getSerpienteJugador().getX() > posicionInicioXComida - 10
				&& jugador1.getSerpienteJugador().getX() <= comida.getX() + 10)
				&& (jugador1.getSerpienteJugador().getY() > posicionInicioYComida - 10
						&& jugador1.getSerpienteJugador().getY() <= comida.getY() + 10)) {
			
			if (!ConfiguracionesServer.coalicionando) {
				ConfiguracionesServer.hs.enviarMensajeParaTodos("RealizarCoalicion/P1/" + 1);
				ConfiguracionesServer.coalicionando = true;
			}
			
			// aca
			cambiarPosicionComidayTamañoSerpiente(1);
		} 
		
		else if ((jugador2.getSerpienteJugador().getX() > posicionInicioXComida - 10
				&& jugador2.getSerpienteJugador().getX() <= comida.getX() + 10)
				&& (jugador2.getSerpienteJugador().getY() > posicionInicioYComida - 10
						&& jugador2.getSerpienteJugador().getY() <= comida.getY() + 10)) {
			
			if (!ConfiguracionesServer.coalicionando) {
				ConfiguracionesServer.hs.enviarMensajeParaTodos("RealizarCoalicion/P2/" + 1);
				ConfiguracionesServer.coalicionando = true;
			}
			
			// aca deberia de activarse el evento de aumento de puntuacion
			cambiarPosicionComidayTamañoSerpiente(2);
		}
		
		
	}

	public void actualizarPuntuacionOnline() {
		actualizarPuntuacion();
	}

	public void serpienteJugadorChocoASiMismo(float jugador) {
		if (jugador == 1) {
			jugador1.restarPuntuacion(10);
			// Una vez que voy a serpiente, deberia ir a jugador y ahi le resto los puntos
			// que corresponden con la interfaz.

		} else {
			jugador2.restarPuntuacion(10);
		}
	}

	private void actualizarPuntuacion() {
		if (sumarPuntos1) {
			sumarPuntos1 = false;
			jugador1.sumarPuntuacion(10);

		} else if (sumarPuntos2) {
			sumarPuntos2 = false;
			jugador2.sumarPuntuacion(10);

		}
		ConfiguracionesServer.coalicionando = false;
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
				// Por o tanto cambié la del spawn, y cuando toca la comida solo spawnea una
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

		posXComida = ConfiguracionesServer.r.nextInt(ConfiguracionesServer.ANCHO) + 1;
		posYComida = ThreadLocalRandom.current().nextInt((ConfiguracionesServer.ALTO - ConfiguracionesServer.ALTO) + 70,
				(ConfiguracionesServer.ALTO - 40));
		// ESTOS VALORES nos sirven para que cuando vaya luego a spawnear comida en
		// coalicion use estos valores que le mandamos por parámetros.
		// Osea de aquí, luego cuando coaliciona y va a dibujar cambia la posicion de la
		// comida.

	}

	public void update(float delta) {
	
		if (isUp1()) {
			tecla = "arriba";
			ConfiguracionesServer.estaApretando = true;
			serpiente1.update(delta, posicion, jugador1, tecla);
		}
		if (isUp2()) {

			tecla = "arriba";
			ConfiguracionesServer.estaApretando = true;
			serpiente2.update(delta, posicion2, jugador2, tecla);
		}

		if (isDown1()) {
			tecla = "abajo";
			ConfiguracionesServer.estaApretando = true;
			serpiente1.update(delta, posicion, jugador1, tecla);
		}
		if (isDown2()) {
			tecla = "abajo";
			ConfiguracionesServer.estaApretando = true;
			serpiente2.update(delta, posicion2, jugador2, tecla);
		}

		if (isLeft1()) {
			tecla = "izquierda";
			ConfiguracionesServer.estaApretando = true;
			serpiente1.update(delta, posicion, jugador1, tecla);

		}
		if (isLeft2()) {
			tecla = "izquierda";
			ConfiguracionesServer.estaApretando = true;
			serpiente2.update(delta, posicion2, jugador2, tecla);

		}

		if (isRight1()) {
			tecla = "derecha";
			ConfiguracionesServer.estaApretando = true;
			serpiente1.update(delta, posicion, jugador1, tecla);

		}
		if (isRight2()) {
			tecla = "derecha";
			ConfiguracionesServer.estaApretando = true;
			serpiente2.update(delta, posicion2, jugador2, tecla);

		}

	}

	public void coalicionOnline() {
		coaliciones();
	}

	private void verificarPantallaFin() {

		if (cronometro.getTiempoJuego() == 0) {

			verificarGanador();

		}

	}

	private void verificarGanador() {
		cronometro.setTiempoJuego(0);
		if (jugador1.getPuntos() > jugador2.getPuntos()) {
			nombreJugadorGanadorString = jugador1.getNombre();
			puntosRealizados = jugador1.getPuntos();
		}

		else if (jugador1.getPuntos() < jugador2.getPuntos()) {
			nombreJugadorGanadorString = jugador2.getNombre();
			puntosRealizados = jugador2.getPuntos();
		} else if (jugador1.getPuntos() == jugador2.getPuntos()) {
			nombreJugadorGanadorString = "¡No hubo ganador, hubo empate!";
			puntosRealizados = jugador2.getPuntos();
		}

		ConfiguracionesServer.hs
				.enviarMensajeParaTodos("GanoPersonaje/" + nombreJugadorGanadorString + "/puntos/" + puntosRealizados);

		Render.limpiarPantalla();
		this.dispose();

		Render.app.setScreen(new PantallaFin(nombreJugadorGanadorString, puntosRealizados));

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

	@Override
	public void dispose() {

	}
	
	public boolean isDown1() {
		return isDown1;
	}

	public void setDown1(boolean b) {
		this.isDown1 = b;
	}

	public boolean isUp1() {
		return isUp1;
	}

	public void setUp1(boolean isUp1) {
		this.isUp1 = isUp1;
		
	}

	public boolean isUp2() {
		return isUp2;
	}

	public void setUp2(boolean isUp2) {
		this.isUp2 = isUp2;
	}

	public boolean isDown2() {
		return isDown2;
	}

	public void setDown2(boolean isDown2) {
		this.isDown2 = isDown2;
	}

	public boolean isLeft1() {
		return isLeft1;
	}

	public void setLeft1(boolean isLeft1) {
		this.isLeft1 = isLeft1;
	}

	public boolean isLeft2() {
		return isLeft2;
	}

	public void setLeft2(boolean isLeft2) {
		this.isLeft2 = isLeft2;
	}

	public boolean isRight1() {
		return isRight1;
	}

	public void setRight1(boolean isRight1) {
		this.isRight1 = isRight1;
	}

	public boolean isRight2() {
		return isRight2;
	}

	public void setRight2(boolean isRight2) {
		this.isRight2 = isRight2;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}

	public void setEsperandoClientes(Texto esperandoClientes) {
		this.esperandoClientes = esperandoClientes;
	}

	public void setCronometro(Cronometro cronometro) {
		this.cronometro = cronometro;
	}

	public void setHt(HiloTiempo ht) {
		this.ht = ht;
	}

	public void setCantJugadores(float cantJugadores) {
		this.cantJugadores = cantJugadores;
	}

	public void setSumarPuntos1(boolean valor) {
		this.sumarPuntos1 = valor;

	}

	public boolean getSumarPuntos1() {
		return this.sumarPuntos1;
	}

	public void setSumarPuntos2(boolean valor) {
		this.sumarPuntos2 = valor;

	}

	public boolean getSumarPuntos2() {
		return this.sumarPuntos2;
	}

	public void dibujarComidaOnline() {
		dibujarComida();
	}

	public void spawnearComidaOnline() {
		comida.spawnearComidaOnline(this.posicionComida, this.jugador1);

	}

	public JugadorBASE getJugador1() {
		return jugador1;
	}

	public void setJugador1(JugadorBASE jugador1) {
		this.jugador1 = jugador1;
	}

	public JugadorBASE getJugador2() {
		return jugador2;
	}

	public void setJugador2(JugadorBASE jugador2) {
		this.jugador2 = jugador2;
	}

}