package com.example;

import java.util.ArrayList;

public class Movie {
    private final int TOTAL_GUESSES = 5;
    private int numberOfGuesses = 0;
    private final String[] letters;
    private ArrayList<String> codedLetters;
    private ArrayList<String> guessedLetters;

    public Movie(String name) {
        this.letters = name.split("");
        this.codedLetters = new ArrayList<>();
        for(String s:this.letters){
            this.codedLetters.add("_");
        }
        this.guessedLetters = new ArrayList<>();
    }

    private ArrayList<String> getCodedLetters() {
        return new ArrayList<>(this.codedLetters);
    }



    private boolean isLetterValid(String c){
        if (c==null||c.length()>1){
            System.out.println("Invalid entry!");
            return false;
        }
        return true;
    }

    private boolean isLetterNew(String c){
        if (this.guessedLetters.contains(c)){
            System.out.println("Repeated letter. Try another one!");
            return false;
        }
        return true;
    }

    private boolean addLetter(String c){
        if(!isLetterValid(c)||!isLetterNew(c)){
            return false;
        }
        return this.guessedLetters.add(c);
    }

    private boolean containsLetter (String c){
        for(String s:this.letters){
            if(c.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public boolean guessLetter(String c){
        if(addLetter(c)){
            if (containsLetter(c)){

                for (int i=0;i<this.letters.length;i++){
                    if (c.equalsIgnoreCase(this.letters[i])){
                        this.codedLetters.set(i,c);
                    }
                }

                return true;
            } else {
                System.out.println("Wrong letter!");
                this.numberOfGuesses++;
            }
        }

        return false;
    }

    public boolean canPlay(){
        return this.numberOfGuesses <this.TOTAL_GUESSES;
    }

    public int getTOTAL_GUESSES() {
        return TOTAL_GUESSES;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void printCodedName(){
        for(String c:this.getCodedLetters()){
            System.out.print(c);
        }
        System.out.println();
    }

    public boolean endGame(){
        for(int i = 0;i<this.letters.length;i++){
            if(!this.letters[i].equalsIgnoreCase(this.codedLetters.get(i))){
                return false;
            }
        }
        return true;
    }

    public String getLetters() {
        return String.join("",this.letters);
    }
}
