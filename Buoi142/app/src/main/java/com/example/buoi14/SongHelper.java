package com.example.buoi14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SongHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "List_Song";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Songs";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_TIME = "time";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_SINGER + " TEXT,"
            + COLUMN_TIME + " DOUBLE"
            + ")";

    public SongHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, song.getName());
        values.put(COLUMN_SINGER, song.getSinger());
        values.put(COLUMN_TIME, song.getTime());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Song> getAllSongs() {
        List<Song> songList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Song song = new Song();
                song.setId(cursor.getInt(0));
                song.setName(cursor.getString(1));
                song.setSinger(cursor.getString(2));
                song.setTime(cursor.getDouble(3));

                songList.add(song);
            } while (cursor.moveToNext());
        }

        return songList;
    }

    public int updateSong(int id, Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, song.getName());
        values.put(COLUMN_SINGER, song.getSinger());
        values.put(COLUMN_TIME, song.getTime());

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteSong(Song song) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(song.getId())});
        db.close();
    }
}