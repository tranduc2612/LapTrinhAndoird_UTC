package com.example.studentcontact;

public class Contact {
    private int id;
    private String studentID;
    private String name;
    private float mathScore;
    private float physicsScore;
    private float chemistryScore;

    // Constructors, getters, setters

    public Contact() {
    }

    public Contact(String studentID, String name, float mathScore, float physicsScore, float chemistryScore) {
        this.studentID = studentID;
        this.name = name;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
        this.chemistryScore = chemistryScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public float getPhysicsScore() {
        return physicsScore;
    }

    public void setPhysicsScore(float physicsScore) {
        this.physicsScore = physicsScore;
    }

    public float getChemistryScore() {
        return chemistryScore;
    }

    public void setChemistryScore(float chemistryScore) {
        this.chemistryScore = chemistryScore;
    }
}
