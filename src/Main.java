import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int UPPER_BOUND_OF_MATRIX_VALUES = 11;

    public static void main(String[] args) {
        boolean isValidMatrixSizes = false;
        int n = 0, m = 0, p = 0;
        while (!isValidMatrixSizes) {
            try {
                Scanner scanner = new Scanner(System.in);
                n = isValidSize(scanner, 'n');
                m = isValidSize(scanner, 'm');
                p = isValidSize(scanner, 'p');

                isValidMatrixSizes = true;
            } catch (Throwable t) {
                System.out.println("Error!\nPlease choose positive numbers.\n");
            }
        }

        int[][] matA = new int[n][m];
        int[][] matB = new int[m][p];

        Random rand = new Random();

        setMatrix(matA, rand, n, m);

        setMatrix(matB, rand, m, p);

        displayMatrix(matA, 'A');

        displayMatrix(matB, 'B');

        MyMonitor monitor = new MyMonitor(n, p);
        MatrixMultiplication t[][] = new MatrixMultiplication[n][p];

        createThreads(t, matA, matB, monitor, m);

        System.out.println("The multiplication of matrix A and matrix B:");
        runThreads(t);
    }


    public static int isValidSize(Scanner scanner, char sizeName) {
        int size = 0;
        System.out.printf("Please choose %c: ", sizeName);
        size = scanner.nextInt();
        if (size < 1)
            throw new Error();
        return size;
    }

    public static void setMatrix(int[][] matrix, Random rand, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = rand.nextInt(UPPER_BOUND_OF_MATRIX_VALUES);
            }
        }
    }

    public static void displayMatrix(int[][] matrix, char matrixName) {
        System.out.println("Matrix " + matrixName);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void createThreads(MatrixMultiplication t[][], int[][] matA, int[][] matB, MyMonitor monitor, int m) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j] = new MatrixMultiplication(matA, matB, i, j, m, monitor);
            }
        }
    }

    public static void runThreads(MatrixMultiplication t[][]) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j].start();
            }
        }
    }
}
