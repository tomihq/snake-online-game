package com.snake.game.red;

import java.net.InetAddress;

public class Direccion {
	
	private InetAddress ip;
	private int puerto;
	
	public Direccion(InetAddress ip, int puerto) 
	{
		this.ip = ip; 
		this.puerto = puerto;
		
		
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPuerto() {
		return puerto;
	}

}
