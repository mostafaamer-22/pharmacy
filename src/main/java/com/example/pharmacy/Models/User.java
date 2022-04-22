package com.example.pharmacy.Models;

public class User  {

    private int SSN;

    private   String FName;

    private   String LName;

    private   String Password;

    private   String DateOfBirth;

    private   String Address;

    private   String Contact;

    private   String Gender;

    private   String Position;

    private  String Salary;



    public User(int SSN, String FName, String LName, String password, String dateOfBirth, String address, String contact, String gender, String position,String Salary) {
        this.SSN = SSN;
        this.FName = FName;
        this.LName = LName;
        Password = password;
        DateOfBirth = dateOfBirth;
        Address = address;
        Contact = contact;
        Gender = gender;
        Position = position;
        this.Salary =Salary;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
