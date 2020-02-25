package fr.arsene.todo.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class Connexion extends SQLiteOpenHelper {

    private static final String DATABASE = "todo.db";
    private static final int DATABASE_VERSION = 2;

    public Connexion(@Nullable Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS todos (" +
                "id_todo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "title_todo VARCHAR(255) NOT NULL," +
                "discription_todo VARCHAR(255) NOT NULL"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
