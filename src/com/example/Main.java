package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
        //Populate List of Movies from file
        File file = new File("movies.txt");
        scanner = new Scanner(file);
        ArrayList<Movie> movies = new ArrayList<>();

        while (scanner.hasNextLine()){
            String tempString = scanner.nextLine();
            Movie movie = new Movie(tempString);
            movies.add(movie);
        }


        if(movies.isEmpty()){
            System.out.println("Error loading movies. Shutting the app ... ");
            return;
        }

        double num = Math.random();
        int myRandInt = (int)(num* movies.size());

        Movie movie = movies.get(myRandInt);
        System.out.println("Guess the movie (enter quit to finish):");
        play(movie);

        System.out.println();


    }

    public static void play(Movie movie){
        scanner = new Scanner(System.in);

        while(true){
            System.out.print("com.example.Movie title: ");
            movie.printCodedName();

            if (movie.canPlay()){
                System.out.println("You have " +movie.getNumberOfGuesses()+ "/" + movie.getTOTAL_GUESSES() +
                        " guesses, enter a letter:");
                String s = scanner.nextLine();
                if (s.equalsIgnoreCase("quit")){
                    System.out.println("Game over.");
                    return;
                }

                movie.guessLetter(s);

                if (movie.endGame()){
                    System.out.println("Congrats! You win!!!");
                    return;
                }

            } else {
                System.out.println("Game over. You are out of guesses!");
                System.out.print("com.example.Movie was "+ movie.getLetters());
                return;
            }
        }

    }

}