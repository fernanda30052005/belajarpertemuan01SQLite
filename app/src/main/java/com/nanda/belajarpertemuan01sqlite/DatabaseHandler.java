package com.nanda.belajarpertemuan01sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favorit.db";
    public static final int DATABASE_VERSION = 1;



    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_favorit (nama TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tb_favorit";
        db.execSQL(sql);
        onCreate(db);
    }
    public boolean simpan (String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("INSERT INTO tb_favorit (nama) VALUES ('%S')",nama);
        db.execSQL(sql);
        return true;
    }

    public List<String> tampilSemua(){
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM tb_favorit";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        return list;
    }
}
