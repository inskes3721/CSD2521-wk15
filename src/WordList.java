// ADDING TO GITHUB
/*
  Author Name: Seth Inskeep
  Date: 11/19/24
  File Name: WordList.java
  Program Description: Class pulls and formats words from a text file.
*/

import java.io.*;
import java.util.ArrayList;

public class WordList {
    private final String FILENAME = "words.txt";
    private ArrayList<String> words;
    
    public WordList() {
        words = new ArrayList<>();
        try(var in = new BufferedReader(
                     new FileReader(FILENAME))){
            String word = in.readLine();
            while(word != null) {
                words.add(word);
                word = in.readLine();
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(FILENAME + " not found.");
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public String getRandomWord() {
        int length = words.size();
        if (length == 0) {
            return "";
        } else {
            int index = (int) (Math.random() * length);
            return words.get(index);
        }
    }
    
    public String addSpaces(String word) {
        return String.join(" ", word.split(""));
    }
}
