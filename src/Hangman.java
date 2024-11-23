// ADDING TO GITHUB
/*
  Author Name: Seth Inskeep
  Date: 11/19/24
  File Name: HangmanApp.java
  Program Description: Class stored several methods used in the game of hang man as well as an instance of hang man
*/

import java.util.Scanner;

public class Hangman {
    private final WordList wordList = new WordList();
    private final String word;
    private final int wordLength;
    private int remainingLetters;
    private String displayedWord;
    private int numGuesses = 0;
    private int numWrong = 0;
    private String guessedLetters = "";
    private final Scanner sc = new Scanner(System.in);
    
    public Hangman() {
        word = wordList.getRandomWord().toUpperCase();
        wordLength = word.length();
        remainingLetters = wordLength;
        displayedWord = "_".repeat(wordLength);
    }
    
    public char getLetter() {
        while (true) {
            System.out.print("Enter a letter: ");
            String s = sc.nextLine().toUpperCase();
            
            if (s.length() == 1) {
                char c = s.charAt(0);
                if (Character.isLetter(c)) {
                    if (guessedLetters.indexOf(c) == -1) {
                        numGuesses++;
                        guessedLetters += c;
                        return c;
                    } else {
                        System.out.println("You already tried that letter.");
                        continue;
                    }
                }
            } 
            // if you're here, user didn't enter a single letter
            System.out.println("Please enter 1 and only 1 letter.");
        }
    }
    
    public void drawLine() {
        System.out.println("*".repeat(64));
    }
    
    public void drawScreen() {
        drawLine();
        drawHangman(numWrong);
        String display = String.format(
            "Word: %s   Guesses: %d  Wrong: %d  Tried: %s", 
            wordList.addSpaces(displayedWord), 
            numGuesses, numWrong, 
            wordList.addSpaces(guessedLetters));
        System.out.println(display);
    }
    
    public void playGame() {
        drawScreen();
        
        while (numWrong < 7 && remainingLetters > 0) {
            char guess = getLetter();

            int index = word.indexOf(guess);            
            if (index == -1) {
                numWrong++;
            } else {
                displayedWord = "";
                remainingLetters = word.length();
                
                for(char c: word.toCharArray()){
                    if (guessedLetters.indexOf(c) == -1) {
                        displayedWord += "_";
                    } else {
                        displayedWord += c;
                        remainingLetters--;  //decrement remaining letters
                    }
                }
            }
            drawScreen();
        }
        drawLine();
        if (remainingLetters == 0) {
            System.out.println(String.format(
                "Congratulations! You got it in %d guesses.", numGuesses));
        } else {
            System.out.println(String.format(
                "Sorry, you lost. The word was %s.", word));
        }
    }
    
    public void drawHangman(int numberWrong) {
        switch (numberWrong) {
            case 0 -> System.out.println("""
                            ____
                               |
                            """);
            case 1 -> System.out.println("""
                            ____
                               |
                               0
                            """);       
            case 2 -> System.out.println("""
                            ____
                               |
                               0
                              \\
                            """);          
            case 3 -> System.out.println("""
                            ____
                               |
                               0
                              \\|
                            """);
            case 4 -> System.out.println("""
                            ____
                               |
                               0
                              \\|/
                            """);  
            case 5 -> System.out.println("""
                            ____
                               |
                               0
                              \\|/
                               |
                            """);  
            case 6 -> System.out.println("""
                            ____
                               |
                               0
                              \\|/
                               |
                              /
                            """);  
            case 7 -> System.out.println("""
                            ____
                               |
                               0
                              \\|/
                               |
                              / \\
                            """); 
        }
    }
}