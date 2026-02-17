
/*****************************************************
   CS 326 - Spring 2026 - Assignment #1
   Student #1's full name: ______
   Student #2's full name: ______
   Student #3's full name: ______
*****************************************************/

import java.util.*;

class DT {

    /*
     * This method takes in a plaintext message and returns
     * a modified version of it in which each period is replaced
     * with "XX" and all characters other than uppercase
     * letters are deleted.
     */
    static String prepare(String plaintext) {
        String preparedText = "";
        for (int i = 0; i < plaintext.length(); i++) {
            if (plaintext.charAt(i) == '.') {
                preparedText = preparedText + "XX";
            } else {
                preparedText = preparedText + plaintext.charAt(i);
            }
        }

        return preparedText;
    }// prepare method

    /*
     * This method implements a single transposition step in the encryption
     * algorithm for this problem. It takes as input the text to transpose
     * and the transposition key. It returns the transposed input.
     */
    static String transpose(String text, String key) {
        int numCols = key.length();
        // Pad text with X's so its length is a multiple of numCols
        while (text.length() % numCols != 0)
            text = text + "X";

        int numRows = text.length() / numCols;

        // Build the grid: grid[row][col]
        char[][] grid = new char[numRows][numCols];
        for (int r = 0; r < numRows; r++)
            for (int c = 0; c < numCols; c++)
                grid[r][c] = text.charAt(r * numCols + c);

        // Determine column order: rank each key letter alphabetically
        // (alphabetically smallest letter gets rank 1, ties broken left-to-right)
        int[] order = columnOrder(key);

        // Read columns in ranked order (rank 1 first, rank 2 second, ...)
        String result = "";
        for (int rank = 1; rank <= numCols; rank++)
            for (int col = 0; col < numCols; col++)
                if (order[col] == rank)
                    for (int r = 0; r < numRows; r++)
                        result = result + grid[r][col];

        return result;
    }// transpose method

    /*
     * This method implements a single transposition step in the decryption
     * algorithm for this problem. It takes as input the tranposed text
     * and the transposition key. It returns the "untransposed" input.
     */
    static String reverseTranspose(String text, String key) {
        int numCols = key.length();
        int numRows = text.length() / numCols;

        // Determine column order used during encryption
        int[] order = columnOrder(key);

        // Reconstruct the grid by filling columns in the same ranked order
        char[][] grid = new char[numRows][numCols];
        int idx = 0;
        for (int rank = 1; rank <= numCols; rank++)
            for (int col = 0; col < numCols; col++)
                if (order[col] == rank)
                    for (int r = 0; r < numRows; r++) {
                        grid[r][col] = text.charAt(idx);
                        idx++;
                    }

        // Read the grid back row by row
        String result = "";
        for (int r = 0; r < numRows; r++)
            for (int c = 0; c < numCols; c++)
                result = result + grid[r][c];

        return result;
    }// reverseTranspose method

    /*
     * Returns an array where order[col] is the 1-based rank of that column
     * when the key letters are sorted alphabetically. Ties are broken by
     * the left-to-right position of the letter in the key (leftmost = lower
     * rank number).
     */
    private static int[] columnOrder(String key) {
        int n = key.length();
        int[] order = new int[n];
        boolean[] used = new boolean[n];

        for (int rank = 1; rank <= n; rank++) {
            int best = -1;
            for (int col = 0; col < n; col++) {
                if (!used[col]) {
                    if (best == -1 || key.charAt(col) < key.charAt(best))
                        best = col;
                }
            }
            order[best] = rank;
            used[best] = true;
        }
        return order;
    }

    /*
     * This method implements the complete encryption algorithm described
     * in the manual. It takes in the names of three files containing the
     * plaintext, the first key, and the second key, respectively. It
     * returns the ciphertext.
     * The body of this method must implement the following steps:
     * 1. Load the plaintext from the file.
     * 2. Process the plaintext using the 'prepare' method.
     * 3. Perform the first transposition using the first key.
     * 4. Perform the second transposition using the second key.
     * 5. Return the ciphertext.
     */
    static String encrypt(String plaintextFile,
            String key1File, String key2File) {
        String plaintext = Utils.loadFromFile("plaintext", plaintextFile).toUpperCase();
        String key1 = Utils.loadFromFile("key1", key1File).toUpperCase().trim();
        String key2 = Utils.loadFromFile("key2", key2File).toUpperCase().trim();
        String prepared = prepare(plaintext);
        String firstPass = transpose(prepared, key1);
        String secondPass = transpose(firstPass, key2);

        return secondPass;

    }// encrypt method

    /*
     * This method implements the complete decryption algorithm corresponding
     * to the encryption algorithm described in the manual. It takes in the
     * names of three files containing the ciphertext, a dictionary, and a set
     * of possible keys. The decryption algorithm is the reverse of the
     * encryption algorithm. This method decrypts the ciphertext with all
     * possible pairs of keys and returns the plaintext that contains the
     * largest number of dictionary words.
     */
    static String decrypt(String ciphertextFile,
            String dictFile, String keyspaceFile) {
        String ciphertext = Utils.loadFromFile("ciphertext", ciphertextFile).toUpperCase().trim();
        Dict dict = new Dict(dictFile);
        String keyspace = Utils.loadFromFile("keyspace", keyspaceFile).toUpperCase();

        String[] keys = keyspace.split("\\s+");

        String bestPlaintext = "";
        int bestCount = -1;

        for (String key1 : keys)
            for (String key2 : keys) {
                String candidate = reverseTranspose(
                        reverseTranspose(ciphertext, key2),
                        key1);
                int count = dict.countWords(candidate);
                if (count > bestCount) {
                    bestCount = count;
                    bestPlaintext = candidate;
                }
            }

        return bestPlaintext;
    }// decrypt method

    /*
     * This method will be used for testing purposes. You may NOT modify it.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
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

        if (args[0].equals("e")) {
            System.out.println(encrypt(args[1], args[2], args[3]));
        } else if (args[0].equals("d")) {
            String plaintext = decrypt(args[1], args[2], args[3]);
            System.out.println("Most likely plaintext = \n  " + plaintext);
        } else {
            System.out.println("Invalid value for the first argument: " +
                    args[0]);
            System.exit(1);
        }
    }// main method
}// DT class
