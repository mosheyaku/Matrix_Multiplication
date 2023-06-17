/*A Monitor class that used as a monitor for synchronization between threads.*/
public class Monitor {
    private int currentRow = 0;
    private int currentCol = 0;
    private int totalRows;
    private int totalCols;

    /*A constructor that gets the number of rows and the number of columns in matrix.*/
    public Monitor(int totalRows, int totalCols) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;
    }

    /*A method that receives a row and a column of a matrix and checks whether it
    is the turn to calculate the cell that is in this row and column.
    If not, the method makes the current thread wait until its turn to execute.*/
    public synchronized void waitForMyTurn(int row, int col) {
        while (row != currentRow || col != currentCol) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*The method calculates the position of the next cell that needs to be calculated
    (in the result matrix) and releases all the threads that are waiting to be executed.*/
    public synchronized void imDone() {
        currentCol++;
        if (currentCol >= totalCols) {
            currentCol = 0;
            currentRow++;
            System.out.println();
        }
        notifyAll();
    }
}

