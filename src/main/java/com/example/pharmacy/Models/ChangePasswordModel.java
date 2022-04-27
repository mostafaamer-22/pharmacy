package com.example.pharmacy.Models;

import java.security.PublicKey;

public class ChangePasswordModel {

    public int employeeSSN;

    public String password;

    public String newPassword;



    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeSSN(int employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public int getEmployeeSSN() {
        return employeeSSN;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
