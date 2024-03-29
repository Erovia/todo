package hu.peti.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by P on 2013.09.24..
 */
public class TodoAdapter extends BaseAdapter {
    private final List<Todo> todos;

    public TodoAdapter(final Context context,final ArrayList<Todo> atodos){
        todos=atodos;
    }

    public void addTodo(Todo aTodo){
        todos.add(aTodo);
    }

    public int getCount(){
        return todos.size();
    }

    public Object getItem(int position){
        return todos.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    // Sor megjelenítésének beállítása
    public View getView(int postion, View convertView, ViewGroup parent){
        final Todo todo = todos.get(postion);
        LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.todorow, null);
        ImageView imageViewIcon = (ImageView)itemView.findViewById(R.id.imageViewPriority);

        switch (todo.getPriority()){
            case LOW: imageViewIcon.setImageResource(R.drawable.low); break;
            case MEDIUM: imageViewIcon.setImageResource(R.drawable.medium); break;
            case HIGH: imageViewIcon.setImageResource(R.drawable.high); break;
        }

        TextView textViewTitle = (TextView)itemView.findViewById(R.id.textViewTitle);
        textViewTitle.setText(todo.getTitle());
        TextView textViewDueDate = (TextView)itemView.findViewById(R.id.textViewDueDate);
        textViewDueDate.setText(todo.getDueDate());

        return itemView;
    }

    public void deleteRow(Todo aTodo){
        if(todos.contains(aTodo)){
            todos.remove(aTodo);
        }
    }
}
