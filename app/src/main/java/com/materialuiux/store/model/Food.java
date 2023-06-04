package com.materialuiux.store.model;

public class Food {
    private int urlImage;
    private String Title;
    private String Desc;
    private int Price;

    public Food(int urlImage, String title, String desc, int price) {
        this.urlImage = urlImage;
        Title = title;
        Desc = desc;
        Price = price;
    }

    public int getUrlImage() {
        return urlImage;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return Desc;
    }

    public int getPrice() {
        return Price;
    }
}


