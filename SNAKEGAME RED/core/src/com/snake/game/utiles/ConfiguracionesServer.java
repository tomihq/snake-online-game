package com.snake.game.utiles;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.snake.game.entradas.Entradas;
import com.snake.game.red.HiloServidor;

public class ConfiguracionesServer {

	public static final int ANCHO = (Gdx.app.getGraphics().getWidth() - 30);
	public static final int ALTO = (Gdx.app.getGraphics().getHeight() - 30);
	public static Entradas entradas = new Entradas();
	public static float timer = 0.094f;
	public static Random r = new Random();
	public static boolean empiezaOnlineC = false;
	public static HiloServidor hs;
	public static boolean estaApretando = false;
	public static boolean creoComida = false;
	public static boolean dispose = false;
	public static boolean disposeBool = false;
	public static boolean creoCronometro = false;
	public static boolean crearCronometroTexto = false;
	public static boolean puntuacionJugador1noCreada = false;
	public static boolean puntuacionJugador2noCreada = false;
	public static boolean coalicionando = false;
	public static boolean reinicioJuego = false;
	public static boolean creoCabeza = false;
	public static boolean serpiente1Choca = false;
	public static boolean serpiente2Choca = false;
	public static boolean chocandoASiMismo = false;
	public static boolean chocandoASiMismo2 = false;
	public static boolean finalizarHiloTiempo=false;
	public static int timerChoque = 2;
	private static ArrayList<EventListener> listeners = new ArrayList<EventListener>();
	public static boolean puntuacion3noCreada = false;
	public static int timerChoque2 = 2;
	
	public static void addListener(EventListener listener) {
		listeners.add(listener);
	}

	public static ArrayList<EventListener> getListeners() {
		return listeners;
	}
	
}
