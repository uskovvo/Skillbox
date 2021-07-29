public class ReverseArray {

    //TODO: Напишите код, который меняет порядок расположения элементов внутри массива на обратный.
    public static String[] reverse(String[] strings) {
        for (int a = 0; a < strings.length / 2; a++) {
            String element = strings[a];
            strings[a] = strings[strings.length - 1 - a];
            strings[strings.length - 1 - a] = element;
        }
        return strings;
    }
}
