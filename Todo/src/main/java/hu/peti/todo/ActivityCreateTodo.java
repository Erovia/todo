package hu.peti.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ActivityCreateTodo extends Activity {

    private static final int DATE_DUE_DIALOG_ID = 1;

    private Calendar calSelectedDate = Calendar.getInstance();

    private EditText editTodoTitle;
    private Spinner spnrTodoPriority;
    private TextView txtDueDate;
    private EditText editTodoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createtodo);

        // Aktuális dátum beállítása
        calSelectedDate.setTime(new Date(System.currentTimeMillis()));

        // UI elem referenciák elkérése
        editTodoTitle = (EditText) this.findViewById(R.id.todoTitle);
        spnrTodoPriority = (Spinner) this.findViewById(R.id.todoPriority);

        String[] priorities = new String[3];
            priorities[0] = "Low";
            priorities[1] = "Medium";
            priorities[2] = "High";

        spnrTodoPriority.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_item,priorities));
        txtDueDate = (TextView) this.findViewById(R.id.todoDueDate);
        refreshDateText();
        txtDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DUE_DIALOG_ID);
            }
        });
        editTodoDescription = (EditText) this.findViewById(R.id.todoDescription);

        // Gomb eseménykezelők kezdeti beállítása
        Button btnOk = (Button)findViewById(R.id.btnCreateTodo);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btnCancel = (Button)findViewById(R.id.btnCancelCreateTodo);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void refreshDateText(){
        StringBuilder dateString = new StringBuilder();
        dateString.append(calSelectedDate.get(Calendar.YEAR));
        dateString.append(". ");
        dateString.append(calSelectedDate.get(Calendar.MONTH)+1);
        dateString.append(". ");
        dateString.append(calSelectedDate.get(Calendar.DAY_OF_MONTH));
        dateString.append(". ");
        txtDueDate.setText(dateString.toString());
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_DUE_DIALOG_ID:
            {
                return new DatePickerDialog(this, mDateListener,
                        calSelectedDate.get(Calendar.YEAR),
                        calSelectedDate.get(Calendar.MONTH),
                        calSelectedDate.get(Calendar.DAY_OF_MONTH));
            }
        }
        return null;
    }

    // mDateListener dátum kiválasztó esemény mentése attribútum formájában
    private DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            // Új dátum beállítása
            calSelectedDate.set(Calendar.YEAR,year);
            calSelectedDate.set(Calendar.MONTH,month);
            calSelectedDate.set(Calendar.DAY_OF_MONTH,day);
            refreshDateText();
            removeDialog(DATE_DUE_DIALOG_ID);
        }
    };
    
}
