/*****************************************************
   CS 326 - Spring 2026 - Assignment #1
   Student #1's full name: ______
   Student #2's full name: ______
   Student #3's full name: ______
*****************************************************/

import java.util.*;

class DT
{

    /* This method takes in a plaintext message and returns 
       a modified version of it in which each period is replaced
       with "XX" and all characters other than uppercase
       letters are deleted.
     */
    static String prepare(String plaintext)
    {
	/* To be completed */

	return ""; // only here to satisfy the compiler
    }// prepare method

    /* This method implements a single transposition step in the encryption
       algorithm for this problem. It takes as input the text to transpose
       and the transposition key. It returns the transposed input.
    */
    static String transpose(String text, String key)
    {
	/* To be completed */

	return ""; // only here to satisfy the compiler
    }// transpose method

    /* This method implements a single transposition step in the decryption
       algorithm for this problem. It takes as input the tranposed text 
       and the transposition key. It returns the "untransposed" input.
    */
    static String reverseTranspose(String text, String key)
    {
	/* To be completed */

	return ""; // only here to satisfy the compiler
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
	/* To be completed */

	return ""; // only here to satisfy the compiler	
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
	/* To be completed */

	return ""; // only here to satisfy the compiler
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
