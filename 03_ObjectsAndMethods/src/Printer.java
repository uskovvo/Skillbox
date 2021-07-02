public class Printer {
    private String queue = "";
    private static int pages = 0;
    private int totalPages = 0;
    private int allPages = 0;

    public Printer() {
        queue = "Очередь печати:";
    }

    public void append(String text) {
        append(text, "", 1);
    }

    public void append(String text, String titleDoc) {
        append(text, titleDoc, 1);
    }

    public void append(String text, String titleDoc, int quantityPages) {

        queue = queue + "\n\n" + titleDoc + "\n" + text + "\n" + "Документ содержит " +
                quantityPages + " страниц";
        totalPages = totalPages + quantityPages;
        allPages = allPages + quantityPages;
    }

    public int getPendingPagesCount() {
        return totalPages;
    }

    public int getAllPages() {
        return allPages;
    }

    public void clear() {
        queue = "";
        totalPages = 0;
    }

    public void print() {
        if (queue.isEmpty()) {
            System.out.println("Очередь печати пуста");
        }else {
            System.out.println(queue);
            System.out.println("Общее кол-во страниц в очереди: " + getPendingPagesCount());
        }

        System.out.println("Кол-во страниц распечатанных за все время: " + getAllPages());
    }
}
