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


            if (names.size() > 1) {
                int rand = (int) (Math.random() * names.size());
                String word = names.get(rand);
                String search = "https://images-api.nasa.gov/search?q=" + java.net.URLEncoder.encode(word, "UTF-8") + "&media_type=image"; //learned the URLEncoder from https://stackoverflow.com/questions/213506/java-net-urlencoder-encodestring-is-deprecated-what-should-i-use-instead
                String output = API.getData(search);
                API.saveData(output);
                JSONObject json = new JSONObject(output);
                JSONArray items = json.getJSONObject("collection").getJSONArray("items");
                int rand2 = (int) (Math.random() * items.length());
                JSONObject image = items.getJSONObject(rand2);
                JSONArray links = image.getJSONArray("links");
                JSONArray data = image.getJSONArray("data");
                String url = links.getJSONObject(0).getString("href");
                String date = data.getJSONObject(0).getString("date_created");
                return new Images(word, url, date);
            } else {
                String word = names.get(0);
                String search = "https://images-api.nasa.gov/search?q=" + java.net.URLEncoder.encode(word, "UTF-8") + "&media_type=image"; //learned the URLEncoder from https://stackoverflow.com/questions/213506/java-net-urlencoder-encodestring-is-deprecated-what-should-i-use-instead
                String output = API.getData(search);
                API.saveData(output);
                JSONObject json = new JSONObject(output);
                JSONArray items = json.getJSONObject("collection").getJSONArray("items");
                int rand2 = (int) (Math.random() * items.length());
                JSONObject image = items.getJSONObject(rand2);
                JSONArray links = image.getJSONArray("links");
                JSONArray data = image.getJSONArray("data");
                int randDataIndex = (int) (Math.random() * data.length());
                JSONObject keywords = data.getJSONObject(randDataIndex);
                JSONArray array = keywords.optJSONArray("keywords");
                String valid = "";
                if (array != null) {
                    for (int i = 0; i < array.length(); i++) {
                        if (!array.getString(i).contains(" ") && array.getString(i).matches("^[A-Za-z0-9\\-]+$") && array.getString(i).length()>2 && !array.getString(i).equals("Washington") && !array.getString(i).contains("Planet")
                                && !array.getString(i).equals("space") && !array.getString(i).equals("star") && !array.getString(i).equals("planet") && !array.getString(i).contains("EWTS") && !array.getString(i).contains("nasa") && !array.getString(i).contains("NASA") && !array.getString(i).equals("Asteroid") && !array.getString(i).equals("asteroid") && !array.getString(i).contains("Stars")) {
                            valid = array.getString(i);
                            break;
                        }
                    }
                }
                String url = links.getJSONObject(0).getString( "href");
                String date = data.getJSONObject(0).getString("date_created");
                return new Images(valid, url, date);
            }


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
