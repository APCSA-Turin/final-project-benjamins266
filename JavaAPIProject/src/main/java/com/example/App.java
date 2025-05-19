package com.example;
import java.util.Scanner;
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        boolean play = true;
        while(play){
        Game game = new Game(API.getWords());
        game.run();
        System.out.println("Would you like to continue playing?");
        String ans = scan.nextLine();
        if(ans.toLowerCase().equals("no")){
            play = false;
        }
        }

        

    }
}
