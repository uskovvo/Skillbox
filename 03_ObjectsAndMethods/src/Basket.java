public class Basket {

    private static int count = 0;
    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight = 0;

    public static int fullPriceAllBasket = 0;
    public static int fullCountItemsAllBasket = 0;
    public static double averageBasketPrice;

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public static int getCount() {
        return count;
    }

    public static int increaseCount(int count) {
        Basket.count = Basket.count + count;
        return count;
    }

    public void add(String name, int price) {
        add(name, price, 1, 0);
    }

    public void add(String name, int price, int count) {
        add(name, price, count, 0);
    }

    public void add (String name, int price, int count, double weight) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price + " руб.";
        totalPrice = totalPrice + count * price;
        totalWeight = totalWeight + weight;
        calculatePrice(price, count);
        calculateCountItems(count);
        averageBasketPrice();
    }

    public static int getFullPriceAllBasket() {
        return fullPriceAllBasket;
    }

    public static int getFullCountItemsAllBasket() {
        return fullCountItemsAllBasket;
    }

    public static void calculatePrice(int price, int count) {
        fullPriceAllBasket = fullPriceAllBasket + price * count;
    }

    public static void calculateCountItems(int count) {
        fullCountItemsAllBasket = fullCountItemsAllBasket + count;
    }

    public static double averagePrice() {
        return (double) fullPriceAllBasket / fullCountItemsAllBasket;
    }

    public static double getAverageBasketPrice() {
        return averageBasketPrice;
    }
    public static void averageBasketPrice() {
        averageBasketPrice = (double) getFullPriceAllBasket() / Basket.getCount();
    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println("\n" + title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
            System.out.println("Сумма ваших товаров в корзине: " + getTotalPrice() + " руб.");
            System.out.println("Общий вес товаров: " + (float)getTotalWeight() + " кг.");
        } else {
            System.out.println(items);
            System.out.println("Сумма ваших товаров в корзине: " + getTotalPrice() + " руб.");
            System.out.println("Общий вес товаров: " + (float)getTotalWeight() + " кг.");
        }
    }
}
