package com.snake.game.red;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.badlogic.gdx.Gdx;
import com.snake.game.pantallas.PantallaJuegoServer;
import com.snake.game.pantallas.PantallaMenu;
import com.snake.game.utiles.ConfiguracionesServer;
import com.snake.game.utiles.Render;

public class HiloServidor extends Thread {

	private DatagramSocket conexion;
	private boolean fin = false;
	private Direccion[] clientes = new Direccion[2];
	private int cantClientes = 0;
	private PantallaJuegoServer pj;
	private Integer primerCliente;

	public HiloServidor(PantallaJuegoServer pj) {
		this.pj = pj;
		try {
			conexion = new DatagramSocket(9999);
		} catch (SocketException e) {

			e.printStackTrace();
		}

	}

	private void enviarMensaje(String msg, InetAddress ip, int puerto) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ip, puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		do {
			// cuando moris deberias poner el fin en true
			byte[] data = new byte[1024]; // 1024 es el maximo que acepta el datagrama (en el mensaje)
			DatagramPacket dp = new DatagramPacket(data, data.length);
			try {
				conexion.receive(dp);
			} catch (IOException e) {

				e.printStackTrace();
			}
			procesarMensaje(dp);
		} while (!fin);

	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = (new String(dp.getData())).trim(); // El trim le saca todos los espacios. Convierto de byte a
														// String.
														// Como no se sabe cuantos caracteres va a tener el mensaje
														// Siempre toma un maximo de 1024, por lo tanto si hay 2 letras
														// // lo demás serian espacios, con el trim estos espacios se
														// eliminan
		int nroCliente = -1;
		if (cantClientes > 1) {
			for (int i = 0; i < clientes.length; i++) {
				if (dp.getPort() == clientes[i].getPuerto() && dp.getAddress().equals(clientes[i].getIp())) {
					nroCliente = i;
				}
			}

		}

		if (msg.equals("Se ha creado el personaje con indice 0")) {
			enviarMensaje("¡Se te ha asignado el primer personaje!", clientes[0].getIp(), clientes[0].getPuerto());
		}

		if (cantClientes < 2) {
			if (msg.equals("Conexion exitosa")) {
				if (cantClientes < 2) {
					clientes[cantClientes] = new Direccion(dp.getAddress(), dp.getPort()); // Creo un nuevo cliente bajo
																							// la
																							// ip y el puerto que
																							// ingresa y
																							// se suma al momento de
																							// agregar
																							// uno nuevo
					enviarMensaje("OK", clientes[cantClientes].getIp(), clientes[cantClientes].getPuerto());
					// Al momento que le mandamos un OK al cliente, de esta forma, el cliente sabe
					// que encontró un servidor y se queda en este.
				}
				cantClientes++;
			
				if (cantClientes == 1) {
					for (int i = 0; i < clientes.length; i++) {
						if (i == 0) {
							primerCliente = i;
						}
					}

					enviarMensaje("primero", clientes[primerCliente].getIp(), clientes[primerCliente].getPuerto());

				}

				else if (cantClientes == 2) {

					ConfiguracionesServer.empiezaOnlineC = true;

					for (int i = 0; i < clientes.length; i++) {
						enviarMensaje("Empieza el online", clientes[i].getIp(), clientes[i].getPuerto()); // Apenas
						ConfiguracionesServer.empiezaOnlineC = true; // sucede
																		// esto, va
																		// // en
																		// procesarMensaje
					}

				}

				else if (cantClientes == 2 && (!ConfiguracionesServer.disposeBool)) {
					ConfiguracionesServer.disposeBool = true;
					ConfiguracionesServer.dispose = true;
				}
			}
		} else {
			if (nroCliente != -1) {
				if (msg.equals("Aprete arriba")) {
				
					if (nroCliente == 0) {
						pj.setUp1(true);
					} else {
						pj.setUp2(true);

					}
				} else if (msg.equals("Aprete abajo")) {
					if (nroCliente == 0) {
						pj.setDown1(true);
					} else {
						pj.setDown2(true);

					}
				} else if (msg.equals("Aprete izquierda")) {
					if (nroCliente == 0) {
						pj.setLeft1(true);

					} else {
						pj.setLeft2(true);

					}
				} else if (msg.equals("Aprete derecha")) {
					if (nroCliente == 0) {
						pj.setRight1(true);

					} else {
						pj.setRight2(true);

					}
				} else if (msg.equals("Deje de apretar arriba")) {
					if (nroCliente == 0) {
						ConfiguracionesServer.estaApretando = false;
						pj.setUp1(false);

					} else {
						ConfiguracionesServer.estaApretando = false;
						pj.setUp2(false);

					}
				} else if (msg.equals("Deje de apretar abajo")) {
					if (nroCliente == 0) {
						ConfiguracionesServer.estaApretando = false;
						pj.setDown1(false);

					} else {
						ConfiguracionesServer.estaApretando = false;
						pj.setDown2(false);

					}
				} else if (msg.equals("Deje de apretar derecha")) {
					if (nroCliente == 0) {
						ConfiguracionesServer.estaApretando = false;
						pj.setRight1(false);

					} else {
						ConfiguracionesServer.estaApretando = false;
						pj.setRight2(false);

					}
				} else if (msg.equals("Deje de apretar izquierda")) {
					if (nroCliente == 0) {
						ConfiguracionesServer.estaApretando = false;
						pj.setLeft1(false);

					} else {
						ConfiguracionesServer.estaApretando = false;
						pj.setLeft2(false);

					}
				}
			}

		}

		if (msg.equals("Estoy coalicionando")) {
			ConfiguracionesServer.hs.enviarMensajeParaTodos("CoalicionTrue");
		}

		if (msg.equals("Suma puntos P1")) {

			pj.setSumarPuntos1(true);
			pj.actualizarPuntuacionOnline();

		}

		else if (msg.equals("Suma puntos P2")) {
			pj.setSumarPuntos2(true);
			pj.actualizarPuntuacionOnline();
		}

		if (msg.equals("creoLaComida")) {
			pj.dibujarComidaOnline();
		}

		if (msg.equals("dibujeLaComida")) {
			pj.spawnearComidaOnline();
		}

		if (msg.equals("Deje de coalicionar")) {
			ConfiguracionesServer.coalicionando = false;
		}

		if (msg.equals("Aprete la R")) {
		
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					ConfiguracionesServer.empiezaOnlineC = false;
					ConfiguracionesServer.reinicioJuego = true;
					ConfiguracionesServer.puntuacionJugador1noCreada = false;
					ConfiguracionesServer.puntuacionJugador2noCreada = false;
					ConfiguracionesServer.crearCronometroTexto = false;
					ConfiguracionesServer.creoCabeza = false;
					ConfiguracionesServer.puntuacion3noCreada = false; 
					ConfiguracionesServer.creoComida = false;
					ConfiguracionesServer.creoCronometro = false;
					ConfiguracionesServer.dispose = false;
					ConfiguracionesServer.disposeBool = true;
					ConfiguracionesServer.estaApretando = false;
					ConfiguracionesServer.chocandoASiMismo=false;
					ConfiguracionesServer.chocandoASiMismo2=false;
					ConfiguracionesServer.estaApretando=false;
					ConfiguracionesServer.serpiente1Choca=false;
					ConfiguracionesServer.serpiente2Choca=false;
					ConfiguracionesServer.finalizarHiloTiempo=true;
					cantClientes=0;
					ConfiguracionesServer.hs.enviarMensajeParaTodos("Reinicia el juego");
					Render.batch.end();
					Render.app.setScreen(new PantallaJuegoServer());
				}
			});

		}
		
		if(msg.equals("RestaPuntos P1"))
			{
				pj.getJugador1().restarPuntuacion(10);
				ConfiguracionesServer.chocandoASiMismo = false;
			}
		if(msg.equals("RestaPuntos P2"))
		{
			pj.getJugador2().restarPuntuacion(10);
			ConfiguracionesServer.chocandoASiMismo2 = false;
		}

	}

	public void enviarMensajeParaTodos(String msg) {
		for (int i = 0; i < clientes.length; i++) {
			enviarMensaje(msg, clientes[i].getIp(), clientes[i].getPuerto());
		} // se manda un mensaje general a todos los clientes conectados
	}
}
