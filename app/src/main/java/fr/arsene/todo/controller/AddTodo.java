package fr.arsene.todo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.Todo;
import fr.arsene.todo.services.HttpClient;
import fr.arsene.todo.services.TodoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTodo extends AppCompatActivity {
    private Activity activity = this;
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
        final Todo todo = new Todo(this.title.getText().toString(), this.description.getText().toString());

        TodoApi api = HttpClient.getInstance().getClient().create(TodoApi.class);
        Call<Todo> apiCall = api.createTodo(HttpClient.getInstance().getBearerString(), todo);
        apiCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(),"Add Todo failed",Toast.LENGTH_SHORT).show();
            }
        });
        finish();
    }

}
