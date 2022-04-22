package com.example.pharmacy.Sales;

public class SalesModel {

    private int cureCode;
    private String cureName;
    private int amount;
    private int tapsNumber;
    private int retailPrice;
    private int totalPrice;
    private int billPrice;

    public SalesModel(int cureCode, String cureName, int amount, int tapsNumber, int retailPrice, int totalPrice) {
        this.cureCode = cureCode;
        this.cureName = cureName;
        this.amount = amount;
        this.tapsNumber = tapsNumber;
        this.retailPrice = retailPrice;
        this.totalPrice = totalPrice;
    }

    public SalesModel()
    {

    }

    static public SalesModel makeObject(int cureCode , String cureName , int amount , int tapsNumber , int retailPrice , int totalPrice)
    {
        return new SalesModel(cureCode , cureName , amount , tapsNumber , retailPrice , totalPrice);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setBillPrice(int billPrice) {
        this.billPrice = billPrice;
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

    public int getAmount() {
        return amount;
    }

    public int getBillPrice() {
        return billPrice;
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
}
