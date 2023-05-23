package com.example.test01;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LeXuanQuynh_SQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;
    // Table name and columns
    private static final String TABLE_NAME = "Contact";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TEN = "Ten";
    private static final String COLUMN_SDT = "SDT";

    public LeXuanQuynh_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_TEN + " TEXT, " +
                COLUMN_SDT + " TEXT)";
        db.execSQL(createTable);
    }

    public void InsertData()
    {
        List<Contact_Quynh> lst = getAllContacts();
        if (lst.size() != 0)
        {
            return;
        }
        // Them 6 ban ghi
        addContact(new Contact_Quynh(1, "Tran Minh Hieu", "1"));
        addContact(new Contact_Quynh(2, "Tran Minh Duc", "1"));
        addContact(new Contact_Quynh(3, "Tran Minh Ngoc", "1"));
        addContact(new Contact_Quynh(4, "Le Xuan Quynh", "1"));
        addContact(new Contact_Quynh(5, "Tran Minh Sy", "1"));
        addContact(new Contact_Quynh(6, "Tran Minh Duy", "1"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // KHONG CAN QUAN TAM
    }

    public void addContact(Contact_Quynh contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, contact.getId());
        values.put(COLUMN_TEN, contact.getTen());
        values.put(COLUMN_SDT, contact.getSdt());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Contact_Quynh> getAllContacts() {
        List<Contact_Quynh> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String ten = cursor.getString(cursor.getColumnIndex(COLUMN_TEN));
                String sdt = cursor.getString(cursor.getColumnIndex(COLUMN_SDT));

                Contact_Quynh contact = new Contact_Quynh(id, ten, sdt);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return contactList;
    }

    public void deleteContact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,  COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateContact(int id, Contact_Quynh contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, contact.getId());
        values.put(COLUMN_TEN, contact.getTen());
        values.put(COLUMN_SDT, contact.getSdt());

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
