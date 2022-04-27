package com.example.pharmacy.Models;

public class UserReviewModel {

    public int ssn;
    public String f_name;
    public String status;
    public String date;
    public String time;

    public UserReviewModel(int ssn, String name, String status, String date, String time)
    {
        this.ssn = ssn;
        this.f_name = name;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public int getSsn() {
        return ssn;
    }

    public String getF_name() {
        return f_name;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }
}

