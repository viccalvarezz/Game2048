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
 * @author Victoria Alvarez Sordo
 * @version 29-01-2019
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

		if(assertDimension(dimension))
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
	 * A�ade un nuevo número 2 en posición aleatoria
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
	 * @return true si el tablero está lleno
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
	 */
	public void compactRight() {

		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++) {
				for (int k = 0; k < board.length - 1; k++)
					if (board[i][k] == board[i][k + 1] || board[i][k + 1] == EMPTY) {
						board[i][k + 1] += board[i][k];
						board[i][k] = EMPTY;
					}
			}

	}
	
	/**
	 * Compacta el tablero a la izquierda compactando todas las filas
	 *
	 */
	public void compactLeft() {
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
			{
				for(int k=0;k<board.length-1;k++) {
					if (board[i][k] == board[i][k+ 1]||(board[i][k]==EMPTY && board[i][k+1]!=EMPTY)) {
						board[i][k] += board[i][k+ 1];
						board[i][k + 1] = EMPTY;
					}
				}
			}
	}
	
	
	
	
	/**
	 * compacta toda la matriz hacia abajo
	 */
	public void compactDown() {
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
			{
				for(int k=0;k<board.length-1;k++)
					if(board[k][i]==board[k+1][i]||board[k+1][i]==EMPTY) {
						board[k+1][i] += board[k][i];
						board[k][i]=EMPTY;
					}
			}
	}
	

	
	/**
	 * cmpacta toda la matriz hacia arriba
	 */
	public void compactUp() {
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board.length;j++)
			{
				for(int k=0;k<board.length-1;k++) {
					if (board[k][i] == board[k+1][i]||(board[k][i]==EMPTY && board[k+1][i]!=EMPTY)) {
						board[k][i] += board[k+1][i];
						board[k+1][i] = EMPTY;
					}
				}
			}
	}

	
	
	/**
	 * Inicializa el teclado con la matriz pasada como par�metro
	 */
	protected void setBoard(int[][] matrix) {
		this.board = matrix;
	}

	/**
	 * Crea un tablero por defecto de 3x3 e inicializa todas sus casillas a 0 exceptuando una de ellas que tendrá
	 * un número 2
	 */
	private void defectBoard(){
		board = new int[MIN_DIMENSION][MIN_DIMENSION];
		restart();
	}

	/**
	 *
	 * @param row: fila
	 * @param column: columna
	 * @return devuelve el valor que se encuentra en la posición de la fila y columna pasados
	 */
	public int getValue(int row, int column)
	{
		assertMatrix(row,column);
		return board[row][column];
	}

	//ASERTOS PARA LOS PARÁMETROS

	/**
	 *
	 * @param dimension: dimension que se desea de la matriz, no puede ser mayor de 10 ni menor de 3
	 * @return un valor booleano: true en caso de que la dimensión sea correcta. false en caso de que no sea correcta
	 * y por esto, se creará un tablero con las dimensiones por defecto (3x3)
	 */
	private boolean assertDimension(int dimension){
		if (dimension<MIN_DIMENSION || dimension>MAX_DIMENSION) {
			defectBoard();
			return false;
		}
		return true;
	}

	/**
	 * Evalua los parámetros para ver si estos son correctos
	 *
	 * @param row:fila
	 * @param column:columna
	 */
	private void assertMatrix(int row, int column)
	{
		if(row>=board.length||column>=board.length|| row < 0 || column < 0)
			throw new RuntimeException("Error: la matriz no tiene la posición que proporcionas");
	}

}
