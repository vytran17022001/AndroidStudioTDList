package com.example.todo_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    // create table todo
    public static final String SQL_TODO = "CREATE TABLE todo ( " +
            "id integer PRIMARY KEY," +
            " taskName text," +
            " taskDescription text," +
            " taskTime text)";
    // ham tao CSDL
    public SQLiteHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1); // thuc thi tao CSDL
    }
    //tao bang data
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TODO); //lenh tao table todo
    }
    // xoa bang data cu , tao bang data moi
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
    }
}
