/**
 * Author: Hannah Soria
 * Date: 3/7/2022
 * File: SudokuSimulation.java
 * Section lab C, Lecture A
 * Project: 3 Sudoku
 * CS231 Spring 2022
**/

public class SudokuSimulation {

    public static void main(String[]args){
        int firstAvg = 0;
        int secondAvg = 0;
        int lastAvg = 0;
        int totalGames = 100;
        
        for (int i = 0; i< totalGames; i ++){
            Sudoku sudokuSim1 = new Sudoku(1);
            Sudoku sudokuSim2 = new Sudoku(4);
            Sudoku sudokuSim3 = new Sudoku(8);
            long start1 = System.nanoTime(); //https://www.tutorialspoint.com/how-to-measure-execution-time-for-a-java-method
            sudokuSim1.solve(0);
            long end1 = System.nanoTime();
            firstAvg += (end1 - start1);
            long start2 = System.nanoTime();
            sudokuSim2.solve(0);
            long end2 = System.nanoTime();
            secondAvg += (end2 - start2);
            long start3 = System.nanoTime();
            sudokuSim3.solve(0);
            long end3 = System.nanoTime();
            lastAvg += (end3 - start3);
        }
        double firstTotal = firstAvg / totalGames;
        double secondTotal = secondAvg / totalGames;
        double lastTotal = lastAvg / totalGames;

        System.out.println("Average speed of 1 locked values: " + firstTotal);
        System.out.println("Average speed of 4 locked values: " + secondTotal);
        System.out.println("Average speed of 8 locked values: " + lastTotal);
    }
    
}
