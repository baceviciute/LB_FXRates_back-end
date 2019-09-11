package com.company.currencyTask;

public class CurrencyInfo {

    private String date;
    private String currency;
    private Double rate;

    public CurrencyInfo() {
    }

    public CurrencyInfo(String date, String currency, Double rate) {
        this.date = date;
        this.currency = currency;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "com.company.currencyTask.CurrencyInfo{" +
                "date='" + date + '\'' +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
