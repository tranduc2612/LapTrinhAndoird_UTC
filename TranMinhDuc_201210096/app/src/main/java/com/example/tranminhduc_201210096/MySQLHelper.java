package com.example.tranminhduc_201210096;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "BaiHat123.db";
    private static final String TABLE_NAME = "BaiHat";
    private static final String COLUMN_ID = "MaBaiHat";
    private static final String COLUMN_TENBAIHAT = "BaiHat";
    private static final String COLUMN_CASY = "CaSy";
    private static final String COLUMN_LIKE = "LIKEs";
    private static final String COLUMN_SHARE = "SHARE";

    public MySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TENBAIHAT + " TEXT," +
                COLUMN_CASY + " TEXT," +
                COLUMN_LIKE + " TEXT," +
                COLUMN_SHARE + " TEXT" +
                " )";
        db.execSQL(createTable);
    }

    public void insertData() {
        List<BaiHat> lstBaiHat = getAll();
        if (lstBaiHat.size() != 0) {
            return;
        }
        add(new BaiHat(0, "Hà Nội Mùa Thu", "Mỹ Tâm", 105,10));
        add(new BaiHat(0, "Phút Cuối", "Mỹ Linh", 117,10));
        add(new BaiHat(0, "Gót Hồng", "Quang Dũng", 157,21));

    }
    @SuppressLint("Range")
    public List<BaiHat> getAll() {
        List<BaiHat> lstGiaoDich = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String TenBaiHat = cursor.getString(cursor.getColumnIndex(COLUMN_TENBAIHAT));
                String CaSy = cursor.getString(cursor.getColumnIndex(COLUMN_CASY));
                int Like = cursor.getInt(cursor.getColumnIndex(COLUMN_LIKE));
                int Share = cursor.getInt(cursor.getColumnIndex(COLUMN_SHARE));

                BaiHat giaoDich = new BaiHat(id, TenBaiHat, CaSy, Like, Share);
                lstGiaoDich.add(giaoDich);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lstGiaoDich;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String xoa = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;
        db.execSQL(xoa);
    }

    public void add(BaiHat giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TENBAIHAT, giaoDich.getTenBai());
        values.put(COLUMN_CASY, giaoDich.getCaSy());
        values.put(COLUMN_LIKE, giaoDich.getLike());
        values.put(COLUMN_SHARE, giaoDich.getShare());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}






