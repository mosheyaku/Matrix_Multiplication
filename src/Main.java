import java.util.Random;
import java.util.Scanner;

/* The Main class serves as the entry point for the program and coordinates
the matrix multiplication process.
It provides methods for matrix generation, display, and thread management.
*/
public class Main {

    private static final int UPPER_BOUND_OF_MATRIX_VALUES = 11;

    /*The main method of the program.*/
    public static void main(String[] args) {
        boolean isValidMatrixSizes = false;
        int n = 0, m = 0, p = 0;
        //Prompt the user to enter valid matrix sizes until valid sizes are provided.
        while (!isValidMatrixSizes) {
            try {
                Scanner scanner = new Scanner(System.in);
                // Get the dimensions of the matrices from the user.
                n = isValidSize(scanner, 'n');
                m = isValidSize(scanner, 'm');
                p = isValidSize(scanner, 'p');

                isValidMatrixSizes = true;
            } catch (Throwable t) {
                System.out.println("Error!\nPlease choose positive numbers.\n");
            }
        }
        // Create the matrices
        int[][] matA = new int[n][m];
        int[][] matB = new int[m][p];

        // Generate random values for the matrices.
        Random rand = new Random();
        setMatrix(matA, rand, n, m);
        setMatrix(matB, rand, m, p);

        // Display the matrices
        displayMatrix(matA, 'A');
        displayMatrix(matB, 'B');

        // Create monitor and thread arrays.
        Monitor monitor = new Monitor(n, p);
        MatrixMultiplication t[][] = new MatrixMultiplication[n][p];

        // Create threads for matrix multiplication.
        createThreads(t, matA, matB, monitor, m);

        // Run the matrix multiplication threads
        System.out.println("The multiplication of matrix A and matrix B:");
        runThreads(t);
    }

    /*The method receives an input and checks whether it is a positive integer.
    If so, it returns it, if not, an error will be returned.*/
    public static int isValidSize(Scanner scanner, char sizeName) {
        int size = 0;
        System.out.printf("Please choose %c: ", sizeName);
        size = scanner.nextInt();
        if (size < 1)
            throw new Error();
        return size;
    }

    /*The method gets a matrix and fills it with random integer values.*/
    public static void setMatrix(int[][] matrix, Random rand, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = rand.nextInt(UPPER_BOUND_OF_MATRIX_VALUES);
            }
        }
    }

    /*This method displays the elements of the given matrix.*/
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

    /*This method creates threads for matrix multiplication
    and assigns them to the provided thread array.*/
    public static void createThreads(MatrixMultiplication t[][], int[][] matA, int[][] matB, Monitor monitor, int m) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j] = new MatrixMultiplication(matA, matB, i, j, m, monitor);
            }
        }
    }

    /*This method starts the matrix multiplication threads.*/
    public static void runThreads(MatrixMultiplication t[][]) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                t[i][j].start();
            }
        }
    }
}
