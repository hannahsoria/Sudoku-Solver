/**
 * Author: Hannah Soria
 * Date: 2/28/2022
 * File: CellStack.java
 * Section lab C, Lecture A
 * Project: 3 Sudoku
 * CS231 Spring 2022
**/

//meredith helped me debug and fix my code to run properly
public class CellStack {
    
    public static final int CAPACITY = 1000;//default capatcity
    
    private Cell[] data;// int array used for storage
    
    private int t = -1;// index of the top element in the stack
    
    public CellStack() { //constructs stack with default capacity
        this(CAPACITY);
    }

    public CellStack(int CAPACITY){ // initialize the stack to hold up to max elements
        data = new Cell [CAPACITY];
    }

    public void push(Cell c){ //if there is space, push c onto the stack
        if (size() == data.length) {
		    Cell[] temp = new Cell [data.length * 2];
            for (int i=0; i < data.length; i ++){
                temp[i] = data[i];
            }
            this.data = temp;
		}
		t++;
		data[t] = c;
    } 

    public Cell pop(){  //remove and return the top element from the stack; return null if the stack is empty
        if (empty() == true){
            return null;
        }
        else{
            Cell x = data[t];
            data[t] = null;
            t--;
            
            return x;
        }    
    }

    public int size(){ // return the number of selements on the stack
        return t + 1;
    }

    public boolean empty(){ //return true if the stack is empty
        if (size() == 0){
            return true;
        }
        else{
            return false;
        }
    }

}

