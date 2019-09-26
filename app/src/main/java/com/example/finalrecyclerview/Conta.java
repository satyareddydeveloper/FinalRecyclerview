package com.example.finalrecyclerview;

public class Conta {
    int id;
    String joiningdate;

    public Conta(String empid, String name, String email, String mobile,
                 String depatment, String role) {
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.depatment = depatment;
        this.role = role;
    }

    public Conta(int id, String joiningdate, String empid, String name,
                 String email, String mobile) {
        this.id = id;
        this.joiningdate = joiningdate;
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public Conta(String empid, String name, String email, String mobile,
                 String depatment, String role, String joiningdate) {
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.depatment = depatment;
        this.role = role;
        this.joiningdate=joiningdate;

    }

    public Conta(int id, String empid, String name, String email, String
            mobile, String depatment, String role ,String joiningdates) {
        this.id = id;
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.depatment = depatment;
        this.role = role;
        this.joiningdate=joiningdates;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public int getId() {
        return id;
    }

    public String getEmpid() {
        return empid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDepatment() {
        return depatment;
    }

    public String getRole() {
        return role;
    }

    String empid;
    String name;
    String email;
    String mobile;
    String depatment;
    String role;
}
