/*****************************************************
   CS 326 - Spring 2026 - Assignment #1
   Student #1's full name: ______
   Student #2's full name: ______
   Student #3's full name: ______
*****************************************************/

import java.util.*;
import java.io.*;

class Dict
{
  int numWords;    // the number of words in the dictionary
  String[] words;  // the words included in the dictionary
  int maxLength;   // the number of characters in the longest word

  /* This constructor loads the words from the file whose name is passed
      as an argument. In this text file, there is exactly one word per line.
      This constructor initializes the three instance variables above.
      Note that the words must be stored in alphabetical order in the array 
      and that the length of the array must be equal to the number of words 
      in the input file.
  */
  Dict(String fileName)
  {
    System.out.print("Loading dictionary... ");
    ArrayList<String> wordArrayList = new ArrayList<>();
    maxLength = 0;
    File file = new File(fileName);
    try{
      for(int i = 65; i < 91; i++){

          Scanner sc = new Scanner(file);
          while(sc.hasNextLine()){
            String word = sc.nextLine().toUpperCase();
            if((char)i == word.charAt(0)){
              wordArrayList.add(word);
              numWords++;
              maxLength = Math.max(maxLength, word.length());
            }
          }
      }
      words = wordArrayList.toArray(new String[0]);
        
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    

    System.out.println("done");
  }// constructor

  /* This method returns true if and only if the given word is contained
      in the dictionary. 
  */
  boolean contains(String word)
  {
    for(int i = 0; i < words.length; i++){
      if(word.equals(words[i])){
        return true;
      }
    }

    return false;
  }// contains method
  
  /* This method returns the number of occurrences of dictionary words  
      in the given text. To be clear, if a dictionary word appears n times
      in the text, then the final count returned by this method  must be 
      incremented by n. Note that two or more words may overlap in the text.
      For example, the text "the bananas" contains the words: 
        "a" "an" "as" "ban" "banana" "bananas" among others            
    */
  int countWords(String text)
  {
    int count = 0;
    for(int i = 0; i < words.length; i++){
      int startPoint = 0;
      while(startPoint + words[i].length() <= text.length()){
        int index = text.substring(startPoint).indexOf(words[i]);
        //if the word is found move the starting point to after where it was found in text and add to count
        if(index != -1){
          count++;
          startPoint = startPoint + index + 1;
        } else {
          break;
        }
      }
    }

    return count;
  }// countWords method

  /* This method will only be used for testing purposes 
      You may NOT modify it.
    */
  public static void main(String[] args)
  {   
    Dict d = new Dict(args[0]);
    System.out.println(d.numWords + " " + d.maxLength);
    for(int index=0; index<d.words.length; index++) {
      System.out.print(d.words[index] + " ");
    }
    System.out.println(d.words[d.numWords-1]);
  }// main method

}// Dict class
