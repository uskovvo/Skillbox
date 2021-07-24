import org.checkerframework.checker.units.qual.C;

public class Main {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        //TODO: напишите ваш код, результат вывести в консоль
        int a = summMan(text, "Вася");
        int b = summMan(text, "Петя");
        int c = summMan(text, "Маша");
        int summ = a + b + c;
        System.out.println(summ);
    }

    public static int summMan(String str, String name) {
        int earn = 0;
        int i;

        for (int a = 0; a < str.length(); a++) {
            i = str.indexOf(name); //определяем индекс переданной строки name
            if (i == -1) break; //если не нашли переданную переменную, возвращаемся в начало
            str = str.substring(i + name.length()).trim(); //получаем строку после определенного индекса и его
            //длины, убираем лишние пробелы спереди и сзади
            i = str.indexOf(" руб"); //находим индекс по переданной строке
            if (i == -1) break; //если не нашли переданную переменную, возвращаемся в начало
            str = str.substring(0, i).trim(); //получаем строку от нулевого индекса и до найденного индекса,
            //убираем пробелы с двух сторон
            i = str.lastIndexOf(" "); //находим индекс по переданной строке с конца найденной строки
            if (i == -1) break; //если не нашли переданную переменную, возвращаемся в начало
            earn = Integer.parseInt(str.substring(i, str.length()).trim()); //получаем число через преобразование
            //строки в число
        }
        return earn; //возвращаем найденное число
    }
}