import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        ArrayList<String> list = new ArrayList<>();
        String[] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        String number;
        String region;
        for (int a = 0; a < letters.length; a++) {
            for (int b = 0; b < letters.length; b++) {
                for (int c = 0; c < letters.length; c++) {
                    for (int d = 1; d < 10; d++) {
                        for(int r = 1; r < 200; r++) {
                            region = "" + r;
                            if (r < 10) {
                                region = "0" + r;
                            }
                            number = "" + letters[a] + d + d + d + letters[b] + letters[c] + region;
                            list.add(number);
                        }
                    }
                }
            }
        }

        return list;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for(String lists : list){
            if(number.equals(lists)){
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        return Collections.binarySearch(sortedList, number) >= 0;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
