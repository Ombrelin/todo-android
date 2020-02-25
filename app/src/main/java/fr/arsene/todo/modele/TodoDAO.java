package fr.arsene.todo.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import java.util.LinkedList;
import java.util.List;

public class TodoDAO extends DAO<Todo> {
    /**
     * Initilise le DAO
     *
     * @param context Le contexte qui correspond à la base de donnee
     */
    public TodoDAO(Context context) {
        super(context);
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todos = new LinkedList<>();

        Cursor result = this.getDatabase().rawQuery("SELECT * FROM todos", null);

        while (result.moveToNext()) {
            int id = result.getInt(0);
            String title = result.getString(1);
            String description = result.getString(2);

            todos.add(new Todo(id, title, description));
        }
        result.close();

        return todos;
    }

    @Override
    public Todo getFromId(int id) {
        return null;
    }

    @Override
    public void insert(Todo tuple) {
        SQLiteStatement requete = this.getDatabase().compileStatement("INSERT INTO todos(title_todo,discription_todo)" +
                " VALUES (?,?)");

        requete.bindString(1,tuple.getTitle());
        requete.bindString(2, tuple.getDescription());

        requete.executeInsert();
        requete.close();
    }

    @Override
    public void delete(Todo tuple) {

    }

    @Override
    public void update(Todo tuple) {

    }
}
