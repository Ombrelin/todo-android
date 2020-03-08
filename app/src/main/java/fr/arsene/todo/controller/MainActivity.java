package fr.arsene.todo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.Todo;
import fr.arsene.todo.services.HttpClient;
import fr.arsene.todo.services.TodoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainActivity activity = this;

    private ListView listView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private List<Todo> todos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load view from XML view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load references to controles from resource file
        this.toolbar = findViewById(R.id.toolbar);
        this.fab = findViewById(R.id.fab);
        this.listView = findViewById(R.id.todolist);

        // Set toolbar as activities' toolbar
        setSupportActionBar(toolbar);

        // Get Data
        // TODO : fetch data

        fetchTodos();
    }

    private void fetchTodos() {
        TodoApi api = HttpClient.getInstance().getClient().create(TodoApi.class);
        Call<List<Todo>> apiCall = api.getUsersTodos(HttpClient.getInstance().getBearerString());
        apiCall.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                loadData(response.body());
                activity.setTodos(response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(),"Fetch todos failed : "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadData(List<Todo> todos) {
        TodoAdapter adapter = new TodoAdapter(this,todos);
        this.listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTodos();
    }

    public void handleClickAddButton(View v){
        Intent i = new Intent(getApplicationContext(), AddTodo.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
