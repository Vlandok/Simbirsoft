
package com.vlad.lesson4.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageMain")
    @Expose
    private String imageMain;
    @SerializedName("imageExtra")
    @Expose
    private List<ImageExtraEvent> imageExtra = null;
    @SerializedName("timeStart")
    @Expose
    private String timeStart;
    @SerializedName("timeFinish")
    @Expose
    private String timeFinish;
    @SerializedName("idSearchEvent")
    @Expose
    private Integer idSearchEvent;
    @SerializedName("idCategoryHelp")
    @Expose
    private Integer idCategoryHelp;
    @SerializedName("charityCompany")
    @Expose
    private String charityCompany;
    @SerializedName("addressCompany")
    @Expose
    private String addressCompany;
    @SerializedName("numberPhone")
    @Expose
    private List<NumberPhoneCharityEventCompany> numberPhone = null;
    @SerializedName("emailCompany")
    @Expose
    private String emailCompany;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("urlSiteCompany")
    @Expose
    private String urlSiteCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public List<ImageExtraEvent> getImageExtra() {
        return imageExtra;
    }

    public void setImageExtra(List<ImageExtraEvent> imageExtra) {
        this.imageExtra = imageExtra;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public Integer getIdSearchEvent() {
        return idSearchEvent;
    }

    public void setIdSearchEvent(Integer idSearchEvent) {
        this.idSearchEvent = idSearchEvent;
    }

    public Integer getIdCategoryHelp() {
        return idCategoryHelp;
    }

    public void setIdCategoryHelp(Integer idCategoryHelp) {
        this.idCategoryHelp = idCategoryHelp;
    }

    public String getCharityCompany() {
        return charityCompany;
    }

    public void setCharityCompany(String charityCompany) {
        this.charityCompany = charityCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public List<NumberPhoneCharityEventCompany> getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(List<NumberPhoneCharityEventCompany> numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmailCompany() {
        return emailCompany;
    }

    public void setEmailCompany(String emailCompany) {
        this.emailCompany = emailCompany;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrlSiteCompany() {
        return urlSiteCompany;
    }

    public void setUrlSiteCompany(String urlSiteCompany) {
        this.urlSiteCompany = urlSiteCompany;
    }

}
