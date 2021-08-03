import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {
    TreeSet<String> treeSet = new TreeSet<>();
    Pattern pattern;
    Matcher matcher;
    String regex = "^[a-z]+[a-z0-9._-]+@" + "[a-z]+[a-z0-9]\\.[a-z]{2,3}";

    public void add(String email) {
        email = email.toLowerCase(Locale.ROOT);
        pattern = Pattern.compile(regex);
        if(validate(email)) {
            treeSet.add(email);
        }else {
            System.out.println("Неверный формат email");
        }
        // TODO: валидный формат email добавляется
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
        return new ArrayList<>(treeSet);
    }

    public boolean validate(String email) {
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
