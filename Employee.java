package com.example.dtl;

public class Employee {
    Integer id;
    String  name,DOB,Email,gender,phn;

    public Employee(Integer id, String name, String DOB, String email, String gender, String phn) {
        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.Email = email;
        this.gender = gender;
        this.phn = phn;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhn() {
        return phn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }
}
