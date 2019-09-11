package com.company.currencyTask;

import java.util.List;

public class Result {

    private List<CurrencyInfo> currencyInfoList;

    private String exchangeRateDifference;

    public Result() {
    }

    public Result(List<CurrencyInfo> currencyInfoList, String exchangeRateDiffefence) {
        this.currencyInfoList = currencyInfoList;
        this.exchangeRateDifference = exchangeRateDiffefence;
    }

    public List<CurrencyInfo> getCurrencyInfoList() {
        return currencyInfoList;
    }

    public void setCurrencyInfoList(List<CurrencyInfo> currencyInfoList) {
        this.currencyInfoList = currencyInfoList;
    }

    public String getExchangeRateDiffefence() {
        return exchangeRateDifference;
    }

    public void setExchangeRateDifference(String exchangeRateDiffefence) {
        this.exchangeRateDifference = exchangeRateDiffefence;
    }
}
