package com.example.test01;

import java.io.Serializable;

public class Contact_Quynh implements Serializable {
    private int id;
    private String ten;
    private String sdt;

    public Contact_Quynh() {
    }

    public Contact_Quynh(int id, String ten, String sdt) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
