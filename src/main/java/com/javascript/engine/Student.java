package com.javascript.engine;

public class Student {

    private String firstName;
    private String lastName;

    private int totalMarks;

    public Student() {
    }

    public Student(String firstName, String lastName, int totalMarks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalMarks = totalMarks;
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

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", totalMarks=" + totalMarks +
                '}';
    }
}
