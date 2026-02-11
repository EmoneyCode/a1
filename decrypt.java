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
    public static void main(String args[]){
        System.out.println(CipherText("THISCOURSEISANINTROTOCOMPUTERSECURITYWITHANEMPHASISONSOFTWAREDESIGNPRINCIPLESANDTECHNICALCONTROLS"));
    }
}
