package com.example.studentcontact;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MATH_SCORE = "math_score";
    public static final String COLUMN_PHYSICS_SCORE = "physics_score";
    public static final String COLUMN_CHEMISTRY_SCORE = "chemistry_score";

    private static final String CREATE_TABLE_CONTACTS =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_STUDENT_ID + " TEXT NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_MATH_SCORE + " REAL NOT NULL, " +
                    COLUMN_PHYSICS_SCORE + " REAL NOT NULL, " +
                    COLUMN_CHEMISTRY_SCORE + " REAL NOT NULL)";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Methods to add, update, delete, and query contacts
    public void addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, contact.getStudentID());
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_MATH_SCORE, contact.getMathScore());
        values.put(COLUMN_PHYSICS_SCORE, contact.getPhysicsScore());
        values.put(COLUMN_CHEMISTRY_SCORE, contact.getChemistryScore());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void updateContact(int id, Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, contact.getStudentID());
        values.put(COLUMN_NAME, contact.getName());
        values.put(COLUMN_MATH_SCORE, contact.getMathScore());
        values.put(COLUMN_PHYSICS_SCORE, contact.getPhysicsScore());
        values.put(COLUMN_CHEMISTRY_SCORE, contact.getChemistryScore());
        db.update(TABLE_CONTACTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String ext = " ORDER BY " +  COLUMN_NAME + " ASC ";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + ext , null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                contact.setStudentID(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                contact.setMathScore(cursor.getFloat(cursor.getColumnIndex(COLUMN_MATH_SCORE)));
                contact.setPhysicsScore(cursor.getFloat(cursor.getColumnIndex(COLUMN_PHYSICS_SCORE)));
                contact.setChemistryScore(cursor.getFloat(cursor.getColumnIndex(COLUMN_CHEMISTRY_SCORE)));
                contacts.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }
}

