/*****************************************************
   CS 326 - Spring 2026 - Assignment #1
   Student #1's full name: ______
   Student #2's full name: ______
   Student #3's full name: ______
*****************************************************/

import java.util.*;
import java.io.*;

class DT
{

    /* This method takes in a plaintext message and returns 
       a modified version of it in which each period is replaced
       with "XX" and all characters other than uppercase
       letters are deleted.
     */
    static String prepare(String plaintext)
    {
        String preparedString = "";

        for(int i = 0; i < plaintext.length(); i++){
            if(Character.isUpperCase(plaintext.charAt(i))){
                preparedString += plaintext.charAt(i);
            }
            else if(i == plaintext.indexOf(".")){
                preparedString += "XX";
            }
        }

	return preparedString; // only here to satisfy the compiler
    }// prepare method

    /* This method implements a single transposition step in the encryption
       algorithm for this problem. It takes as input the text to transpose
       and the transposition key. It returns the transposed input.
    */
    static String transpose(String text, String key)
    {
        int numRows = (int)Math.ceil((double)text.length()/key.length());
        char[][] table = new char[numRows][key.length()];
        String transposedText = "";

        //fills table
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[0].length; j++){
                if(j + key.length() * i < text.length()){
                    table[i][j] = text.charAt(j + key.length() * i);
                }
                
            }
        }

        ArrayList<Integer> indexOrder = new ArrayList<Integer>();
        int minIndex = -1;
        
        while(indexOrder.size() != key.length()){
            char min = Character.MAX_VALUE;
            for(int i = 0; i < key.length(); i++){
                if(key.charAt(i) < min && !indexOrder.contains(i)){
                    min = key.charAt(i);
                    minIndex = i;
                }
            }
            indexOrder.add(minIndex);
        }

        for(int i = 0; i < indexOrder.size(); i++){
            for(int j = 0; j < numRows; j++){
                transposedText += table [j][indexOrder.get(i)];
            }
        }

	return transposedText;
    }// transpose method

    /* This method implements a single transposition step in the decryption
       algorithm for this problem. It takes as input the tranposed text 
       and the transposition key. It returns the "untransposed" input.
    */
    static String reverseTranspose(String text, String key)
    {
	
        int numRows = (int)Math.ceil((double)text.length()/key.length());
        char[][] table = new char[numRows][key.length()];
        
        ArrayList<Integer> indexOrder = new ArrayList<Integer>();
        int minIndex = -1;
        
        while(indexOrder.size() != key.length()){
            char min = Character.MAX_VALUE;
            for(int i = 0; i < key.length(); i++){
                if(key.charAt(i) < min && !indexOrder.contains(i)){
                    min = key.charAt(i);
                    minIndex = i;
                }
            }
            indexOrder.add(minIndex);
        }

        for(int i = 0; i < indexOrder.size(); i++){
            for(int j = 0; j < numRows; j++){
                table[j][indexOrder.get(i)] = text.charAt(i * numRows + j);
            }
        }

        String decodedText = "";

        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                decodedText += table[i][j];
            }
        }

	return decodedText; // only here to satisfy the compiler
    }// reverseTranspose method

    /* This method implements the complete encryption algorithm described
       in the manual. It takes in the names of three files containing the
       plaintext, the first key, and the second key, respectively. It
       returns the ciphertext.
       The body of this method must implement the following steps:
       1. Load the plaintext from the file.
       2. Process the plaintext using the 'prepare' method.
       3. Perform the first transposition using the first key.
       4. Perform the second transposition using the second key.
       5. Return the ciphertext.
     */
    static String encrypt(String plaintextFile, 
			  String key1File, String key2File)
    {
        File textFile = new File(plaintextFile);
        File key1F = new File(key1File);
        File key2F = new File(key2File);
        String plaintext = "";
        String key1 = "";
        String key2 = "";
        
        try{

            Scanner sc = new Scanner(textFile);

            if(sc.hasNextLine()){
                plaintext = sc.nextLine();
            }

            sc = new Scanner(key1F);

            if(sc.hasNextLine()){
                key1 = sc.nextLine();
            }

            sc = new Scanner(key2F);

            if(sc.hasNextLine()){
                key2 = sc.nextLine();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
        String prepeardPlaintext = prepare(plaintext);
        String transposedPlaintext = transpose(prepeardPlaintext, key1);
        String doubleTransposedPlaintext = transpose(transposedPlaintext, key2);

	return doubleTransposedPlaintext;
    }// encrypt method

    /* This method implements the complete decryption algorithm corresponding 
       to the encryption algorithm described in the manual. It takes in the 
       names of three files containing the ciphertext, a dictionary, and a set
       of possible keys. The decryption algorithm is the reverse of the
       encryption algorithm. This method decrypts the ciphertext with all 
       possible pairs of keys and returns the plaintext that contains the 
       largest number of dictionary words. 
     */
    static String decrypt(String ciphertextFile, 
			  String dictFile, String keyspaceFile)
    {
	    File textFile = new File(ciphertextFile);
        Dict dict = new Dict(dictFile);
        File keyspace = new File(keyspaceFile);
        String ciphertext = "";
        String bestPlaintext = "";
        
        try{

            Scanner sc = new Scanner(textFile);

            if(sc.hasNextLine()){
                ciphertext = sc.nextLine();
            }

            sc = new Scanner(keyspace);
            Scanner sc2 = new Scanner(keyspace);

            int maxCount = -1;

            while(sc.hasNextLine()){
                String key1 = sc.nextLine();
                while(sc2.hasNextLine()){
                    String key2 = sc2.nextLine();
                    String plaintext = reverseTranspose(reverseTranspose(ciphertext, key1), key2);

                    int count = dict.countWords(plaintext);

                    if (count > maxCount) {
                        maxCount = count;
                        bestPlaintext = plaintext;
                    }
                }

            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

	return bestPlaintext; // only here to satisfy the compiler
    }// decrypt method

    /* This method will be used for testing purposes. You may NOT modify it.
     */
    public static void main(String[] args)
    {	
	if (args.length != 4)
	{
	    System.out.println("This program should be invoked with the " +
			       "following arguments:");
	    System.out.println("  java DT e <plaintext> <key1> <key2>");
	    System.out.println("      where e stands for encrypt and the " +
			       "bracketed expressions are file names");
	    System.out.println("or");
	    System.out.println("  java DT d <ciphertext> <dict> <key space>");
	    System.out.println("      where d stands for decrypt and the " +
			       "bracketed expressions are file names");
	    System.exit(1);
	}

	if (args[0].equals("e"))
	{
	    System.out.println(encrypt(args[1],args[2],args[3]));
	} else if (args[0].equals("d"))
	{
	    String plaintext = decrypt(args[1],args[2],args[3]);
	    System.out.println("Most likely plaintext = \n  " + plaintext);	    
	} else
	{
	    System.out.println("Invalid value for the first argument: " +
			       args[0]);
	    System.exit(1);
	}
    }// main method
}// DT class
