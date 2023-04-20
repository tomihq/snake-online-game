package com.snake.game.utiles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.snake.game.entradas.Entradas;
import com.snake.game.red.HiloCliente;

public class Configuraciones {

	public static final int ANCHO = (Gdx.app.getGraphics().getWidth()-30);
	public static final int ALTO =  (Gdx.app.getGraphics().getHeight()-30);
	public static HiloCliente hc; 
	public static Entradas entradas = new Entradas(); 
	public static float timer = 0.094f; 
	public static Random r = new Random(); 
	public static boolean empiezaOnlineC = false; 
	public static boolean spawneoComida=false;
	public static boolean realizarCoalicion = false;
	public static boolean dispose = false;
	public static boolean cronometroCreado = false;
	public static boolean puntuacionJugador1Creada = false;
	public static boolean puntuacionJugador2Creada = false;
	public static boolean tiempoRestanteCreado = false;
	public static boolean reinicioJuego = false;
	public static boolean chocandoASiMismo = false;
	public static boolean chocandoASiMismo2 = false;
	public static boolean fin=false;
	public static boolean finalizarJuego=false;
	public static boolean finalizarHiloTiempo=false;

}
