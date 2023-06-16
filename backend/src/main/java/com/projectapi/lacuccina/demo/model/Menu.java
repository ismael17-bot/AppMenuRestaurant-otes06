package com.projectapi.lacuccina.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Menu {

    @Id
    private String id;
    private String desc;
    private String title;
    private String url;
    private Float price;

    public Menu(String desc, String title, float price, String url) {
        this.desc = desc;
        this.title = title;
        this.price = price;
        this.url = url;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", desc=" + desc + ", price=" + price + ", url="+ url +"]";
    }
}
