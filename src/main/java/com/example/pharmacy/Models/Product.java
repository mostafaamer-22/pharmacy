package com.example.pharmacy.Models;
public class Product {
   private int CureCode;
   private String CureName;
   private String CompanyName;
   private String DistributorName;
   private int DistributorNumber;
   private int Amount;
   private String ExpireDate;
   private int TapsNumber;
   private int RetailPrice;
   private int TotalPrice;



    public Product(int CureCode,
                   String CureName,
                   String CompanyName,
                   String DistributorName,
                   int DistributorNumber,
                   int Amount,
                   String ExpireDate,
                   int TapsNumber,
                   int RetailPrice,
                   int TotalPrice
    ) {

        this.setCureCode(CureCode);
        this.setCureName(CureName);
        this.setAmount(Amount);
        this.setTapsNumber(TapsNumber);
        this.setCompanyName(CompanyName);
        this.setDistributorName(DistributorName);
        this.setDistributorNumber(DistributorNumber);
        this.setExpireDate(ExpireDate);
        this.setRetailPrice(RetailPrice);
        this.setTotalPrice(TotalPrice);
    }


    public int getCureCode() {
        return CureCode;
    }

    public void setCureCode(int cureCode) {
        CureCode = cureCode;
    }

    public String getCureName() {
        return CureName;
    }

    public void setCureName(String cureName) {
        CureName = cureName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDistributorName() {
        return DistributorName;
    }

    public void setDistributorName(String distributorName) {
        DistributorName = distributorName;
    }

    public int getDistributorNumber() {
        return DistributorNumber;
    }

    public void setDistributorNumber(int distributorNumber) {
        DistributorNumber = distributorNumber;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public int getTapsNumber() {
        return TapsNumber;
    }

    public void setTapsNumber(int tapsNumber) {
        TapsNumber = tapsNumber;
    }

    public int getRetailPrice() {
        return RetailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        RetailPrice = retailPrice;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }



}
