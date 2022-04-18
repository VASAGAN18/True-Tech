package com.example.splashscreen.HelperClass.HomeAdapter;

public class pop_mob_HelperClass {

    int image;
    String title, description;

    public pop_mob_HelperClass(int image, String title, String description) {
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
