package com.example.baithi1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLHelper extends SQLiteOpenHelper {
    private static final String DATBASE_NAME = "hihi001.db";
    private static final String TABLE_NAME = "ThuChi";
    private static final String COLUMN_ID = "MaThuChi";
    private static final String COLUMN_TENKHOAN = "TenKhoan";
    private static final String COLUMN_NGAYTHANG = "NgayThang";
    private static final String COLUMN_SOTIEN = "SoTien";
    private static final String COLUMN_LOAITHUCHI = "LoaiThuChi";

    public MySQLHelper(Context context){
        super(context,DATBASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TENKHOAN + " TEXT," +
                COLUMN_NGAYTHANG + " TEXT," +
                COLUMN_LOAITHUCHI + " BOOLEAN," +
                COLUMN_SOTIEN + " INTEGER" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreate the table
        onCreate(db);
    }

    public void insertData(){
        List<ThuChi> lst = getAll();
        if(lst.size() != 0){
            return;
        }
        add(new ThuChi(0, "Thuê nhà", "20/2/2022","20000",false));
        add(new ThuChi(0, "Làm thêm", "20/2/2022","200000",true));
        add(new ThuChi(0, "Lương", "20/3/2022","20000",false));
    }
    @SuppressLint("Range")
    public List<ThuChi> getAll(){
        List<ThuChi> lstThuChi = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String tenKhoan = cursor.getString(cursor.getColumnIndex(COLUMN_TENKHOAN));
                String ngayThang = cursor.getString(cursor.getColumnIndex(COLUMN_NGAYTHANG));
                boolean loaiThuChi = cursor.getInt(cursor.getColumnIndex(COLUMN_LOAITHUCHI)) == 1;
                String soTien = cursor.getString(cursor.getColumnIndex(COLUMN_SOTIEN));

                ThuChi thuChi = new ThuChi(id, tenKhoan, ngayThang, soTien,loaiThuChi);
                lstThuChi.add(thuChi);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return lstThuChi;
    }

    public void add(ThuChi thuChi){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TENKHOAN,thuChi.getTenKhoan());
        values.put(COLUMN_NGAYTHANG,thuChi.getNgayThang());
        values.put(COLUMN_LOAITHUCHI,thuChi.isLoaiThuChi());
        values.put(COLUMN_SOTIEN,thuChi.getSoTien());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }


}
