package com.example.buoi7;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int MaSV;
    private String QueQuan;

    public SinhVien(int maSV, String queQuan) {
        MaSV = maSV;
        QueQuan = queQuan;
    }

    public int getMaSV() {
        return MaSV;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    @Override
    public String toString() {
        return this.MaSV + "\n" + this.QueQuan;
    }
}
