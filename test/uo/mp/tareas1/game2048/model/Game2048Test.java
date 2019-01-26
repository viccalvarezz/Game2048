package uo.mp.tareas1.game2048.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runners.JUnit4;
import uo.mp.tareas1.game2048.model.Game2048;

public class Game2048Test {

	private static int[][] SEMIFULL11 = {{0,2,0},{0,4,0},{0,8,0}};
	private static int[][] SEMIFULL12 = {{2,0,0},{4,0,0},{8,0,0}};
	private static int[][] SEMIFULL13 = {{0,0,2},{0,0,4},{0,0,8}};
	private static int[][] SEMIFULL1_RIGHTCOMPACTED = {{0,0,2},{0,0,4},{0,0,8}};
	private static int[][] SEMIFULL1_LEFTCOMPACTED = {{2,0,0},{4,0,0},{8,0,0}};

	private static int[][] SEMIFULL21 = {{0,2,4},{0,4,8},{0,8,16}};
	private static int[][] SEMIFULL22 = {{2,4,0},{4,8,0},{8,16,0}};
	private static int[][] SEMIFULL23 = {{2,0,4},{4,0,8},{8,0,16}};
	private static int[][] SEMIFULL2_RIGHTCOMPACTED = {{0,2,4},{0,4,8},{0,8,16}};
	private static int[][] SEMIFULL2_LEFTCOMPACTED = {{2,4,0},{4,8,0},{8,16,0}};

	private static int[][] FULL = {{2,4,8},{4,8,16},{8,16,32}};

	private static int[][] SEMIFULL31 = {{0,0,0},{0,0,0},{2,4,8}};
	private static int[][] SEMIFULL32 = {{0,0,0},{2,4,8},{0,0,0}};
	private static int[][] SEMIFULL33 = {{2,4,8},{0,0,0},{0,0,0}};
	private static int[][] SEMIFULL3_UPCOMPACTED = {{2,4,8},{0,0,0},{0,0,0}};
	private static int[][] SEMIFULL3_DOWNCOMPACTED = {{0,0,0},{0,0,0},{2,4,8}};

	private static int[][] SEMIFULL41 = {{0,0,0},{2,4,8},{4,8,16},};
	private static int[][] SEMIFULL42 = {{2,4,8},{0,0,0},{4,8,16}};
	private static int[][] SEMIFULL43 = {{2,4,8},{4,8,16},{0,0,0}};
	private static int[][] SEMIFULL4_UPCOMPACTED = {{2,4,8},{4,8,16},{0,0,0}};
	private static int[][] SEMIFULL4_DOWNCOMPACTED = {{0,0,0},{2,4,8},{4,8,16}};

	@Test
	public void testFirstConstructor(){
		Game2048 b1 = new Game2048();
		assertEquals(3,b1.getBoard().length);
		assertEquals(3,b1.getBoard()[0].length);
	}

	@Test
	public void testSecondConstructor(){
		//Casos Positivos

		//Caso 1: se coloca la dimensión mínima, 3x3
		Game2048 b1 = new Game2048(3);
		assertEquals(3,b1.getBoard().length);
		assertEquals(3,b1.getBoard()[0].length);

		//Caso 2: se coloca una dimesión de 5x5
		Game2048 b2 = new Game2048(5);
		assertEquals(5,b2.getBoard().length);
		assertEquals(5,b2.getBoard()[0].length);

		//Caso 3: se coloca la dimensión máxima, 10x10
		Game2048 b3 = new Game2048(10);
		assertEquals(10,b3.getBoard().length);
		assertEquals(10,b3.getBoard()[0].length);

		//Casos Negativos

		//Caso 4: se coloca una dimensión menor del límite inferior
		Game2048 b4 = new Game2048(2);
		assertEquals(3,b4.getBoard().length);
		assertEquals(3,b4.getBoard()[0].length);

		//Caso 5: se coloca un dimensión mayor al límite superior
		Game2048 b5 = new Game2048(11);
		assertEquals(3,b5.getBoard().length);
		assertEquals(3,b5.getBoard()[0].length);
	}

	@Test
	public void testRestart(){
		//Caso 1: proporciono una matriz cualquiera de dimensión 3x3 y le doy restart
		int[][] matrix = {{2,0,0},{0,0,0},{2,0,2}};
		Game2048 b1 = new Game2048();
		b1.setBoard(matrix);
		b1.restart();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				assertEquals(b1.getValue(i,j),b1.getBoard()[i][j]);

	}

	@Test
	public void testNext(){
		//Caso 1: creo una matriz con la dimensión por defecto, con un solo dos colocado y le doy next
		Game2048 b1 = new Game2048();
		b1.next();
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					assertEquals(b1.getValue(i,j),b1.getBoard()[i][j]);

		//Caso 2:proporciono una matriz llena y le doy next, no pasará nada
		int[][] matrixFull = {{2,4,8},{4,8,16},{8,16,32}};
		Game2048 b2 = new Game2048();
		b2.setBoard(matrixFull);
		b2.next();
		assertArrayEquals(matrixFull,b2.getBoard());

		//Caso 3: proporciono una matriz con un solo espacio con 0 y le doy next
		int[][] matrix0 = {{2,4,8},{4,8,16},{8,16,0}};
		Game2048 b3 = new Game2048();
		b3.setBoard(matrix0);
		b3.next();
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					assertEquals(b3.getValue(i,j),b3.getBoard()[i][j]);
		}

	@Test
	public void testIsBoardFull(){
		//Caso 1:el tablero vacío a excepción de una casilla que tendrá un 2
		Game2048 b1 = new Game2048();
		assertFalse(b1.isBoardFull());

		//Caso 2:el tablero esta lleno
		Game2048 b2 = new Game2048();
		b2.setBoard(FULL);
		assertTrue(b2.isBoardFull());

		//Caso 3:el tablero solo tendrá una posición con 0
		int[][] matrix0 = {{2,4,8},{4,8,16},{8,16,0}};
		Game2048 b3 = new Game2048();
		b3.setBoard(matrix0);
		assertFalse(b3.isBoardFull());
		}


	@Test
	public void testCompactRight(){
		//Caso 1: con matriz de 3x3, llevar de la segunda columna a la ultima
		Game2048 b1 = new Game2048();
		b1.setBoard(SEMIFULL11);
		b1.compactRight();
		assertArrayEquals(b1.getBoard(),SEMIFULL1_RIGHTCOMPACTED);

		//Caso 2:con matriz de 3x3, se debe mantener igual a la matriz que se le pasa
		Game2048 b2 = new Game2048();
		b2.setBoard(SEMIFULL21);
		b2.compactRight();
		assertArrayEquals(b2.getBoard(),SEMIFULL2_RIGHTCOMPACTED);

		//Caso 3:matriz 5x5 en la que los bucles deben realizar mas iteraciones
		int [][] max={{16,8,4,2,2},{2,4,4,8,8},{0,2,0,0,2},{0,4,0,2,2},{8,0,0,0,0}};
		int [][] res={{0,0,0,0,32},{0,0,2,16,8},{0,0,0,0,4},{0,0,0,0,8},{0,0,0,0,8}};
		Game2048 b3 = new Game2048();
		b3.setBoard(max);
		b3.compactRight();
		assertArrayEquals(b3.getBoard(),res);

		//Caso 4:matriz 3x3, lleva de la primera columna a la ultima
		Game2048 b4 = new Game2048();
		b4.setBoard(SEMIFULL12);
		b4.compactRight();
		assertArrayEquals(b4.getBoard(),SEMIFULL1_RIGHTCOMPACTED);

		//Caso 5:matriz 3x3, se debe mantener igual a la matriz que se le pasa
		Game2048 b5 = new Game2048();
		b5.setBoard(SEMIFULL13);
		b5.compactRight();
		assertArrayEquals(b5.getBoard(),SEMIFULL1_RIGHTCOMPACTED);

		//Caso 6:matriz 3x3, la columna 0 queda vacía
		Game2048 b6 = new Game2048();
		b6.setBoard(SEMIFULL22);
		b6.compactRight();
		assertArrayEquals(b6.getBoard(),SEMIFULL2_RIGHTCOMPACTED);

		//Caso 7:matriz 3x3, los elementos de la columna cero pasan a la columa 1
		Game2048 b7 = new Game2048();
		b7.setBoard(SEMIFULL23);
		b7.compactRight();
		assertArrayEquals(b7.getBoard(),SEMIFULL2_RIGHTCOMPACTED);

		//Caso 8: matriz 3x3, la matriz se encuentra llena y no hay casillas contiguas iguales a la derecha
		Game2048 b8 = new Game2048();
		b8.setBoard(FULL);
		b8.compactRight();
		assertArrayEquals(b8.getBoard(),FULL);
	}

	@Test
	public void testCompactLeft(){
		//Caso 1:matriz 3x3, los números de la columna uno pasan a la cero
		Game2048 b1 = new Game2048();
		b1.setBoard(SEMIFULL11);
		b1.compactLeft();
		assertArrayEquals(b1.getBoard(),SEMIFULL1_LEFTCOMPACTED);

		//Caso 2: matriz 3x3, se mantendrá igual a la salida luego de realizar el movimiento
		Game2048 b2 = new Game2048();
		b2.setBoard(SEMIFULL12);
		b2.compactLeft();
		assertArrayEquals(b2.getBoard(),SEMIFULL1_LEFTCOMPACTED);

		//Caso 3:matriz 3x3, los numeros de la columna dos pasan a la columna cero
		Game2048 b3 = new Game2048();
		b3.setBoard(SEMIFULL13);
		b3.compactLeft();
		assertArrayEquals(b3.getBoard(),SEMIFULL1_LEFTCOMPACTED);

		//Caso 4:matriz 3x3,queda la columna 2 vacía
		Game2048 b4 = new Game2048();
		b4.setBoard(SEMIFULL21);
		b4.compactLeft();
		assertArrayEquals(b4.getBoard(),SEMIFULL2_LEFTCOMPACTED);

		//Caso 5: matriz 3x3, la matriz resultante se mantendrá igual a la que estaba
		Game2048 b5 = new Game2048();
		b5.setBoard(SEMIFULL22);
		b5.compactLeft();
		assertArrayEquals(b5.getBoard(),SEMIFULL2_LEFTCOMPACTED);

		//Caso 6:matriz 3x3, los elementos de la columna 2 se moverán a la columna 1
		Game2048 b6 = new Game2048();
		b6.setBoard(SEMIFULL23);
		b6.compactLeft();
		assertArrayEquals(b6.getBoard(),SEMIFULL2_LEFTCOMPACTED);

		//Caso 7:matriz 3x3, la matriz estará llena y se mantendrá igual
		Game2048 b7 = new Game2048();
		b7.setBoard(FULL);
		b7.compactLeft();
		assertArrayEquals(b7.getBoard(),FULL);
	}

	@Test
	public void testCompactUp(){

		//Caso 1:matriz 3x3, los elementos de la última fila pasan a la primera
		Game2048 b1 = new Game2048();
		b1.setBoard(SEMIFULL31);
		b1.compactUp();
		assertArrayEquals(b1.getBoard(),SEMIFULL3_UPCOMPACTED);

		//Caso 2: matriz 3x3, los elementos de la fila 1 pasan a la 0
		Game2048 b2 = new Game2048();
		b2.setBoard(SEMIFULL32);
		b2.compactUp();
		assertArrayEquals(b2.getBoard(),SEMIFULL3_UPCOMPACTED);

		//Caso 3: matriz 3x3, la matriz resultante será igual a la que se pasa
		Game2048 b3 = new Game2048();
		b3.setBoard(SEMIFULL33);
		b3.compactUp();
		assertArrayEquals(b3.getBoard(),SEMIFULL3_UPCOMPACTED);

		//Caso 4: matriz 3x3, la fila 2 pasa a la 1 y esta a la 0
		Game2048 b4 = new Game2048();
		b4.setBoard(SEMIFULL41);
		b4.compactUp();
		assertArrayEquals(b4.getBoard(),SEMIFULL4_UPCOMPACTED);

		//Caso 5: matriz 3x3, la fila 0 se mantiene y la 2 pasa a la 1
		Game2048 b5 = new Game2048();
		b5.setBoard(SEMIFULL42);
		b5.compactUp();
		assertArrayEquals(b5.getBoard(),SEMIFULL4_UPCOMPACTED);

		//Caso 6:matriz 3x3, la matriz resultante es igual a la que se pasa
		Game2048 b6 = new Game2048();
		b6.setBoard(SEMIFULL43);
		b6.compactUp();
		assertArrayEquals(b6.getBoard(),SEMIFULL4_UPCOMPACTED);

		//Caso 7:matriz 3x3, la matriz estará llena y se mantendrá igual
		Game2048 b7 = new Game2048();
		b7.setBoard(FULL);
		b7.compactLeft();
		assertArrayEquals(b7.getBoard(),FULL);
	}

	@Test
	public void testCompactDown(){
		//Caso 1:matriz 3x3, la matriz resultante será igual a la que se pasa
		Game2048 b1 = new Game2048();
		b1.setBoard(SEMIFULL31);
		b1.compactDown();
		assertArrayEquals(b1.getBoard(),SEMIFULL3_DOWNCOMPACTED);

		//Caso 2:matriz 3x3, los elementos de la fila 1 pasan a la fila 2
		Game2048 b2 = new Game2048();
		b2.setBoard(SEMIFULL32);
		b2.compactDown();
		assertArrayEquals(b2.getBoard(),SEMIFULL3_DOWNCOMPACTED);

		//Caso 3:matriz 3x3, los elementos de la fila 0 pasan a la última fila
		Game2048 b3 = new Game2048();
		b3.setBoard(SEMIFULL33);
		b3.compactDown();
		assertArrayEquals(b3.getBoard(),SEMIFULL3_DOWNCOMPACTED);

		//Caso 4:matriz 3x3, la matriz resultante será igual a la que se pasa
		Game2048 b4 = new Game2048();
		b4.setBoard(SEMIFULL41);
		b4.compactDown();
		assertArrayEquals(b4.getBoard(),SEMIFULL4_DOWNCOMPACTED);

		//Caso 5:matriz 3x3, los elementos de la fila 0 pasan a la fila 1
		Game2048 b5 = new Game2048();
		b5.setBoard(SEMIFULL42);
		b5.compactDown();
		assertArrayEquals(b5.getBoard(),SEMIFULL4_DOWNCOMPACTED);

		//Caso 6:matriz 3x3, los elementos de la fila 1 se mueven a la 2 y los de la 0 a la 1
		Game2048 b6 = new Game2048();
		b6.setBoard(SEMIFULL43);
		b6.compactDown();
		assertArrayEquals(b6.getBoard(),SEMIFULL4_DOWNCOMPACTED);
	}

	
}
