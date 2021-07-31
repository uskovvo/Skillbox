public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {
        char[][] symbol = new char[size][size];

        for(int a = 0; a < size; a++) {
            for(int b = 0; b < size; b++) {
                symbol[a][b] = ' ';
            }
            symbol[a][a] = 'X';
            symbol[a][size - 1 - a] = 'X';
        }

        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                System.out.print(symbol[a][b]);
            }
            System.out.println();
        }
        return symbol;
    }
}
