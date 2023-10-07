package com.javascript.engine;

public class Student {

    private String firstName;
    private String lastName;
    private int totalMarks;
    private String rollNum;

    public Student() {
    }

    public Student(String firstName, String lastName, int totalMarks, String rollNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalMarks = totalMarks;
        this.rollNum = rollNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalMarks=" + totalMarks +
                ", rollNum='" + rollNum + '\'' +
                '}';
    }
}
