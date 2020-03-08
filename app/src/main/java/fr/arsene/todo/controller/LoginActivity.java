package fr.arsene.todo.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.User;
import fr.arsene.todo.services.HttpClient;
import fr.arsene.todo.services.TodoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Activity activity = this;

    private EditText login;
    private EditText password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
    }

    public void handleClickLoginButton(View v){
        User u = new User(login.getText().toString(), password.getText().toString());
        TodoApi api = HttpClient.getInstance().getClient().create(TodoApi.class);
        Call<Void> apiCall = api.login(u);
        apiCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String bearerString = response.headers().get("Authorization");
                if(bearerString == null || bearerString.isEmpty()){
                    Toast.makeText(activity.getApplicationContext(),"Wrong credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    HttpClient.getInstance().setBearerString(bearerString);
                    Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
                    activity.startActivity(i);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
