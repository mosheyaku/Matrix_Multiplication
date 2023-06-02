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
}
