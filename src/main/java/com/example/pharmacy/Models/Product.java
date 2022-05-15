package com.example.pharmacy.Models;
public class Product {
   private int CureCode;
   private String CureName;
   private String CompanyName;
   private String DistributorName;
   private String DistributorNumber;
   private int Amount;
   private int TapsNumber;
   private String ExpireDate;
   private int RetailPrice;
   private int TotalPrice;
   private int totalTapsNumber;


    public Product(int CureCode,
                   String CureName,
                   String CompanyName,
                   String DistributorName,
                   String DistributorNumber,
                   int Amount,
                   int TapsNumber,
                   String ExpireDate,
                   int RetailPrice,
                   int TotalPrice,
                   int totalTapsNumber
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
        this.setTotalTapsNumber(totalTapsNumber);
    }


    public Product()
    {

    }

    public int getTotalTapsNumber() {
        return totalTapsNumber;
    }

    public void setTotalTapsNumber(int totalTapsNumber) {
        this.totalTapsNumber = totalTapsNumber;
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

    public String getDistributorNumber() {
        return DistributorNumber;
    }

    public void setDistributorNumber(String distributorNumber) {
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
