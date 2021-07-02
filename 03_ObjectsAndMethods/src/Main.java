import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Basket basket1 = new Basket();
        basket1.add("Яблоки", 120, 10, 1.52);
        basket1.add("Хлеб", 45, 2);
        basket1.add("Насос", 300);
        basket1.add("Бананы", 80, 6, 1.01);
        basket1.print("Моя корзина");
        basket1.clear();
        basket1.print("Моя корзина");

        Arithmetic arithmetic = new Arithmetic(110, 115);
        arithmetic.sumNumbers();
        arithmetic.multiplicationNumbers();
        arithmetic.maxOfNumbers();
        arithmetic.minOfNumbers();

        Printer printer = new Printer();
        printer.append("Люблю грозу в начале мая...", "Стих", 50);
        printer.append("Я достаю из широких штанин...", "Маяковский");
        printer.print();
        printer.clear();
        printer.print();
        printer.append("Я на тебе, как на войне...");
        printer.print();
        printer.clear();
        printer.print();
    }
}
