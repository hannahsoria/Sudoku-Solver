/**
 * Author: Hannah Soria
 * Date: 3/7/2022
 * File: Sudoku.jav
 * Section lab C, Lecture A
 * Project: 3 Sudoku
 * CS231 Spring 2022
**/

import java.util.Random;
   
public class Sudoku {
    private Board sudokuBoard;
    private LandscapeDisplay display;//mary helped me syntax this

    public Sudoku(){//creates a new Board
        display = new LandscapeDisplay(sudokuBoard, 30);
        System.out.println(sudokuBoard = new Board());
    }

    public Sudoku(int N){//takes in an int parameter that is the number of populated starting values N
        sudokuBoard = new Board();
        display = new LandscapeDisplay(sudokuBoard, 30);
        Random ran = new Random();
        int counter = 0;
        
        while (counter<N){
            int ranValue = ran.nextInt(8) + 1;
            int ranRow = ran.nextInt(8) + 1;
            int ranCol = ran.nextInt(8) + 1;
            if (sudokuBoard.validValue(ranRow, ranCol, ranValue) == true && sudokuBoard.isLocked(ranRow, ranCol) == false){ //gretchen helped me a little bit here with syntax
                sudokuBoard.set(ranRow, ranCol, ranValue, true);
                counter+=1;
            }
        }
    }

    public Cell findBestCell(){// find and return the next cell to check
        for (int i = 0; i < Board.Size; i ++){
            for (int j = 0; j < Board.Size; j++){
                if (sudokuBoard.value(i,j) == 0){
                    for (int k = 1; k < 10; k ++){
                        if (sudokuBoard.validValue(i,j,k) == true){
                            sudokuBoard.set(i,j,k);
                            return sudokuBoard.get(i,j);
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }

    // the TA nick helped me with this code
    public boolean solve(int delay){ // given a stack and number of locked cells this method goes through each cell and solves it
        CellStack stack = new CellStack();
        boolean backtrack = false;
        while(stack.size() < 81 - sudokuBoard.numLocked()){
            Cell bestCell = findBestCell();
            if (bestCell!=null){
                stack.push(bestCell);//add it to stack
                sudokuBoard.set(bestCell.getRow(),bestCell.getCol(),bestCell.getValue(), false);// set the value
            }
            else if (bestCell == null){
                backtrack = true;// make it set to true
            
                while(backtrack == true){
                    Cell nextCell = stack.pop();//Prof Bender explained to me how I need to set this to a variable
                    for (int i = nextCell.getValue(); i < 10; i ++){// go through all values to nine
                        if (sudokuBoard.validValue(nextCell.getRow(),nextCell.getCol(),i + 1)){
                            nextCell.setValue(i + 1);
                            stack.push(nextCell);
                            sudokuBoard.set(nextCell.getRow(),nextCell.getCol(),nextCell.getValue());
                            backtrack = false;
                            break;
                        }                     
                    }
                    if (backtrack == true){
                        sudokuBoard.set(nextCell.getRow(),nextCell.getCol(),0);// set the cell
                    }
                }
                if(stack.size()== 0){
                    return false;
                } 
            }

                //delay++;
                if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            
                }
            }
            return true;
    }
    

    public String toString(){// generate and return a representating String
        String s = "";
        return s + sudokuBoard;
    }

    public static void main(String[]args){// this method prints out the board
        Sudoku test = new Sudoku(Integer.parseInt(args[0]));
        System.out.println(test);
        long start1 = System.nanoTime(); //https://www.tutorialspoint.com/how-to-measure-execution-time-for-a-java-method
        System.out.println(test.solve(10));
        long end1 = System.nanoTime();
        double firstAvg = (end1 - start1);
        System.out.println(test);
        System.out.println(firstAvg);
    }
}
