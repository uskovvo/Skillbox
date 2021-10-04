import java.util.Scanner;

public class Main {
    private static String sourcePath;
    private static String destinationPath;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            for (; ; ) {
                System.out.println("Введите путь копируемой папке.");
                scanner = new Scanner(System.in);
                sourcePath = scanner.nextLine();
                System.out.println("Введите путь куда копировать.");
                destinationPath = scanner.nextLine();
                FileUtils.copyFolder(sourcePath, destinationPath);

                System.out.println("Копируем папку: " + sourcePath + "\n" +
                        "В папку: " + destinationPath + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
