package com.example.todo_list;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtDescription, txtTime, txtId;
    Button btnAdd, btnEdit, btnDelete, btnList, btnTime;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ToDoDAO todoDAO;
    List<String> list = new ArrayList<>(); //tao list rong
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //anh xa
        txtName = findViewById(R.id.todoTxt1);
        txtDescription = findViewById(R.id.todoTxt2);
        txtTime = findViewById(R.id.todoTxt3);
        txtId = findViewById(R.id.todoTxt4);
        btnAdd = findViewById(R.id.todoBtnAdd);
        btnEdit = findViewById(R.id.todoBtnEdit);
        btnDelete = findViewById(R.id.todoBtnDelete);
        btnList = findViewById(R.id.todoBtnList);
        btnTime = findViewById(R.id.todoBtnTime);
        listView = findViewById(R.id.todoListView);
        //khoi tao cac bien
        context = this;
        //hien thi data khi chay chuong trinh
        list.clear(); //xoa het ndung in list
        todoDAO = new ToDoDAO(context); // tao csdl va table data
        list = todoDAO.getAllToDoToString(); //lay all todo in bang todoList
        arrayAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1,list); //tao adapter
        listView.setAdapter(arrayAdapter); //day data vao listview
        //--- btn hien thi
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear(); //xoa het ndung in list
                todoDAO = new ToDoDAO(context); // tao csdl va table data
                list = todoDAO.getAllToDoToString();
                arrayAdapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_list_item_1,list);
                listView.setAdapter(arrayAdapter);
            }
        });

        // Thiết lập DatePickerDialog để chọn ngày
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Định dạng ngày thành "yyyy-MM-dd"
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(Calendar.YEAR, year);
                        selectedDate.set(Calendar.MONTH, month);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        txtTime.setText(dateFormat.format(selectedDate.getTime()));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //----btn add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtName.getText().toString().trim().isEmpty() ||
                        txtDescription.getText().toString().trim().isEmpty() ||
                        txtTime.getText().toString().trim().isEmpty()) {
                    txtName.setError("Please input a Name.");
                    txtDescription.setError("Please input a Description.");
                    txtTime.setError("Please select a due date.");
                    return; // dừng lại nếu có trường nào bị bỏ trống
                }

                ToDoList todo = new ToDoList(); //create oj chua data ngdung input
                //dua data vao oj
                todo.setTaskName(txtName.getText().toString());
                todo.setTaskDescription(txtDescription.getText().toString());
                todo.setTaskTime(txtTime.getText().toString());
                //goi ham insert
                int result = todoDAO.InsertToDo(todo);
                if (result == -1) //kt result
                {
                    Toast.makeText(context,"Insert faile",Toast.LENGTH_LONG).show();
                }
                if (result == 1)
                {
                    Toast.makeText(context,"Insert Success", Toast.LENGTH_LONG).show();
                }
            }
        });
        //---delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtId.getText().toString();
                if (id.isEmpty()) {
                    txtId.setError("Please input a ID.");
                    return;
                }
                int result = todoDAO.DeleteToDo(id);
                if (result == -1) //kt result
                {
                    Toast.makeText(context,"Delete faile",Toast.LENGTH_LONG).show();
                }
                if (result == 1)
                {
                    Toast.makeText(context,"Delete Success", Toast.LENGTH_LONG).show();
                }
            }
        });
         //---
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoList todo = new ToDoList(); //create oj chua data ngdung input
                //dua data vao oj
                todo.setId(txtId.getText().toString());
                todo.setTaskName(txtName.getText().toString());
                todo.setTaskDescription(txtDescription.getText().toString());
                todo.setTaskTime(txtTime.getText().toString());
                //goi ham insert
                int result = todoDAO.UpdateToDo(todo);
                if (result == -1) //kt result
                {
                    Toast.makeText(context,"Update faile",Toast.LENGTH_LONG).show();
                }
                if (result == 1)
                {
                    Toast.makeText(context,"Update Success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}