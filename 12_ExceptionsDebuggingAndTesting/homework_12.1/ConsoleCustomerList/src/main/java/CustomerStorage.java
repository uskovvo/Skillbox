import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if(components.length > 4){
            throw new ArrayIndexOutOfBoundsException();
        }
        if(!formatPhoneNumber(components)){
            throw new IllegalArgumentException("Неверный формат телефона.");
        }
        if(!formatEmail(components)){
            throw new IllegalArgumentException("Неверный формат электронной почты.");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    private boolean formatPhoneNumber(String[] components) {
        String pnoneNumber = components[3];
        String fullNumber = "7";
        pnoneNumber = pnoneNumber.replaceAll("[^0-9]", "");
        if (pnoneNumber.length() > 11 || pnoneNumber.length() < 10) {
            return false;
        } else if (pnoneNumber.length() == 10) {
            fullNumber += pnoneNumber;
            return true;
        } else if (pnoneNumber.charAt(0) == '8' || pnoneNumber.charAt(0) == '7') {
            if (pnoneNumber.charAt(0) == '8') {
                pnoneNumber = pnoneNumber.replace(pnoneNumber.charAt(0), '7');
                return true;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean formatEmail(String[] components){
        String email = components[2];
        String regexMail = "^[a-z]+[a-z0-9._-]+@" + "[a-z]+[a-z0-9]\\.[a-z]{2,3}";
        email = email.toLowerCase(Locale.ROOT);
        Pattern pattern = Pattern.compile(regexMail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}