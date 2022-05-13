import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Helpers {
    // Scanner : https://cardiff.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=c0b366a5-e664-4228-ad1e-ae32006171e5&instance=CLAWSBlackboard
    static Scanner in = new Scanner(System.in);

    /**
     * Used to print a line to save time coding and clean up code
     * @param message
     */
    public static void printLine(String message){
        System.out.println(message);
    }

    /**
     * Exits the program, displaying a message first
     * @param message
     */
    public static void exitProgram(String message) {
        printLine(message);
        printLine("");
        System.exit(400);
    }

    /**
     * Returns the line written by user in terminal
     * @return The string that was written by the user
     */
    public static String readLine(){
        return in.nextLine();
    }

    /**
     * Reads a file with name fileName and returns an arrayList with all the words in it
     * @param fileName The name of the file to be read from
     * @param isStopwords Boolean variable for if the .txt contains stopwords
     * @return ArrayList<String> of the words in the file
     */
    public static ArrayList<String> readFile(String fileName, boolean isStopwords) {
        // Reading Files : https://cardiff.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=86d25a70-2c64-4a30-8fa6-ae3200617166&instance=CLAWSBlackboard
        ArrayList<String> stringList = new ArrayList<String>();

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(reader);

            // Reading the file is slightly different depending on if it's a stopword or input
            if (isStopwords) {
                String s;
                while ((s = in.readLine()) != null) {
                    stringList.add(s);
                }
                in.close();
            } else {
                String s;
                while ((s = in.readLine()) != null) {
                    // Split line by space and ' (so words like "don't" become "don t" which are stopwords)
                    String[] splitLine = s.toLowerCase().split("[ ']");
                    Collections.addAll(stringList, splitLine);    
                }
                // Remove any stray empty strings
                stringList.removeAll(Collections.singleton(""));
            }
           
        // Error handling code
        } catch (FileNotFoundException e) {
            exitProgram("\n" + fileName + " does not exist.");

        } catch (Exception e){
            printLine(e.getMessage());
            exitProgram("\nAn error has occured.");
        }

        return stringList;
    }
}
