package com.project.cv.Model;

public enum Experience {
    norequire("No experience required"),
    goingtowork("Going to work"),
    lessoneyear ("Less than 1 year"),
    oneyear("1 year"),
    twoyear("2 year"),
    threeyear("3 year"),
    fouryear("4 year"),
    fiveyear("5 year"),
    overfiveyear("Over 5 year");

    private String experience;

    Experience(String status){this.experience=status;}

    public String getExperience(){
        return this.experience;
    }
}
