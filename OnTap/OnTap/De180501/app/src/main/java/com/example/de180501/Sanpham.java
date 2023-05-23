package com.example.de180501;

public class Sanpham {
    int maSp;
    String noiDung;
    boolean giamGia;
    int soTien;

    public Sanpham() {
    }

    public Sanpham(int maSp, String noiDung, boolean giamGia, int soTien) {
        this.maSp = maSp;
        this.noiDung = noiDung;
        this.giamGia = giamGia;
        this.soTien = soTien;
    }

    public int getMaSp() {
        return maSp;
    }

    public void setMaSp(int maSp) {
        this.maSp = maSp;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isGiamGia() {
        return giamGia;
    }

    public void setGiamGia(boolean giamGia) {
        this.giamGia = giamGia;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}
