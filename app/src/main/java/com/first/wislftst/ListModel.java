package com.first.wislftst;

public class ListModel {
    String Image;
    String Title;
    String description;

    public ListModel(String id, String author, String url) {
        this.Image=url;
        this.Title=author;
        this.description=id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
