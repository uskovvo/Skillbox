public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.

        for (char a = 'А'; a < 'я'; a++) {
            int b = a;
            System.out.println(a + " - " + b);
        }
        char c = 'Ё';
        int d = c;
        System.out.println(c + " - " + d);

        char a = 'ё';
        int b = a;
        System.out.println(a + " - " + b);
    }
}
