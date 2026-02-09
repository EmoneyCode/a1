/*****************************************************
   CS 326 - Spring 2026 - Assignment #1
   Student #1's full name: ______
   Student #2's full name: ______
   Student #3's full name: ______
*****************************************************/

class Caesar
{

    /* This method takes in a Caesar-encrypted message and the key used 
       to generate this ciphertext. It returns the original plaintext.
       The third argument determines whether the plaintext should be
       printed before returning from the method. If its value is true, 
       your code should print the value of the key, followed by a single
       white space, followed by the plaintext, followed by a single newline
       character. If its value is false, the method should return with no
       printed output.
     */
    static String decrypt(String ciphertext, int key, 
                          boolean printPlaintext)
    {
        /* To be completed */

	return ""; // only here to satisfy the compiler
    }// decrypt method

    /* This method will be used for testing purposes. You must complete it
       without deleting or modifying the provided code. This method must:
       1. load the dictionary and the ciphertext given as command-line arguments
       2. decrypt the ciphertext with all reasonable keys and print the
          plaintext that contains the largest number of dictionary words.
       Note that, besides the messages pertaining to the I/O operations on 
       the dictionary and ciphertext files, this method must print only a
       single line of text, namely the recovered plaintext, followed by a
       single newline character. In other words, the decrypt method must be
       called with 'false' as its third argument. During grading, I will
       change this value to 'true' to check the intermediate behavior of your
       code.
     */
    public static void main(String[] args)
    {   
        if (args.length != 2)
        {
            System.out.println("This program should be invoked with the " +
                               "following arguments:");
            System.out.println("  java Cesar <dictionary file name> " +
                               "<ciphertext file name>");
            System.exit(1);
        }

        String ciphertext = Utils.loadFromFile("ciphertext",args[1]);

        /* To be completed */

    }// main method
    
}// Caesar class
