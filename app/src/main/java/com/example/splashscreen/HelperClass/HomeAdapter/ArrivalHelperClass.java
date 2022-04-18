package com.example.splashscreen.HelperClass.HomeAdapter;

public class ArrivalHelperClass {

    int image;
    String title, description;

    public ArrivalHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
