package com.example;

public class Images {
    private String name;
    private String url;
    private String date;

    public Images(String name, String url, String date){
        this.name = name;
        this.url = url;
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public String getDate(){
        return date;
    }
}
