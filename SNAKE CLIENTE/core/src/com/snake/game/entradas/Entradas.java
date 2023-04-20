package com.snake.game.entradas;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.snake.game.red.HiloCliente;
import com.snake.game.utiles.Configuraciones;

public class Entradas implements InputProcessor {

	private boolean down = false, left = false, right = false, enter = false, click = false;
	private boolean up = false;
	private boolean r = false;
	private int mouseX = 0, mouseY = 0;

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.DOWN) {
			down = true;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Aprete abajo");
			}

		} else if (keycode == Keys.UP) {
			up = true;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Aprete arriba");

			}
		} else if (keycode == Keys.LEFT) {
			left = true;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Aprete izquierda");
			}
		} else if (keycode == Keys.RIGHT) {
			right = true;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Aprete derecha");
			}
		}
		if (keycode == Keys.R && Configuraciones.finalizarJuego) {
			r = true;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Aprete la R");
			}
		}

		if (keycode == Keys.ENTER) {// tecla enter
			enter = true;
		}

		return false;

	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.DOWN) {
			down = false;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Deje de apretar abajo");
			}
		}

		if (keycode == Keys.UP) {
			up = false;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Deje de apretar arriba");
			}
		}
		if (keycode == Keys.LEFT) {
			left = false;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Deje de apretar izquierda");
			}

		}
		if (keycode == Keys.RIGHT) {
			right = false;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Deje de apretar derecha");
			}
		}

		if (keycode == Keys.C) {
			r = false;
			if (Configuraciones.empiezaOnlineC) {
				Configuraciones.hc.enviarMensaje("Deje de apretar la R");
			}
		}

		if (keycode == Keys.ENTER) {
			enter = false;

		}

		return false;
	}

	public boolean isEnter() {
		return enter;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseX = screenX;
		mouseY = Configuraciones.ALTO - screenY; // Para que x 0 esté abajo y no arriba
		return false;
	}

	public void setR(boolean reiniciar2) {
		this.r = reiniciar2;
	}

	public boolean getR() {
		return this.r;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		click = false;
		return false;
	}

	public boolean isClick() {

		return this.click;
	}

}
