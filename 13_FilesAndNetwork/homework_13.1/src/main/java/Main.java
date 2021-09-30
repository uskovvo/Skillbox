import java.util.Scanner;

public class Main {
    private static long size;
    private static String path;
    private static Scanner scanner;
    private static final long MAX_SIZE = 1_024L;
    private static String unit = "byte";

    public static void main(String[] args) {
        try {
            for (; ; ) {
                System.out.println("Введите путь к папке: ");
                scanner = new Scanner(System.in);
                path = scanner.nextLine();
                System.out.println("Пользователь ввёл: " + path);
                size = FileUtils.calculateFolderSize(path);
                System.out.println(toString(size));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String toString(long size) {
        if (size > MAX_SIZE) {
            size /= MAX_SIZE;
            unit = "Kb";
        }
        if (size > MAX_SIZE) {
            size /= MAX_SIZE;
            unit = "Mb";
        }
        if (size > MAX_SIZE) {
            size /= MAX_SIZE;
            unit = "Gb";
        }
        return "Размер выбранной папки: " + size + unit + "\n";
    }
}
