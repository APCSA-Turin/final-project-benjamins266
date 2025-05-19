package com.example;

public class Images {
    private String name;
    private String url;

    public Images(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }

    public String toString(){
        return "Title: " + name + " URL: " + url;
    }
}
