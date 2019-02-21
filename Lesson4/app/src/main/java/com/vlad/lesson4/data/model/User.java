
package com.vlad.lesson4.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User{

    @SerializedName("birthDay")
    @Expose
    private Integer birthDay;
    @SerializedName("birthMonth")
    @Expose
    private String birthMonth;
    @SerializedName("birthYears")
    @Expose
    private Integer birthYears;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fieldActivity")
    @Expose
    private String fieldActivity;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("isPushNotifications")
    @Expose
    private Boolean isPushNotifications;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("password")
    @Expose
    private String password;

    public Integer getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public Integer getBirthYears() {
        return birthYears;
    }

    public void setBirthYears(Integer birthYears) {
        this.birthYears = birthYears;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFieldActivity() {
        return fieldActivity;
    }

    public void setFieldActivity(String fieldActivity) {
        this.fieldActivity = fieldActivity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsPushNotifications() {
        return isPushNotifications;
    }

    public void setIsPushNotifications(Boolean isPushNotifications) {
        this.isPushNotifications = isPushNotifications;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
