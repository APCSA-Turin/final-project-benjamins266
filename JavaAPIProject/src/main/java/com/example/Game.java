package com.example;
import java.util.Scanner;
import java.util.ArrayList;


public class Game{
    private Images image;
    private ArrayList<String> wordArray;
    private int rGuesses;
    private static int totalGuesses;
    private static int numGames;
    private static int numCorrect;
    private static int max;

    public Game(Images image){
        this.image = image;
        rGuesses = image.getName().length();
        wordArray = new ArrayList<>();
        for(int i = 0; i<image.getName().length(); i++){
            wordArray.add("_ ");
        }
        System.out.println("\n" + image.getUrl());
        System.out.println("\nDate Picture Taken: " + image.getDate());
        //testing
        System.out.println("\n" + image.getName());
        numGames++;
    }

    public static int getMax(){
        return max;
    }

    public void run(){
        boolean gameRun = true;
        int count = 0;
        while(gameRun){
            Scanner scan = new Scanner(System.in);
            System.out.print("\nAverage Guesses Per Game: " + average());
            System.out.println("      Remaining Guesses: " + rGuesses);
            System.out.print("Streak: " + numCorrect);
            System.out.println("      Max Streak: " + max);
            if(image.getName().indexOf("Galaxy") !=-1){
                System.out.println("\nWhat Galaxy is This?");
            } else {
                System.out.println("\nWhat Space Structure is This?");
            }
            //testing
            for(int i  = 0; i<wordArray.size(); i++){
                System.out.print(wordArray.get(i));
            }
            System.out.println("\nEnter your Guess:");
            String guess = scan.nextLine();
            if(guess(guess)){
                numCorrect++;
                if(numCorrect>max){
                    max = numCorrect;
                }
                System.out.println("Correct! The answer was: " + guess);
                break;
            } else {
                wordArray.set(count, image.getName().substring(count, count+1));
                count++;
            }
            if(rGuesses == 0){
                System.out.println("You lose! The answer was: " + image.getName() + "\nYour Final Streak Was: " + numCorrect);
                numCorrect = 0;
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
        return Math.round(((double) totalGuesses/(numGames-1)) * 10.0) / 10.0;
    }
}