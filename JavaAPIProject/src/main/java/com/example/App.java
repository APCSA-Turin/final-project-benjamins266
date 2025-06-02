package com.example;

import java.util.Arrays;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
public class App
{
    public static void main( String[] args ) throws Exception
    {
        //creates an array of pre determined words
        ArrayList<String> words = new ArrayList<String>(Arrays.asList("Jupiter", "Earth", "Moon", "Uranus", "Sun", "Andromeda Galaxy", "Mercury", "Venus", "Pluto", "Neptune", "Saturn", "Milky Way Galaxy"));
        //creates an empty arrayList that will hold the users search
        ArrayList<String> singular = new ArrayList<>();
        //setting up the scanner and frame for the image
        Scanner scan = new Scanner(System.in);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);


        //creates a label (used to build the frame)
        JLabel label = new JLabel();
        frame.add(label);
        frame.setVisible(true);


        //prompts the user to enter a difficulty
        System.out.println("Enter a difficulty: ");
        System.out.println("Hard   or   Easy");
        String answer = scan.nextLine();


        //if the user entered easy, the easy program will run
        if(answer.toLowerCase().equals("easy")){
            boolean play = true;
            //the program will continue to run until play is false
            while(play){
                //creates an image object of a image from the api
                Images image1 = API.getWords(words);
                //creates a URL object using the url of the image, and adds the image to the interface
                URL url = new URL(image1.getUrl());
                ImageIcon image = new ImageIcon(url);
                label.setIcon(image);
                frame.pack();
                Game game = new Game(image1);
                //runs the game
                game.run();
                System.out.println("Would you like to continue playing?");
                String ans = scan.nextLine();
                //if the user enters "no" then the program ends
                if(ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n")){
                    System.out.println("Your Max Streak Was: " + Game.getMax());
                    play = false;
                }
            }
            //if the user enters hard
        } else if(answer.toLowerCase().equals("hard")){
            boolean play = true;
            //it prompts the user to enter a category
            System.out.println("Enter a category: (ie. Planets)");
            String categories = scan.nextLine();
            singular.add(categories);
            //will continue to run the program until play is false
            while(play) {
                Images image1 = API.getWords(singular);
                //will continually change the image until a valid image is selected
                while(image1.getName().length() == 0 || image1.getName() == null){
                    image1 = API.getWords(singular);
                }
                //creates a URL object using the url of the image, and adds the image to the interface
                URL url = new URL(image1.getUrl());
                ImageIcon image = new ImageIcon(url);
                label.setIcon(image);
                frame.pack();
                Game game1 = new Game(image1);
                //runs the game
                game1.run();
                System.out.println("Would you like to continue playing?");
                String ans = scan.nextLine();
                //if the user enters "no" then the program ends
                if(ans.toLowerCase().equals("no") || ans.toLowerCase().equals("n")){
                    System.out.println("Thanks for playing! Your Max Streak Was: " + Game.getMax());
                    play = false;
                }
            }
            //if neither hard or easy is entered, then the user is told to enter a valid difficulty
        } else {
            System.out.println("Please enter a difficulty");
        }
    }
}
