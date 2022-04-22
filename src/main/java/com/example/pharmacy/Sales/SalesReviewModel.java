package com.example.pharmacy.Sales;

public class SalesReviewModel {

    private int cureCode;
    private String cureName;
    private int amount;
    private int tapsNumber;
    private int retailPrice;
    private int totalPrice;
    private int employeeSSN;
    private String date;

    public SalesReviewModel(int cureCode , String cureName , int amount , int tapsNumber ,  String date , int retailPrice , int totalPrice , int employeeSSN ) {
        this.cureCode = cureCode;
        this.cureName = cureName;
        this.amount = amount;
        this.tapsNumber = tapsNumber;
        this.retailPrice = retailPrice;
        this.totalPrice = totalPrice;
        this.employeeSSN = employeeSSN;
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public void setCureCode(int cureCode) {
        this.cureCode = cureCode;
    }

    public void setCureName(String cureName) {
        this.cureName = cureName;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setTapsNumber(int tapsNumber) {
        this.tapsNumber = tapsNumber;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmployeeSSN(int employeeSSN) {
        this.employeeSSN = employeeSSN;
    }

    public int getAmount() {
        return amount;
    }

    public int getCureCode() {
        return cureCode;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getTapsNumber() {
        return tapsNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getCureName() {
        return cureName;
    }

    public int getEmployeeSSN() {
        return employeeSSN;
    }

    public String getDate() {
        return date;
    }
}
