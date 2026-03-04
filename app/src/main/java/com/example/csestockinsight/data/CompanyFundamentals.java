package com.example.csestockinsight.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// refer: GitHub/Gemini
@Entity(tableName = "company_fundamentals")
public class CompanyFundamentals {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "ticker")
    public String ticker;

    @ColumnInfo(name = "sector")
    public String sector;

    // Income for the last five years
    @ColumnInfo(name = "income_year1")
    public double incomeYear1;
    @ColumnInfo(name = "income_year2")
    public double incomeYear2;
    @ColumnInfo(name = "income_year3")
    public double incomeYear3;
    @ColumnInfo(name = "income_year4")
    public double incomeYear4;
    @ColumnInfo(name = "income_year5")
    public double incomeYear5;

    // Debt-to-equity ratio for last five years
    @ColumnInfo(name = "de_ratio_year1")
    public double deRatioYear1;
    @ColumnInfo(name = "de_ratio_year2")
    public double deRatioYear2;
    @ColumnInfo(name = "de_ratio_year3")
    public double deRatioYear3;
    @ColumnInfo(name = "de_ratio_year4")
    public double deRatioYear4;
    @ColumnInfo(name = "de_ratio_year5")
    public double deRatioYear5;

    // Dividend per share for last five years
    @ColumnInfo(name = "dividend_year1")
    public double dividendYear1;
    @ColumnInfo(name = "dividend_year2")
    public double dividendYear2;
    @ColumnInfo(name = "dividend_year3")
    public double dividendYear3;
    @ColumnInfo(name = "dividend_year4")
    public double dividendYear4;
    @ColumnInfo(name = "dividend_year5")
    public double dividendYear5;

    // Price–earnings ratio
    @ColumnInfo(name = "pe_ratio")
    public double peRatio;

    // Promoter holding percentage
    @ColumnInfo(name = "promoter_holding")
    public double promoterHolding;

    // Earnings per share for last five years
    @ColumnInfo(name = "eps_year1")
    public double epsYear1;
    @ColumnInfo(name = "eps_year2")
    public double epsYear2;
    @ColumnInfo(name = "eps_year3")
    public double epsYear3;
    @ColumnInfo(name = "eps_year4")
    public double epsYear4;
    @ColumnInfo(name = "eps_year5")
    public double epsYear5;

    // Monthly price history for the last 12 months (technical analysis)
    @ColumnInfo(name = "price_month1")
    public double priceMonth1;
    @ColumnInfo(name = "price_month2")
    public double priceMonth2;
    @ColumnInfo(name = "price_month3")
    public double priceMonth3;
    @ColumnInfo(name = "price_month4")
    public double priceMonth4;
    @ColumnInfo(name = "price_month5")
    public double priceMonth5;
    @ColumnInfo(name = "price_month6")
    public double priceMonth6;
    @ColumnInfo(name = "price_month7")
    public double priceMonth7;
    @ColumnInfo(name = "price_month8")
    public double priceMonth8;
    @ColumnInfo(name = "price_month9")
    public double priceMonth9;
    @ColumnInfo(name = "price_month10")
    public double priceMonth10;
    @ColumnInfo(name = "price_month11")
    public double priceMonth11;
    @ColumnInfo(name = "price_month12")
    public double priceMonth12;

    // Flag to mark favourite companies
    @ColumnInfo(name = "is_favourite")
    public boolean isFavourite;
}