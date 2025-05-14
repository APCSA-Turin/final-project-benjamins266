package com.example;
import org.json.JSONObject;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String url = "https://images-api.nasa.gov/search?q=apollo%2011&description=moon%20landing&media_type=image";
    String output =  API.getData(url);
    API.saveData(output);
    JSONObject json = new JSONObject(output);
    System.out.println(json);
       
    }
}
