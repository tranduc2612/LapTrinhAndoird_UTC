package com.example.de230203;

public class GiaoDich {
    int maGiaoDich;
    String noiDung;
    String ngayThang;
    boolean loaiGiaoDich;
    String tenNguoi;
    int soTien;

    public GiaoDich() {
    }

    public GiaoDich(int maGiaoDich, String noiDung, String ngayThang, boolean loaiGiaoDich, String tenNguoi, int soTien) {
        this.maGiaoDich = maGiaoDich;
        this.noiDung = noiDung;
        this.ngayThang = ngayThang;
        this.loaiGiaoDich = loaiGiaoDich;
        this.tenNguoi = tenNguoi;
        this.soTien = soTien;
    }

    public int getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(int maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public boolean isLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(boolean loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getTenNguoi() {
        return tenNguoi;
    }

    public void setTenNguoi(String tenNguoi) {
        this.tenNguoi = tenNguoi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}