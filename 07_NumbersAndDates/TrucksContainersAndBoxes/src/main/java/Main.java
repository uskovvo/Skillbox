import net.sf.saxon.expr.Component;

import java.util.Scanner;

public class Main {
    public static final int CONTAINER_MAX = 12;
    public static final int BOX_MAX = 27;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int allBoxes = Integer.parseInt(boxes);
        int container = 0;
        int truck = 0;

        if (allBoxes > 0) {
            truck++;
            container++;
            System.out.println("Грузовик: " + getTruck(truck));
            System.out.println("\tКонтейнер: " + getContainer(container));
            for (int a = 1; a <= allBoxes; a++) {
                System.out.println("\t\tЯщик: " + a);
                if (a == allBoxes) {
                    break;
                }
                if (container % CONTAINER_MAX == 0 && a % BOX_MAX == 0) {
                    truck++;
                    System.out.println("Грузовик: " + getTruck(truck));

                }
                if (a % BOX_MAX == 0) {
                    container++;
                    System.out.println("\tКонтейнер: " + getContainer(container));
                }
            }
        }
        System.out.println("Необходимо:");
        System.out.println("грузовиков" + " - " + truck + " шт.");
        System.out.println("контейнеров" + " - " + container + " шт.");
//            System.out.println("Грузовик: " + truck);
//            System.out.println("\t Контейнер: " + container);
//                System.out.println("\t\t Ящик: " + a);
//            System.out.println("Необходимо:");
//            System.out.println("грузовиков " + "-" + truck + " шт.");
//            System.out.println("контейнеров " + "-" + container + " шт.");
        // TODO: вывести в консоль коробки разложенные по грузовикам и контейнерам
        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

    public static int getContainer(int container) {
        return container;
    }

    public static int getTruck(int truck) {
        return truck;
    }
}
