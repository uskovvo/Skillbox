import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    TreeMap<String, String> phoneBook = new TreeMap<>();
    Pattern patternName;
    Pattern patternNumber;
    Matcher matcherName;
    Matcher matcherNumber;
    String regexName = "[А-Я][а-я]+";
    String regexNumber = "[78][0-9]{10}";

    public void addContact(String phone, String name) {
        patternName = Pattern.compile(regexName);
        patternNumber = Pattern.compile(regexNumber);
        if (validateName(name) && validateNumber(phone)) {
            phoneBook.put(phone, name);
        }
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getNameByPhone(String phone) {
        if (validateNumber(phone)) {
            for (String key : phoneBook.keySet()) {
                if (phoneBook.containsKey(phone)) {
                    return phoneBook.get(key) + " - " + key;
                }
            }
        }
        System.out.println("Такого номера нет в телефонной книге.");
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        TreeSet<String> contactsByName = new TreeSet<>();
        if (validateName(name)) {
            for (String key : phoneBook.keySet()) {
                if (phoneBook.get(key).equals(name)) {
                    contactsByName.add(name + " - " + key);
                }
            }
        }
        if (contactsByName.isEmpty()) System.out.println("Такого имени в телефонной книге нет.");
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return contactsByName;
    }

    public Set<String> getAllContacts() {
        TreeSet<String> contacts = new TreeSet<>();
        String phone;
        String name;
        for (String key : phoneBook.keySet()) {
            name = phoneBook.get(key);
            phone = String.join(", ", getPhonesByName(name));
            phone = phone.replaceAll(name + " - ", "");
            contacts.add(name + " - " + phone);
        }
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return contacts;
    }

    public boolean validateName(String name) {
        matcherName = patternName.matcher(name);
        if (matcherName.find()) {
            return matcherName.matches();
        }
        System.out.println("Неверный формат ввода");
        return false;
    }

    public boolean validateNumber(String phone) {
        matcherNumber = patternNumber.matcher(phone);
        if (matcherNumber.find()) {
            return matcherNumber.matches();
        }
        System.out.println("Неверный формат ввода");
        return false;
    }
}