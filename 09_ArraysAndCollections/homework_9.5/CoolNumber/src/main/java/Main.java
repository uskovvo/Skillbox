import java.util.*;

public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска вводимого номера в консоль в каждой из структуры данных
     - проанализировать полученные данные
     */

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(CoolNumbers.generateCoolNumbers());
        HashSet<String> hashSet = new HashSet<>(list);
        TreeSet<String> treeSet = new TreeSet<>(list);

        long start = System.nanoTime();
        CoolNumbers.bruteForceSearchInList(list, "П666ВА177");
        long end = System.nanoTime();
        System.out.println("Потраченное время поиска перебором: " + (end - start) + " нс");

        Collections.sort(list);
        start = System.nanoTime();
        CoolNumbers.binarySearchInList(list, "П666ВА177");
        end = System.nanoTime();
        System.out.println("Потраченное время бинарным поиском: " + (end - start) + " нс");

        start = System.nanoTime();
        CoolNumbers.searchInHashSet(hashSet, "П666ВА177");
        end = System.nanoTime();
        System.out.println("Потраченное время поиска в HashSet: " + (end - start) + " нс");

        start = System.nanoTime();
        CoolNumbers.searchInTreeSet(treeSet, "П666ВА177");
        end = System.nanoTime();
        System.out.println("Потраченное время поиска в TreeSet: " + (end - start) + " нс");
    }
}
