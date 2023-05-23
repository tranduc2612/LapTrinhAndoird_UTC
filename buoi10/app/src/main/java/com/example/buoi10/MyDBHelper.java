package com.example.buoi10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydb.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "SinhVien";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String YEAROB = "yearob";
    private SQLiteDatabase myDB;

    public MyDBHelper(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    static String getID(){
        return ID;
    }

    public static String getNAME(){
        return NAME;
    }

    public static String getYEAROB(){
        return YEAROB;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY autoincrement," + NAME + " TEXT NOT NULL, " + YEAROB + " INTEGER NOT NULL" + ")";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDB(){
        myDB = getWritableDatabase();
    }
    public void closeDB(){
        if(myDB!=null && myDB.isOpen()){
            myDB.close();
        }
    }

    public long Insert(String name,int yearob){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(YEAROB,yearob);
        return db.insert(TABLE_NAME,null,values);
    }

    public long Update(int id,String name,int yearob){
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(NAME,name);
        values.put(YEAROB,yearob);
        String dk = ID + " = " + id;
        return myDB.update(TABLE_NAME,values,dk,null);
    }
    public long Delete(int id){
        String dk = ID + " = " + id;
        return myDB.delete(TABLE_NAME,dk,null);
    }

    public Cursor findById(int id){
        String query = "Select * from " + TABLE_NAME + " where " + ID + " = " + id;
        return myDB.rawQuery(query,null);
    }

    public Cursor DisplayAll(){
        String query = "Select * from " + TABLE_NAME;
        return myDB.rawQuery(query,null);
    }
}
