import java.util.ArrayList;

public class TodoList {
    ArrayList<String> todoList = new ArrayList<>();

    public void add(String todo) {
        todoList.add(todo);
        // TODO: добавьте переданное дело в конец списка
    }

    public void add(int index, String todo) {
        todoList.add(index, todo);
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
    }

    public void edit(String todo, int index) {
        todoList.set(index, todo);
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
    }

    public void delete(int index) {
        todoList.remove(index);
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел
        return todoList;
    }

}