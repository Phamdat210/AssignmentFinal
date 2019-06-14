package com.example.hp.assignmentfinal.model;

public class SinhVien {
//    String lopID;
    String nameStudent;
    String date;

    public SinhVien(){

    }

    public SinhVien(String nameStudent, String date) {
//        this.lopID = lopID;
        this.nameStudent = nameStudent;
        this.date = date;
    }

//    public String getLopID() {
//        return lopID;
//    }
//
//    public void setLopID(String lopID) {
//        this.lopID = lopID;
//    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String name) {
        this.nameStudent = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
