package com.example;
import java.util.Scanner;
import java.util.ArrayList;


public class Game{
    //creates instance variables
    private Images image;
    private ArrayList<String> wordArray;
    private int rGuesses;
    private static int totalGuesses;
    private static int numGames;
    private static int numCorrect;
    private static int max;

    //creates a game object
    public Game(Images image){
        this.image = image;
        //sets rGuesses to the length of the image name
        rGuesses = image.getName().length();
        wordArray = new ArrayList<>();
        //fills the wordArray with "_ "
        for(int i = 0; i<image.getName().length(); i++){
            wordArray.add("_ ");
        }
        //displays the date the picture was taken, and the name for testing
        System.out.println("\nDate Picture Taken: " + image.getDate());
        //testing
        System.out.println("\n" + image.getName());
        numGames++;
    }

    //returns the max streak
    public static int getMax(){
        return max;
    }

    //the programs that runs the game
    public void run(){
        boolean gameRun = true;
        int count = 0;
        //keeps running the program until gameRun is false
        while(gameRun){
            Scanner scan = new Scanner(System.in);
            //displays all of the statistics
            System.out.print("\nAverage Guesses Per Game: " + average());
            System.out.println("      Remaining Guesses: " + rGuesses);
            System.out.print("Streak: " + numCorrect);
            System.out.println("      Max Streak: " + max);
            //this checks the wording in order to create the question
            if(image.getName().indexOf("Galaxy") !=-1){
                System.out.println("\nWhat Galaxy is This?");
            } else {
                System.out.println("\nWhat Space Structure is This?");
            }
            //prints out wordArray
            for(int i  = 0; i<wordArray.size(); i++){
                System.out.print(wordArray.get(i));
            }
            //prompts the user to enter a guess and recieves the result
            System.out.println("\nEnter your Guess:");
            String guess = scan.nextLine();
            //if the guess of the user is correct
            if(guess(guess)){
                //increases the number of correct guesses by 1
                numCorrect++;
                //checks to see if the number of correct guesses on the current run is greater
                //than the max number of correct guesses
                if(numCorrect>max){
                    max = numCorrect;
                }
                System.out.println("Correct! The answer was: " + guess);
                break;
                //if the users guess is incorrect
            } else {
                //it will display 1 letter of the image's name
                wordArray.set(count, image.getName().substring(count, count+1));
                count++;
            }
            //if the user has no more guesses, it will display the max streak and end the game
            if(rGuesses == 0){
                System.out.println("You lose! The answer was: " + image.getName() + "\nYour Final Streak Was: " + numCorrect);
                numCorrect = 0;
                break;
            }

        }
    }

    //this is the guess method that returns a boolean based on if the guess matches the image name
    public boolean guess(String guess){
        totalGuesses++;
        String word = image.getName().toLowerCase();
        rGuesses--;
        //if the user's guess is the same as the image name, true is returned
        if(word.equals(guess.toLowerCase())){
            return true;
        }
        return false;
    }

    //calculates the rounded average of the number of total guesses based on the number of total games
    public static double average(){
        return Math.round(((double) totalGuesses/(numGames-1)) * 10.0) / 10.0;
    }
}