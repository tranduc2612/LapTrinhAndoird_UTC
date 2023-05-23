package com.example.de02;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "thuchi.db";
    private static final String TABLE_NAME = "ThuChi";
    private static final String COLUMN_ID = "MaThuChi";
    private static final String COLUMN_NOIDUNG = "NoiDung";
    private static final String COLUMN_NGAYTHANG = "NgayThang";
    private static final String COLUMN_KHOANTHUCHI = "KhoanThuChi";
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
                COLUMN_KHOANTHUCHI + " BOOLEAN," +
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
        List<ThuChi> lstThuChi = GetAll();
        if (lstThuChi.size() != 0)
        {
            add(new ThuChi(4, true, 2500000, "20/01/2023", "Thuê nhà"));
            return;
        }
        add(new ThuChi(0, true, 12000000, "15/01/2023", "Lương"));
        add(new ThuChi(1, true, 4000000, "30/01/2023", "Làm thêm"));
        add(new ThuChi(2, false, 3000000, "28/01/2023", "Góp tiền ăn tối"));
        add(new ThuChi(3, false, 2000000, "20/01/2023", "Thuê nhà"));
    }

    public void add(ThuChi thuChi)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOIDUNG, thuChi.getNoiDung());
        values.put(COLUMN_NGAYTHANG, thuChi.getNgayThang());
        values.put(COLUMN_KHOANTHUCHI, thuChi.isKhoanThuChi());
        values.put(COLUMN_SOTIEN, thuChi.getSoTien());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<ThuChi> GetAll()
    {
        List<ThuChi> lstThuChi  = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String noiDung = cursor.getString(cursor.getColumnIndex(COLUMN_NOIDUNG));
                String ngayThang = cursor.getString(cursor.getColumnIndex(COLUMN_NGAYTHANG));
                boolean khoanThuChi = cursor.getInt(cursor.getColumnIndex(COLUMN_KHOANTHUCHI)) == 1;
                int soTien = cursor.getInt(cursor.getColumnIndex(COLUMN_SOTIEN));

                ThuChi thuChi = new ThuChi(id, khoanThuChi, soTien, ngayThang, noiDung);

                lstThuChi.add(thuChi);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return lstThuChi;
    }
}
