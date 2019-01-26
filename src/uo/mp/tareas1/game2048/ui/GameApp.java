package uo.mp.tareas1.game2048.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uo.mp.tareas1.game2048.model.Game2048;

/**
 * <p>
 * T�tulo: Clase principal
 * </p>
 * <p>
 * Descripci�n: 2048 es un juego en l�nea creado por Gabriele Cirulli. El
 * objetivo del juego es combinar n�meros juntos (potencias de 2) con el fin de
 * alcanzar el m�ximo n�mero 2048 y ganar el juego. Para mover los n�meros en el
 * tablero, se debe elegir una direcci�n (arriba, derecha, abajo o izquierda).
 * Todos los n�meros se mueven en la direcci�n elegida y pueden ocurrir dos
 * cosas: los n�meros se juntan en una celda que tiene el mismo n�mero o son
 * bloqueados si una celda contiene un n�mero diferente.
 * 
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Empresa: Escuela de Ingeniería Inform�tica - Universidad de Oviedo
 * </p>
 * 
 * @author Victoria Alvarez Sordo
 * @version 1.0
 */

public class GameApp {
	private Game2048 game;

	public static void main(String[] args) {
		new GameApp().run();
	}

	public void run() {

		game = new Game2048();		
		do {
			System.out.print("JUEGO 2048\n");
			game.restart();
			do {
				System.out.print("Mueve los n�meros en una direcci�n [r R]/[l L]/[u U]/[d D]: ");
				switch (readCharacter()) {
				case 'r':	
				case 'R':
					game.compactRight();
					game.next();
					break;
				case 'l':
				case 'L':
					game.compactLeft();
					game.next();
					break;
				case 'u':
				case 'U':
					game.compactUp();
					game.next();
					break;
				case 'd':
				case 'D':
					game.compactDown();
					game.next();
					break;
				}

			} while (!game.isBoardFull());
			System.out.println("GAME OVER");
			System.out.print("�Quieres continuar? s/n: ");
		} while (readCharacter() == 's');
	}

	/**
	 * Lee un caracter de la entrada estandar (normalmente el teclado)
	 * 
	 * @return El caracter (en formato num�rico) tecleado por el usuario.
	 */
	private int readCharacter() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int character = 0;
		try {
			character = br.read();
		} catch (IOException e) {
			System.out.println("�Error de lectura en la entrada de datos!");
			System.exit(0);
		}
		return character;
	}
}
