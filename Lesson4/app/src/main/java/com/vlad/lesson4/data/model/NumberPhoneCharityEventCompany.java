
package com.vlad.lesson4.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberPhoneCharityEventCompany {

    @SerializedName("number")
    @Expose
    private String numberCompany;

    public String getNumberCompany() {
        return numberCompany;
    }

    public void setNumberCompany(String numberCompany) {
        this.numberCompany = numberCompany;
    }
}