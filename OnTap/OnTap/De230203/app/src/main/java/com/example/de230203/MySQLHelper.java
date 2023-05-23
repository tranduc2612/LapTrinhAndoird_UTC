package com.example.de230203;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "giaodich.db";
    private static final String TABLE_NAME = "GiaoDich";
    private static final String COLUMN_ID = "MaGiaoDich";
    private static final String COLUMN_NOIDUNG = "NoiDung";
    private static final String COLUMN_NGAYTHANG = "NgayThang";
    private static final String COLUMN_LOAIGIAODICH = "LoaiGiaoDich";
    private static final String COLUMN_TENNGUOI = "TenNguoi";
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
                COLUMN_NGAYTHANG + " TEXT," +
                COLUMN_LOAIGIAODICH + " BOOLEAN," +
                COLUMN_TENNGUOI + " TEXT," +
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
        List<GiaoDich> lstGiaoDich = GetAll();
        if (lstGiaoDich.size() != 0)
        {
            return;
        }
        add(new GiaoDich(0, "Thuê nhà", "20/01/2023", false, "Liêm", 20000000));
        add(new GiaoDich(0, "Lương", "20/02/2023", true, "Duy", 10000000));
        add(new GiaoDich(0, "Lương", "20/03/2023", true, "Hiếu", 50000000));
    }

    public void add(GiaoDich giaoDich)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOIDUNG, giaoDich.getNoiDung());
        values.put(COLUMN_NGAYTHANG, giaoDich.getNgayThang());
        values.put(COLUMN_LOAIGIAODICH, giaoDich.isLoaiGiaoDich());
        values.put(COLUMN_TENNGUOI, giaoDich.getTenNguoi());
        values.put(COLUMN_SOTIEN, giaoDich.getSoTien());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id);
    }

    @SuppressLint("Range")
    public List<GiaoDich> GetAll()
    {
        List<GiaoDich> lstGiaoDich  = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String noiDung = cursor.getString(cursor.getColumnIndex(COLUMN_NOIDUNG));
                String ngayThang = cursor.getString(cursor.getColumnIndex(COLUMN_NGAYTHANG));
                boolean loaiGiaoDich = cursor.getInt(cursor.getColumnIndex(COLUMN_LOAIGIAODICH)) == 1;
                String tenNguoi = cursor.getString(cursor.getColumnIndex(COLUMN_TENNGUOI));
                int soTien = cursor.getInt(cursor.getColumnIndex(COLUMN_SOTIEN));

                GiaoDich giaoDich = new GiaoDich(id, noiDung, ngayThang, loaiGiaoDich, tenNguoi, soTien);

                lstGiaoDich.add(giaoDich);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lstGiaoDich;
    }
}
