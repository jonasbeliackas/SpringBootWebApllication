package com.example.web.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Currency {

    @Id @GeneratedValue
    private long id;
    private String currencyName;
    private double currencyValue;



    public Currency() {

    }

    public Currency( String currencyName, double currencyValue) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(double currencyValue) {
        this.currencyValue = currencyValue;
    }

    @Override
    public String toString() {
        return  "1 EUR = "+currencyValue+" "+currencyName ;
    }
}
