package fr.arsene.todo.controller;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.DAO;
import fr.arsene.todo.modele.Todo;
import fr.arsene.todo.modele.TodoDAO;

public class AddTodo extends AppCompatActivity {

    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fetch controls
        this.title = findViewById(R.id.editTextTitle);
        this.description = findViewById(R.id.editTextDescription);
    }

    public void handleClickAddButton(View v){
        Todo todo = new Todo(this.title.getText().toString(), this.description.getText().toString());

        DAO<Todo> todoDAO = new TodoDAO(getApplicationContext());
        todoDAO.insert(todo);
        finish();
    }

}
