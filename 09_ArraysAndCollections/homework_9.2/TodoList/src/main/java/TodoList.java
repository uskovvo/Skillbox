import java.util.ArrayList;

public class TodoList {
    ArrayList<String> todoList = new ArrayList<>();

    public void add(String todo) {
        todoList.add(todo);
        // TODO: добавьте переданное дело в конец списка
    }

    public void add(int index, String todo) {
        if (index < todoList.size() && index >= 0) {
            todoList.add(index, todo);
        }else {
            todoList.add(todo);
        }
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
    }

    public void edit(String todo, int index) {
        if (index < todoList.size() && index >= 0) {
            String nameIndex = todoList.get(index);
            todoList.set(index, todo);
            System.out.println("Дело " + "\"" + nameIndex + "\"" + " заменено на " + todo);
        }else {
            System.out.println("Дело с таким номером не существует");
        }
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
    }

    public void delete(int index) {
        if (index < todoList.size() && index >= 0) {
            String nameIndex = todoList.get(index);
            todoList.remove(index);
            System.out.println("Дело " + "\"" + nameIndex + "\"" + " удалено");
        }else {
            System.out.println("Дело с таким номером не существует");
        }
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return todoList;
    }

}