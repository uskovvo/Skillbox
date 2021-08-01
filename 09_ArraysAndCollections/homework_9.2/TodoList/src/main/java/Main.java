import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final TodoList todoList = new TodoList();

    public static void main(String[] args) {
        while (true) {
            Scanner str = new Scanner(System.in);
            String request = str.nextLine();
            if (request.equals("0")) {
                break;
            }
            String command = findCommand(request);
            String todo = findTodo(request);
            int index = findIndex(request);
            if (command.equals("ADD")) {
                if(index >= 0 && index < todoList.getTodos().size()) {
                    todoList.add(index, todo);
                    System.out.println("Добавлено дело " + todo);
                } else if(index > todoList.getTodos().size()){
                    todoList.add(todo);
                    System.out.println("Добавлено дело " + todo);
                } else if (index == -1) {
                    todoList.add(todo);
                    System.out.println("Добавлено дело " + todo);
                }
            }

            if (command.equals("EDIT")) {
                if (index >= 0 && index < todoList.getTodos().size()) {
                    String nameIndex = todoList.getTodos().get(index);
                    todoList.edit(todo, index);
                    System.out.println("Дело " + "\"" + nameIndex + "\"" + " заменено на " + todo);
                } else {
                    System.out.println("Дело с таким номером не существует");
                }
            }

            if (command.equals("DELETE")) {
                if (index >= 0 && index < todoList.getTodos().size()) {
                    String nameIndex = todoList.getTodos().get(index);
                    todoList.delete(index);
                    System.out.println("Дело " + "\"" + nameIndex + "\"" + " удалено");
                } else {
                    System.out.println("Дело с таким номером не существует");
                }
            }

            if (command.equals("LIST")) {
                for (int a = 0; a < todoList.getTodos().size(); a++) {
                    System.out.println(a + " - " + todoList.getTodos().get(a));
                }
            }
        }

        // TODO: написать консольное приложение для работы со списком дел todoList
    }

    public static String findCommand(String request) {
        String command;
        String regex = "[\\W+0-9]";
        request = request.replaceAll(regex, " ").trim();
        int start = request.indexOf("");
        int end = request.indexOf(" ");
        if (end == -1) {
            command = request.substring(start);
        } else {
            command = request.substring(start, end);
        }
        command = command.toUpperCase(Locale.ROOT);

        return command;
    }

    public static String findTodo(String request) {
        String regex = "[\\W+0-9]";
        request = request.replaceAll(regex, " ").trim();
        int start = request.indexOf(" ");
        if (start == -1) {
            return "";
        }

        return request.substring(start).trim();
    }

    public static int findIndex(String request) {
        int index;
        String regex = "[^0-9]";
        request = request.replaceAll(regex, " ").trim();
        if (request.isEmpty()) {
            return -1;
        }
        index = Integer.parseInt(request);

        return index;
    }
}
