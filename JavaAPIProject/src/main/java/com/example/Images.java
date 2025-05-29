package com.example;

public class Images {
    private String name;
    private String url;
    private String date;

    //creates an Image object with a name, url and date
    public Images(String name, String url, String date){
        this.name = name;
        this.url = url;
        this.date = date;
    }

    //returns the name of the image
    public String getName(){
        return name;
    }

    //returns the url of the image
    public String getUrl(){
        return url;
    }

    //returns the date of the image
    public String getDate(){
        return date;
    }
}
