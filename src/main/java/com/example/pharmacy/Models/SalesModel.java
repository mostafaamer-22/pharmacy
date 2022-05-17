package com.example.pharmacy.Models;

import com.example.pharmacy.Exception.Exception;

import java.sql.SQLException;

public class SalesModel {

    public int cureCode;
    private String cureName;
    public int amount;
    public int tapsNumber;
    private int retailPrice;
    private int totalPrice;
    private int billCode;

    public SalesModel(int cureCode, String cureName, int amount, int tapsNumber, int retailPrice, int totalPrice , int billCode) {
        this.cureCode = cureCode;
        this.cureName = cureName;
        this.amount = amount;
        this.tapsNumber = tapsNumber;
        this.retailPrice = retailPrice;
        this.totalPrice = totalPrice;
        this.billCode = billCode;
    }

    public SalesModel()
    {

    }


    static public SalesModel makeObject(int cureCode , String cureName , int amount , int tapsNumber , int retailPrice , int totalPrice , int billCode)
    {
        return new SalesModel(cureCode , cureName , amount , tapsNumber , retailPrice , totalPrice , billCode);
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


    public void setTapsNumber(int tapsNumber) {
        this.tapsNumber = tapsNumber;
    }


    public int getAmount() {
        return amount;
    }


    public int getCureCode() {
        return cureCode;
    }


    public int getTapsNumber() {
        return tapsNumber;
    }


    public String getCureName() {
        return cureName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getBillCode() {
        return billCode;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setBillCode(int billPrice) {
        this.billCode = billPrice;
    }
}