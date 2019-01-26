package uo.mp.tareas1.game2048.model;


import java.util.Random;

/**
 * <p>
 * T�tulo: Clase Game2048
 * </p>
 * <p>
 * Descripci�n: A partir de un array bidimensional de n�meros y a trav�s de
 * diferentes operaciones se simula un juego llamado "2048"
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Empresa: Escuela de Ingenier�a Inform�tica - Uiversidad de Oviedo
 * </p>
 * 
 * @author Profesores-MP
 * @version 2.0
 */
public class Game2048 {
	public static final int MIN_DIMENSION = 3;
	public static final int MAX_DIMENSION = 10;
	public static final int EMPTY = 0;

	private int[][] board;

	/**
	 * Constructor sin par�metros con tablero por defecto de 3*3 Inicialmente todas
	 * las posiciones con valor 0
	 */
	public Game2048() {

		defectBoard();
	}

	

	/**
	 * 
	 * @param dimension numero de filas y de columnas (matriz cuadrada)
	 *  entre minimo y m�ximo
	 */

	public Game2048(int dimension) {

		assertDimension(dimension);
		board = new int[dimension][dimension];
		restart();
	}

	/**
	 * Devuelve una copia de la matriz para poder usarla en las pruebas
	 * @return la matriz del tablaro
	 */
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Reinicia todas las posiciones a 0 y llama a next para que incluya un
	 * 2 en una posici�n aleatoria
	 */
	public void restart() {
		int r1=new Random().nextInt(board.length);
		int r2=new Random().nextInt(board.length);
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++){
				board[i][j] = EMPTY;
				next();
			}
		board[r1][r2]=2;
	}
	
	

	
	/**
	 * A�ade un nuevo n�mero 2 en posici�n aleatoria
	 * y pinta el tablero
	 * 
	 */

	public void next() {
		if(!isBoardFull()) {
			int r1, r2;
			do {
				r1 = new Random().nextInt(board.length);
				r2 = new Random().nextInt(board.length);
			} while (board[r1][r2] != EMPTY);
		}

	}
	
	/**
	 * Comprueba si el tablero est� lleno. Esto ocurre cuando todas las celdas o
	 * posiciones del tablero tienen un n�mero distinto de cero
	 * 
	 * @return true si el tablero est� lleno
	 */

	public boolean isBoardFull() {
		for (int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
				if(board[i][j]==0)
					return false;
		return true;
	}

	
	
	/**
	 * Compacta el tablero a la derecha compactando todas las filas
	 * {0,0,4}
	 * {2,4,2}
	 * {0,0,0,0,0,64}
	 *
	 *
	 *
	 * {0,0,0,0,16,2}
	 *
	 * {0,2,2}
	 * {2,4,2}
	 * {0,0,2}
	 *
	 * {0,0,0}
	 * {0,0,4}
	 * {2,4,4}
	 */
	public void compactRight() {

		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length-1;j++)
			{
				for(int k=0;k<board.length;k++)
					if(board[i][j]==board[i][j+1]||board[i][j+1]==0) {
						board[i][j + 1] += board[i][j];
						board[i][j]=EMPTY;
					}
			}
		/*for(int i=0;i<board.length;i++)
			for(int j=board.length-1;j>=0;j++) {
				if(board[i][j]==EMPTY) {
					int k=j;
					while (k>=0&&board[i][k]==EMPTY)
						k--;
					if(k!=-1) {
						board[i][j] = board[i][k];
						board[i][k] = 0;
					}
				}
				while(s)

			}*/

	}
	
	/**
	 * Compacta el tablero a la derecha compactando todas las filas
	 */
	public void compactLeft() {

	}
	
	
	
	
	/**
	 * compacta toda la matriz hacia arriba
	 */
	public void compactUp() {
		
	}
	

	
	/**
	 * compacta toda la matriz hacia abajo
	 */
	public void compactDown() {


	}

	
	
	/**
	 * Inicializa el teclado con la matriz pasada como par�metro
	 */
	protected void setBoard(int[][] matrix) {
		this.board = board;
	}

	private void defectBoard(){
		board = new int[MIN_DIMENSION][MIN_DIMENSION];
		restart();
	}

	//ASERTOS PARA LOS PARÁMETROS

	private void assertDimension(int dimension){
		if (dimension<MIN_DIMENSION || dimension>MAX_DIMENSION) {
			defectBoard();
		}
	}


}
