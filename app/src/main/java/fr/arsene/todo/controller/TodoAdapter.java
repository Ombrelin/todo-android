package fr.arsene.todo.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.arsene.todo.R;
import fr.arsene.todo.modele.Todo;

public class TodoAdapter extends ArrayAdapter<Todo> {

    private Context localContext;
    private List<Todo> todos;

    public TodoAdapter(@NonNull Context context, List<Todo> todos) {
        super(context, 0, todos);
        this.localContext = context;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Create the item's View
        if(convertView == null){
            convertView = LayoutInflater.from(this.localContext)
                    .inflate(R.layout.listview_item_layout, parent, false);
        }

        // Get the current item data
        final Todo todo = this.todos.get(position);

        // Fetching view controls
        final TextView title = convertView.findViewById(R.id.title);
        final TextView description = convertView.findViewById(R.id.description);

        // Filling the view with data
        title.setText(todo.getTitle());
        description.setText(todo.getDescription());

        return convertView;

    }
}
