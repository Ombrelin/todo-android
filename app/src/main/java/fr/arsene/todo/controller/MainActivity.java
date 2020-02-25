package fr.arsene.todo.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.DAO;
import fr.arsene.todo.modele.Todo;
import fr.arsene.todo.modele.TodoDAO;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    private  FloatingActionButton fab;

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

        // Init test data
        DAO<Todo> todoDAO = new TodoDAO(getApplicationContext());
        List<Todo> todos = todoDAO.getAll();

        loadData(todos);
    }

    private void loadData(List<Todo> todos) {
        TodoAdapter adapter = new TodoAdapter(this,todos);
        this.listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DAO<Todo> todoDAO = new TodoDAO(getApplicationContext());
        this.loadData(todoDAO.getAll());
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
}
