package com.example.tranminhduc_201210096;

public class BaiHat {
    int Id;
    String tenBai;
    String CaSy;
    int like;
    int share;

    public BaiHat() {
    }

    public BaiHat(int id, String tenBai, String caSy, int like, int share) {
        Id = id;
        this.tenBai = tenBai;
        CaSy = caSy;
        this.like = like;
        this.share = share;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public String getCaSy() {
        return CaSy;
    }

    public void setCaSy(String caSy) {
        CaSy = caSy;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
