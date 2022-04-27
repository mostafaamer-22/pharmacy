package com.example.pharmacy.Models;

public class LoginModel {

    private int employeeSSN;
    private String password;

    public void setEmployeeSSN(int employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmployeeSSN() {
        return employeeSSN;
    }

    public String getPassword() {
        return password;
    }
}
