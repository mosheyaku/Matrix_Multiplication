public class MyMonitor {
    private int currentRow = 0;
    private int currentCol = 0;
    private int totalRows;
    private int totalCols;

    public MyMonitor(int totalRows, int totalCols) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;
    }

    public synchronized void waitForMyTurn(int row, int col) {
        while (row != currentRow || col != currentCol) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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

