package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
public class API {
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


    public static Images getWords(ArrayList<String> names) {
        try {// learned about try/catch from https://www.w3schools.com/java/java_try_catch.asp

            //if the arrayList size is greater than 1
            if (names.size() > 1) {
                //creates a random integer value based on the size of the names arrayList
                int rand = (int) (Math.random() * names.size());
                //gets a random name from the arrayList
                String word = names.get(rand);
                //sends a request to the API using the random name
                String search = "https://images-api.nasa.gov/search?q=" + java.net.URLEncoder.encode(word, "UTF-8") + "&media_type=image"; //learned the URLEncoder from https://stackoverflow.com/questions/213506/java-net-urlencoder-encodestring-is-deprecated-what-should-i-use-instead
                //gets the data from the search
                String output = API.getData(search);
                API.saveData(output);
                //accesses the data and links arrays
                JSONObject json = new JSONObject(output);
                JSONArray items = json.getJSONObject("collection").getJSONArray("items");
                int rand2 = (int) (Math.random() * items.length());
                JSONObject image = items.getJSONObject(rand2);
                JSONArray links = image.getJSONArray("links");
                JSONArray data = image.getJSONArray("data");
                //gets the link and the date created of the image
                String url = links.getJSONObject(0).getString("href");
                String date = data.getJSONObject(0).getString("date_created");
                //returns an image object using the name, url and date created
                return new Images(word, url, date);
                //if the array size is equal to 1
            } else {
                //accesses the name of the 1 item
                String word = names.get(0);
                //sends a request to the API using the word
                String search = "https://images-api.nasa.gov/search?q=" + java.net.URLEncoder.encode(word, "UTF-8") + "&media_type=image"; //learned the URLEncoder from https://stackoverflow.com/questions/213506/java-net-urlencoder-encodestring-is-deprecated-what-should-i-use-instead
                String output = API.getData(search);
                //gets the data from the search
                API.saveData(output);
                JSONObject json = new JSONObject(output);
                //accesses the data and links arrays
                JSONArray items = json.getJSONObject("collection").getJSONArray("items");
                int rand2 = (int) (Math.random() * items.length());
                JSONObject image = items.getJSONObject(rand2);
                JSONArray links = image.getJSONArray("links");
                JSONArray data = image.getJSONArray("data");
                //gets a random image from the api
                int randDataIndex = (int) (Math.random() * data.length());
                JSONObject keywords = data.getJSONObject(randDataIndex);
                //accesses the keywords of the image
                JSONArray array = keywords.optJSONArray("keywords");
                String valid = "";
                //if the keywords array is not empty
                if (array != null) {
                    //it will iterate throughout the array
                    for (int i = 0; i < array.length(); i++) {
                        //if the keywords doesn't contain or equal any of the things listed below, it will set the keyword to valid
                        if (!array.getString(i).contains(" ") && array.getString(i).matches("^[A-Za-z0-9\\-]+$") && array.getString(i).length()>2 && !array.getString(i).equals("Washington") && !array.getString(i).contains("Planet")
                                && !array.getString(i).equals("space") && !array.getString(i).equals("star") && !array.getString(i).equals("planet") && !array.getString(i).contains("EWTS") && !array.getString(i).contains("nasa") && !array.getString(i).contains("NASA") && !array.getString(i).equals("Asteroid") && !array.getString(i).equals("asteroid") && !array.getString(i).contains("Stars")) {
                            valid = array.getString(i);
                            break;
                        }
                    }
                }
                //gets the link and the date created of the image
                String url = links.getJSONObject(0).getString( "href");
                String date = data.getJSONObject(0).getString("date_created");
                //returns an image object using the keyword, url and date created
                return new Images(valid, url, date);
            }

        //if an exception is thrown, null is returned
        } catch (Exception e) {
            System.out.println("Category not found");
            return null;
        }
    }

    //saves data into a text file
    public static void saveData(String data) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
