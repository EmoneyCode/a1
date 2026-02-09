import java.io.IOException;
import java.io.File;
import java.util.Scanner;

class Utils
{
    /* this method returns the String that represents the full contents
       of the file whose name is given as its second argument. It also
       prints informative messages pertaining to I/O operations.
    */
    static String loadFromFile(String contentType, String fileName)
    {
        System.out.print("Loading " +  contentType + "... ");
        String text = "";
        try
        {
            text = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
        } catch (IOException e)
        {
            System.out.println("Error while loading text from " + fileName);
            System.exit(1);
        }
        System.out.println("done");
        return text;
    }// loadFromFile method

}// Utils class
