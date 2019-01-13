package com.vlad.lesson4.data.model;

public class ItemForChooseCategoryHelp {
    private int imageCategory;
    private String nameCategory;

    public ItemForChooseCategoryHelp(int imageCategory,String nameCategory) {
        this.imageCategory = imageCategory;
        this.nameCategory = nameCategory;
    }

    public int getImageCategory() {
        return imageCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setImageCategory(int imageCategory) {
        this.imageCategory = imageCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
