/*A class that runs the threads.*/
public class MatrixMultiplication extends Thread {
    private int[][] matA;
    private int[][] matB;
    private int row;
    private int col;
    private int m;
    private Monitor monitor;

    /*A constructor that receives 2 matrices, a row index and a column index
    (of the array of the result of the multiplication between the 2 matrices),
    the number of columns of the first matrix which is also the number
    of rows in the second matrix and a monitor type object.*/
    public MatrixMultiplication(int[][] matA, int[][] matB, int row, int col, int m, Monitor monitor) {
        this.matA = matA;
        this.matB = matB;
        this.row = row;
        this.col = col;
        this.m = m;
        this.monitor = monitor;
    }

    /*This method runs the threads.*/
    @Override
    public void run() {
        int cell = 0;
        for (int k = 0; k < m; k++) {
            cell += matA[row][k] * matB[k][col];
        }
        monitor.waitForMyTurn(row, col);
        System.out.printf("%2d ", cell);
        monitor.imDone();
    }
}