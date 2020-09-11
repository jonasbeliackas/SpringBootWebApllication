package com.example.web.domain;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Converter {
    private double currencyValue;
    private String valueInTextField;
    private double result;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getValueInTextField() {
        return valueInTextField;
    }

    public void setValueInTextField(String valueInTextField) {
        this.valueInTextField = valueInTextField;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Converter() {
    currencyValue  = result =0;
    valueInTextField = "0";
    }

    public String convertToEur()
    {
        double value;
        try{
            value = Double.parseDouble(valueInTextField);
        }catch (NumberFormatException nfe)
        {
            return "Error";
        }
        double tmp = 1/ currencyValue;
        result = value * tmp;
        NumberFormat formatter = new DecimalFormat("#0.000000");
        result= Double.parseDouble(formatter.format(result));
        return String.valueOf(result);
    }

}
