package com.project.cv.Model;

public enum Gender {
    male("Male"),
    female("Female"),
    other ("Other"),
    norequire("No gender require");

    private String gender;

    Gender(String status){this.gender=status;}

    public String getGender(){
        return this.gender;
    }
}
