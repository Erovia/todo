package hu.peti.todo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adapter létrehozása és feltöltése példákkal
        ArrayList<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("title1", Todo.Priority.LOW,"2011.03.14.","description1"));
        todos.add(new Todo("title2", Todo.Priority.MEDIUM,"2012.12.24.","description2"));
        todos.add(new Todo("title3", Todo.Priority.HIGH,"2013.01.01.","description3"));
        TodoAdapter todoAdapter = new TodoAdapter(getApplicationContext(),todos);
        setListAdapter(todoAdapter);
        // Elemre kattintás figyelése
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Todo selectedTodo = (Todo)getListAdapter().getItem(position);
                Toast.makeText(MainActivity.this,selectedTodo.getDescription(),Toast.LENGTH_LONG).show();
            }
        });
    registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(getListView())){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(((Todo)getListAdapter().getItem(info.position)).getTitle());
            String[] menuItems = getResources().getStringArray(R.array.todomenu);
            for (int i=0; i<menuItems.length; i++){
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        if(menuItemIndex==0){
            ((TodoAdapter)getListAdapter()).deleteRow((Todo)getListAdapter().getItem(info.position));
            ((TodoAdapter)getListAdapter()).notifyDataSetChanged();
        }
        return true;
    }
}
