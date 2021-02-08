// ==========================================
//  Title:  ExchangeRate
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

public class ExchangeRate {
    private String code;
    private double value;

    String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double value){
        this.value = value;
    }
}
