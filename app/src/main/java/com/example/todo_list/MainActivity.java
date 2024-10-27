package com.example.todo_list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtDescription, txtTime, txtId;
    Button btnAdd, btnEdit, btnDelete, btnList;
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
        //----btn add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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