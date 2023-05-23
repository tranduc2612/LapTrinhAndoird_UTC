package com.example.buoi14;

public class Song {
    private int Id;
    private String Name;
    private String Singer;
    private Double Time;

    public Song(int id, String name, String singer, Double time) {
        Id = id;
        Name = name;
        Singer = singer;
        Time = time;
    }

    public Song(String name, String singer, Double time) {
        Name = name;
        Singer = singer;
        Time = time;
    }

    public Song() {

    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public Double getTime() {
        return Time;
    }

    public void setTime(Double time) {
        Time = time;
    }
}
