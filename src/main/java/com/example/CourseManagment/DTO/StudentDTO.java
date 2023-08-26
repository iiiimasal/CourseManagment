package com.example.CourseManagment.DTO;



public class StudentDTO {

    private long nationalNum;
    private String firstname;
    private String lastname;
    private String address;

    // Getters and Setters

    public long getNationalNum() {
        return nationalNum;
    }

    public void setNationalNum(long nationalNum) {
        this.nationalNum = nationalNum;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
