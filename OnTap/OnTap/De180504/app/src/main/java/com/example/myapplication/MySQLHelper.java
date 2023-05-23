package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sanpham.db";
    private static final String TABLE_NAME = "SanPham";
    private static final String COLUMN_ID = "MaSP";
    private static final String COLUMN_NOIDUNG = "NoiDung";
    private static final String COLUMN_GIAMGIA = "GiamGia";
    private static final String COLUMN_SOTIEN = "SoTien";

    public MySQLHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NOIDUNG + " TEXT," +
                COLUMN_GIAMGIA + " BOOLEAN," +
                COLUMN_SOTIEN + " TEXT" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreate the table
        onCreate(db);
    }

    public void insertData()
    {
        List<Sanpham> lstSanPham = GetAll();
        if (lstSanPham.size() != 0)
        {
            return;
        }
        add(new Sanpham(0, "Tủ lạnh LG 200l", true, 14500000));
        add(new Sanpham(1, "Điện thoại Samsung S7", false, 8300000));
        add(new Sanpham(2, "Tivi Samsung 14", true, 8900000));
        add(new Sanpham(3, "Điện thoại Iphone 6", true, 6700000));
        add(new Sanpham(4, "Lò vi sóng SunHouse", true, 1200000));
    }

    public void add(Sanpham sanPham)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOIDUNG, sanPham.getNoiDung());
        values.put(COLUMN_GIAMGIA, sanPham.isGiamGia());
        values.put(COLUMN_SOTIEN, sanPham.getSoTien());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Sanpham> GetAll()
    {
        List<Sanpham> lstSanPham  = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String noiDung = cursor.getString(cursor.getColumnIndex(COLUMN_NOIDUNG));
                boolean giamGia = cursor.getInt(cursor.getColumnIndex(COLUMN_GIAMGIA)) == 1;
                int soTien = cursor.getInt(cursor.getColumnIndex(COLUMN_SOTIEN));

                Sanpham giaoDich = new Sanpham(id, noiDung, giamGia, soTien);

                lstSanPham.add(giaoDich);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lstSanPham;
    }
}
