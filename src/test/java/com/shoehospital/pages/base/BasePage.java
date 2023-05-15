package com.shoehospital.pages.base;

import com.shoehospital.pages.main.Header;
import lombok.Getter;

import java.text.DecimalFormat;

public class BasePage {

    @Getter
    final private Header header = new Header();

    public String rounding (Double str){
    DecimalFormat decimalFormat = new DecimalFormat( "#.##" );
    return decimalFormat.format(str).replace(",", ".");
    }

}
