# Sudoku Solver

In this project I created a sudoku game that creates a board and solves it by finding a value that is the only one in the row, column, and 3x3 section. The project creates cells, utilizes a 2D array, stack, and graphics to get the final board. The cells are created and then put into a 9x9 grid. A stack is then used when solving the board. It is used to store the cells and keep track of backtracking when it gets stuck. Finally, using graphics, the board is displayed and runs with a 10 second delay update. It looks like any sudoku board and updates and solves when it is run.

The data structure implemented in this project is a stack. A stack is used to store a collection of objects based on last in first out. The only object that can be accessed is whatever is the top object. This is accessed by -1 index. The first object can be seen, popped off, and pushed on. Peeking returns what the first object is without doing anything with it. Popping the object gets rid of the top object and the next one becomes the new top object. Pushing an object adds a next object to the stack and this is the new top element.
In this implementation when the stack size is less than than the number of unspecified cells it tries to add the next possible cell by pushing it on. If the value is not valid then the cell is popped off. Then it backtracks and tries the next possible cell. This process is repeated for the entire board. The stack is useful here because a cell is pushed on and popped off and tried until it works. These useful methods are specific to the stack implementation.

When the Sudoku class is run with 10 locked values, then 20, then 30, and then 40 it takes longer to solve the board as the number increases. Eventually the board is unsolvable. The lower the number is, the quicker the board is solved. This makes sense because there are more valid values on the board when less cells are locked. Eventually when many cells are locked there are very few options for valid values. This causes the board to take longer to solve because more values must be tried. Below on this grid the results are shown for runtime in nanoseconds.


<img width="329" alt="Screen Shot 2023-02-10 at 11 33 40 PM" src="https://user-images.githubusercontent.com/113323340/218286113-42060683-9d9b-4883-8fb2-ca8ba16293d3.png">
