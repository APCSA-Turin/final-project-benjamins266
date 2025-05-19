package com.example;
import java.util.Scanner;
import java.util.ArrayList;

public class Game{
    private Images image;
    private ArrayList<String> wordArray;
    private int rGuesses;
    private static int totalGuesses;
    private static int numGames;

    public Game(Images image){ 
        this.image = image;
        rGuesses = image.getName().length();
        wordArray = new ArrayList<>();
        for(int i = 0; i<image.getName().length(); i++){
            wordArray.add("_ ");
        }
        System.out.println(image.getUrl());
        System.out.println(image.getName());
        numGames++;
    }

    public void run(){
        boolean gameRun = true;
        int count = 0;
        while(gameRun){
        Scanner scan = new Scanner(System.in);
        System.out.print("Average Guesses Per Game: " + average());
        System.out.println("      Remaining Guesses: " + rGuesses);
        System.out.print("Total Guesses: " + totalGuesses);
        System.out.println("      Total Games: " + numGames);
        //testing
        for(int i  = 0; i<wordArray.size(); i++){
            System.out.print(wordArray.get(i));
        }
        System.out.println("\nEnter your Guess:");
        String guess = scan.nextLine();
        if(guess(guess)){
            System.out.println("Correct! The answer was: " + guess);
            // gameRun = false;
            break;
        } else {
            wordArray.set(count, image.getName().substring(count, count+1));
            count++;
        }
        if(rGuesses == 0){
            System.out.println("You lose! The answer was: " + image.getName());
            break;
        }
    }
    }

    public boolean guess(String guess){
        totalGuesses++;
        String word = image.getName().toLowerCase();
        rGuesses--;
        if(word.equals(guess.toLowerCase())){
            return true;
        }
        return false;
    }

    public static double average(){
        return Math.round(((double) totalGuesses/numGames) * 10.0) / 10.0;
    }
}