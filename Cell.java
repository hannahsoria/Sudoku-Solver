/**
 * Author: Hannah Soria
 * Date: 3/7/2022
 * File: Cell.java
 * Section lab C, Lecture A
 * Project: 3 Sudoku
 * CS231 Spring 2022
**/

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
    int rowIndex;
    int colIndex;
    int valueInt;
    boolean lockedBool;
    
    public Cell(){ //initialize all valus to 0 or false
        rowIndex = 0;
        colIndex = 0;
        valueInt = 0;
        lockedBool = false;
    }

    public Cell( int row, int col, int value){ //initialize the row, column, and value fields to the given parameter values. Set the locked field to false
        this.rowIndex = row;
        this.colIndex = col;
        this.valueInt = value;
        this.lockedBool = false;
    }

    public Cell( int row, int col, int value, boolean locked){ //initialize all of the Cell fields given the parameters
        this.rowIndex = row;
        this.colIndex = col;
        this.valueInt = value;
        this.lockedBool = locked;
    }
    
    public int getRow(){ //return the Cell's row index
        return rowIndex;
    }

    public int getCol(){ //return the Cell's column index
        return colIndex;
    }

    public int getValue(){ //return the Cell's value
        return valueInt;
    }

    public void setValue(int newval){ //set the Cell's value
        valueInt = newval;
    }

    public boolean isLocked(){ //return the value of the locked field
        return lockedBool;
    }

    public void setLocked (boolean lock){; //set the Cell's locked field to the new value
        lockedBool = lock;
    }

    public Cell clone(){ // return a new Cell with the same values as the existing Cell
        Cell newCell = new Cell(this.rowIndex, this.colIndex, this.valueInt, this.lockedBool);
        return newCell;
    }

    public String toString(){ //generate and return a representating String
        String s = "";
        s += "position: " + rowIndex + ", " + colIndex + " value: " + valueInt + " locked: " + lockedBool;
        return s;
    }

    public void draw(Graphics g, int x0, int y0, int scale){ // draws the Cell's number
        char[] charArray = new char[2];
        charArray[0] = (char) ('0' + this.valueInt);
        charArray[1] = (char) (0);
        g.setColor(Color.black);
        if (this.valueInt == 1){
            g.setColor(Color.RED);
        }
        if (this.valueInt == 2){
            g.setColor(Color.orange);
        }
        if (this.valueInt == 3){
            g.setColor(Color.YELLOW);
        }
        if (this.valueInt == 4){
            g.setColor(Color.green);
        }
        if (this.valueInt == 5){
            g.setColor(Color.blue);
        }
        if (this.valueInt == 6){
            g.setColor(Color.pink);
        }
        if (this.valueInt == 7){
            g.setColor(Color.CYAN);
        }
        if (this.valueInt == 8){
            g.setColor(Color.LIGHT_GRAY);
        }
        if (this.valueInt == 9){
            g.setColor(Color.magenta);
        }
        g.drawChars(charArray, 0, charArray.length, (x0 +1) * scale, (y0 + 1)* scale);
        

    }

    public static void main(String[]args){
    }

}
