/**
 * *
o	 * Copyright (c) 2019 KhydmaShore Solutions Inc.
o	 *
o	 * All rights reserved.
o	 *
o	 *****************************************************************************
 */

/**
 * @author ZEYNAB
 *
 */

package com.appavoc.model.enums;

public enum Currency {
    XOF, EUR;

    @Override
    public String toString() {
        return super.toString();
    }

    public String lowerCaseCode() {
        return super.toString().toLowerCase();
    }

    public static Currency fromLowerCase(String value){
        for (Currency currencyValue: values()){
            String stringVal = currencyValue.lowerCaseCode();
            if(stringVal.equalsIgnoreCase(value)){
                return currencyValue;
            }
        }
        throw new IllegalArgumentException("Not a valid Currency" + value);
    }
}
