public class decrypt {
    public static String CipherText(String plainText){
        String cipher1 = "";
        String cipher2 = "";
        if(plainText.length()<2){
            return plainText;
        }

        for(int i = 0; i<plainText.length(); i++){
            if(i%2==0){
                cipher1 = cipher1 + plainText.charAt(i);
            }
            else{
                cipher2 = cipher2 + plainText.charAt(i);
            }
        }
        return CipherText(cipher1) + CipherText(cipher2);
    }

    public static String decryptString(String cipher){
        int n = cipher.length();

        // Base case
        if (n < 3) {
            return cipher;
        }

        // Determine subtree sizes
        int leftSize = (n + 1) / 2;  // ceil(n/2)
        int rightSize = n / 2;       // floor(n/2)

        // Split ciphertext into left and right parts
        String leftCipher = cipher.substring(0, leftSize);
        String rightCipher = cipher.substring(leftSize);

        // Recursively decrypt each half
        String leftPlain = decryptString(leftCipher);
        String rightPlain = decryptString(rightCipher);

        // Interleave to reconstruct original order
        return interleave(leftPlain, rightPlain);
    }

    private static String interleave(String left, String right) {
        StringBuilder result = new StringBuilder();

        int i = 0, j = 0;

        while (i < left.length() || j < right.length()) {

            if (i < left.length()) {
                result.append(left.charAt(i));
                i++;
            }

            if (j < right.length()) {
                result.append(right.charAt(j));
                j++;
            }
        }

        return result.toString();
    }
    public static void main(String args[]){
        System.out.println(CipherText("THISCOURSEISANINTROTOCOMPUTERSECURITYWITHANEMPHASISONSOFTWAREDESIGNPRINCIPLESANDTECHNICALCONTROLS"));
        System.out.println(decryptString("TRMENBDEEVPUTYSHOECELIOAARISP"));
    }
}
