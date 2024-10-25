package com.example.todo_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ToDoDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private Context context;

    public ToDoDAO(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context); //thuc thi tao db
        db = dbHelper.getWritableDatabase(); // cho phep ghi data vao db
    }
    //1 them data vao db
    public int InsertToDo(ToDoList todo)
    {
       ContentValues values = new ContentValues(); //tao oj chua data
        //dua data vao oj
        values.put("taskName", todo.getTaskName());
        values.put("taskDescription", todo.getTaskDescription());
        values.put("taskTime", todo.getTaskTime());
        //thuc thi insert
        long result = db.insert("todo", null, values);
        // kt result insert
        if (result == -1){
            return 0;
        }else {
            return 1;
        }
    }
    //2 hien thi data dang String
    public List<String>getAllToDoToString()
    {
        List<String> ls = new ArrayList<>(); //tao list chua data
        //tao con tro doc du lieu todo
        Cursor c = db.query("todo", null, null, null, null, null, null);
        c.moveToFirst(); //duyet con tro ve bang ghi first
        //doc
        while (c.isAfterLast() == false) //trong khi k phai dong cuoi cung > van doc
        {
            ToDoList todo = new ToDoList(); //tao oj chua data
            todo.setTaskName(c.getString(1)); //doc data trg TaskName va dua vao oj
            todo.setTaskDescription(c.getString(2));
            todo.setTaskTime(c.getString(3));
            //chuyen oj thanh String
            String chuoi = todo.getTaskName() + " - " + todo.getTaskDescription() + " - " + todo.getTaskTime();
            //dua String vao List
            ls.add(chuoi);
        }
        c.close(); // close con tro
        return ls; // tra ve list
    }
}

