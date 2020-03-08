package fr.arsene.todo.services;

import java.util.List;

import fr.arsene.todo.modele.Todo;
import fr.arsene.todo.modele.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TodoApi {

    @POST("/login")
    Call<Void> login(@Body User user);

    @GET("/todos")
    Call<List<Todo>> getUsersTodos(@Header("Authorization") String bearerString);

    @POST("/todos")
    Call<Todo> createTodo(@Header("Authorization") String bearerString, @Body Todo todo);
}
