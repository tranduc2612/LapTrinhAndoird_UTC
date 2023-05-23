package com.example.baithi1;

public class ThuChi {
    int maGiaDich;
    String tenKhoan;
    String ngayThang;
    String soTien;
    boolean loaiThuChi;

    public ThuChi() {
    }

    public ThuChi(int maGiaDich, String tenKhoan, String ngayThang, String soTien, boolean loaiThuChi) {
        this.maGiaDich = maGiaDich;
        this.tenKhoan = tenKhoan;
        this.ngayThang = ngayThang;
        this.soTien = soTien;
        this.loaiThuChi = loaiThuChi;
    }

    public int getMaGiaDich() {
        return maGiaDich;
    }

    public void setMaGiaDich(int maGiaDich) {
        this.maGiaDich = maGiaDich;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }


    public boolean isLoaiThuChi() {
        return loaiThuChi;
    }

    public void setLoaiThuChi(boolean loaiThuChi) {
        this.loaiThuChi = loaiThuChi;
    }
}
