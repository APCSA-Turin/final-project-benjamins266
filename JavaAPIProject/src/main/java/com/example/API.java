package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
public class API{
 public static String getData(String endpoint) throws Exception {
        /*endpoint is a url (string) that you get from an API website*/
        URL url = new URL(endpoint);
        /*connect to the URL*/
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*creates a GET request to the API.. Asking the server to retrieve information for our program*/
        connection.setRequestMethod("GET");
        /* When you read data from the server, it wil be in bytes, the InputStreamReader will convert it to text. 
        The BufferedReader wraps the text in a buffer so we can read it line by line*/
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;//variable to store text, line by line
        /*A string builder is similar to a string object but faster for larger strings, 
        you can concatenate to it and build a larger string. Loop through the buffer 
        (read line by line). Add it to the stringbuilder */
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close(); //close the bufferreader
        connection.disconnect(); //disconnect from server 
        return content.toString(); //return the content as a string
    }

    public static Images getWords(){
        try { // learned about try/catch from https://www.w3schools.com/java/java_try_catch.asp
        String[] words = {"Jupiter", "Earth", "Moon", "Uranus", "Sun", "Andromeda", "Mercury", "Venus", "Pluto", "Neptune", "Saturn"};
        int rand = (int) (Math.random() * words.length);
        String word = words[rand];
        String search = "https://images-api.nasa.gov/search?q=" + java.net.URLEncoder.encode(word, "UTF-8") + "&media_type=image"; //learned the URLEncoder from https://stackoverflow.com/questions/213506/java-net-urlencoder-encodestring-is-deprecated-what-should-i-use-instead
        String output = API.getData(search);
        API.saveData(output);
        JSONObject json = new JSONObject(output);
        JSONArray items = json.getJSONObject("collection").getJSONArray("items");
        JSONObject image = items.getJSONObject((int) (Math.random() * 10));
        JSONArray links = image.getJSONArray("links");
        String url = links.getJSONObject(0).getString("href");
        return new Images(word, url);

    } catch (Exception e) {
        return null;
    }
}


    public static void saveData(String data) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}