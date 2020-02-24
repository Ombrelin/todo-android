package fr.arsene.todo.modele;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class TodoService {

    private static TodoService INSTANCE;

    static {
        INSTANCE = new TodoService();
    }

    private List<Todo> todos = new LinkedList<Todo>();

    public TodoService() { }

    public List<Todo> getTodos() {
        return todos;
    }

    public static TodoService getINSTANCE() {
        return INSTANCE;
    }
}
