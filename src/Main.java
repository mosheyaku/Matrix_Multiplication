import java.util.Scanner;

public class Main {

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
    }


    public static int isValidSize(Scanner scanner, char sizeName) {
        int size = 0;
        System.out.printf("Please choose %c: ", sizeName);
        size = scanner.nextInt();
        if (size < 1)
            throw new Error();
        return size;
    }
}
