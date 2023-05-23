package com.example.de02;

public class ThuChi {
    int maThuChi;
    boolean khoanThuChi;
    int soTien;
    String ngayThang;
    String noiDung;

    public ThuChi() {
    }

    public ThuChi(int maThuCHi, boolean khoanThuChi, int soTien, String ngayThang, String noiDung) {
        this.maThuChi = maThuCHi;
        this.khoanThuChi = khoanThuChi;
        this.soTien = soTien;
        this.ngayThang = ngayThang;
        this.noiDung = noiDung;
    }

    public int getMaGiaoDich() {
        return maThuChi;
    }

    public void setMaGiaoDich(int maThuCHi) {
        this.maThuChi = maThuCHi;
    }

    public boolean isKhoanThuChi() {
        return khoanThuChi;
    }

    public void setKhoanThuChi(boolean khoanThuChi) {
        this.khoanThuChi = khoanThuChi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
