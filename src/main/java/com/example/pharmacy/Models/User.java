package com.example.pharmacy.Models;

public class User  {

    private final int SSN;

    private final String FName;

    private final String LName;

    private   String Password;

    private final String DateOfBirth;

    private final String Address;

    private final String Contact;

    private final String Gender;

    private final String Position;

    private final String Salary;



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


    public String getFName() {
        return FName;
    }


    public String getLName() {
        return LName;
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


    public String getAddress() {
        return Address;
    }


    public String getContact() {
        return Contact;
    }


    public String getGender() {
        return Gender;
    }


    public String getPosition() {
        return Position;
    }


    public String getSalary() {
        return Salary;
    }

}
