package fr.arsene.todo.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Reprente une table dans la base de donnee.
 * Permet d'effectuer des operations CRUD sur la table
 * @param <T> Le type stocke dans la table
 * @author Arsene Lapostolet
 * @version 1.0
 */
public abstract class DAO<T> {

    /**
     * Connexion à la base de donnee
     */
    public Connexion connexion;

    /**
     * Initilise le DAO
     * @param context Le contexte qui correspond à la base de donnee
     */
    public DAO(Context context){
        connexion = new Connexion(context);
    }

    /**
     * Retourne tous les objets stockes dans la table
     * @return Une liste qui contient tous les objets stockes dans la table
     */
    public abstract List<T> getAll();

    /**
     *
     * @param id
     * @return
     */
    public abstract T getFromId(int id);

    /**
     * Permet d'inserer un objet dans la table
     * @param tuple L'objet a inserer dans la table
     */
    public abstract void insert(T tuple);

    /**
     * Supprimer l'objet de la table à partir de son ID
     * @param tuple L'ojbet à supprimer de la table
     */
    public abstract void delete(T tuple);

    /**
     * Permet de mettre à jour un objet de la table
     * Met à jours tous les champs en fonction des champs de l'objet
     * @param tuple L'objet à mettre à jour
     */
    public abstract void update(T tuple);

    /**
     * Permet de recuperer la base de donnee
     * @return L'objet de base de donnee
     */
    protected SQLiteDatabase getDatabase() {
        return connexion.getWritableDatabase();
    }

    public void close(){
        connexion.close();
    }
}
