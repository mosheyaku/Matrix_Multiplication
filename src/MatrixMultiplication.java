public class MatrixMultiplication extends Thread {
    private int[][] matA;
    private int[][] matB;
    private int row;
    private int col;
    private int m;
    private MyMonitor monitor;

    public MatrixMultiplication(int[][] matA, int[][] matB, int row, int col, int m, MyMonitor monitor) {
        this.matA = matA;
        this.matB = matB;
        this.row = row;
        this.col = col;
        this.m = m;
        this.monitor = monitor;
    }

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