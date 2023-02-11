/**
 * Author: Hannah Soria
 * Date: 3/7/2022
 * File: Board.java
 * Section lab C, Lecture A
 * Project: 3 Sudoku
 * CS231 Spring 2022
**/

import java.awt.Color;
import java.awt.Graphics;

import java.io.*;
public class Board {
private Cell [][] grid;
public static final int Size = 9;

  public Board(){ // creates a new grid
    grid = new Cell[Board.Size][Board.Size];
    for (int i=0; i< Board.Size; i++){
      for (int j=0; j< Board.Size; j++){
        grid[i][j] = new Cell(i, j, 0);
      }
    }
  }
    public boolean read (String filename){
      try{
      
      FileReader fReader = new FileReader(filename);// assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      
      BufferedReader bReader = new BufferedReader (fReader);// assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      
      String line = bReader.readLine();// assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      int numRows = 0;
      while (line != null){// start a while loop that loops while line isn't null
        
        String[] arr = line.split("\\s+");// splits the string when there is a space
        
        for (int i = 0; i < arr.length; i++){
          String val = arr[i];
          int newVal = Integer.parseInt(val);
          grid[numRows][i].setValue(newVal);
        }
        numRows += 1;
        
        //System.out.println(line);// print the String (line)
        
        //System.out.println(arr.length);// print the size of the String array (you can use .length)
        
        line = bReader.readLine();// assign to line the result of calling the readLine method of your BufferedReader object.

      }
      
      bReader.close();// call the close method of the BufferedReader
      return true;// return true
      }
      catch(FileNotFoundException ex) {
        System.out.println("Board.read():: unable to open file " + filename );
      }
      catch(IOException ex) {
        System.out.println("Board.read():: error reading file " + filename);
      }

      return false;
      }

      public String toString(){ // this tostring prints the board
        String s = "";
        for (int i = 0; i < Board.Size; i++){
          for (int j = 0; j < Board.Size; j++){
            s += Integer.toString(grid[i][j].getValue());
            if ((j + 1) % 3 == 0){
              s += " ";
            }
          }
          s += "\n";
          if ((i + 1) % 3 == 0){
            s += "\n";
          }
        }
        return s;
      }

      public int getCols(){ //return number of columns
        return Board.Size;
      }

      public int getRows(){//return the number of rows
        return Board.Size;
      }

      public Cell get(int r, int c){ // return the Cell at location r, c.
        return grid[r][c];
      }

      public boolean isLocked(int r, int c){// returns if the cell at r,c is locked
        return grid[r][c].isLocked();
      }

      public int numLocked(){ //returns number of locked cells
        int lockedTotal = 0;
        for (int i=0; i<Board.Size; i++){
          for (int j=0; j<Board.Size; j++){
            if (grid[i][j].isLocked()){
              lockedTotal += 1;
            }
          }
        }
        return lockedTotal;
      }

      public int value(int r, int c){ //returns the value at cell r, c
        return grid[r][c].getValue();
      }

      public void set(int r, int c, int value){ //sets the value of the Cell at r,c
        grid[r][c].setValue(value);
      }

      public void set(int r, int c, int value, boolean locked){ // sets the value and locked fields of the Cell at r, c
        grid[r][c].setValue(value);
        grid[r][c].setLocked(locked);
      }

      //Hersha, Gretchen, and Ian helped me with this method
      public boolean validValue(int row, int col, int value){//tests if the given value is a valid value at the given row and column of the board. It should make sure the value is unique in its row, in its column, and in its local 3x3 square
        if (value < 1 || value > 9){
          return false;
        }
        else{
          for (int i=0; i< 9; i++){
            if (value == grid[row][i].getValue() && (i != col)){
              return false;
            }
        
            if (value == grid[i][col].getValue() && (i != row)){
              return false;
            }
          }
          
          int upperLeftRow = (row / 3) * 3; //when the value is taken and divided by 3 with no remainder then multiplied by 3 the value is the top left of the row in the 3x3 section
          int upperLeftCol = (col / 3) * 3;//when the value is taken and divided by 3 with no remainder then multiplied by 3 the value is the top left of the col in the 3x3 section
          for (int r = upperLeftRow; r < upperLeftRow + 3; r++){ //this iterates through the 3x3 setion
            for (int c = upperLeftCol; c < upperLeftCol + 3; c++){// this iterates throughthe 3x3 section
              if (grid[r][c].getValue() == value && (r != row || c != col)){ // if the value at r,c is equal to the value and not equal to one in the row or col
                return false;
              }
            }
          }
          return true;
        }
      }

      public boolean validSolution(){// returns true if the board is solved
        for (int i = 0; i < 9; i ++){
          for (int j = 0; j < 9; j ++){
            if (grid[i][j].getValue() == 0 || validValue(i, j, grid[i][j].getValue()) == false){
              return false;
            }
          }
        }
        return true;
      }

      public void draw(Graphics g, int scale){ //loop over the board and call the draw method of each Cell
        
        for (int i = 0; i < Board.Size ; i++){
          for (int j = 0; j < Board.Size ; j++){
            grid[i][j].getValue();
            grid[i][j].draw( g, i, j, 31);
            g.setColor(Color.white);
            g.drawLine(1 * (scale + 4), 3 * (scale + 4), 9 * (scale + 2), 3 * (scale + 4));
            g.drawLine(1 * (scale + 4), 6 * (scale + 3), 9 * (scale + 2), 6 * (scale + 3));
            g.drawLine(3 * (scale + 7), 1 * (scale ), 3 * (scale + 7), 9 * (scale + 1));
            g.drawLine(6 * (scale + 4), 1 * (scale ), 6 * (scale + 4), 9 * (scale + 1));
          }
        }
      }

      public static void main(String[]args){
        Board board = new Board();
        board.read("board_nsp_10_solved.txt");
        System.out.println(board);
        System.out.println(board.validValue(1,1,4));
        System.out.println(board.validValue(1,8,2));
        System.out.println(board.validValue(8,5,4));
        System.out.println(board.validValue(1,0,5));
        System.out.println(board.validSolution());
      }
    
}
