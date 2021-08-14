public class Main {
    public static void main(String[] args) {
        Client client = new PhysicalPerson();
        client.put(1000);
        client.getAmount();
        client.take(500);
        client.getAmount();

    }
}
