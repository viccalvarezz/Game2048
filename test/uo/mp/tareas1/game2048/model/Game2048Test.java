package uo.mp.tareas1.game2048.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
	
	
}
