import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    // static Scanner in = new Scanner(System.in);

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
     * Reads a file containing dates and returns a list of them
     * @param file
     * @return A Date ArrayList made from values in the file
     */
    public static ArrayList<String> readFile(String file, Boolean isStopwords) {
        ArrayList<String> stringList = new ArrayList<String>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);

            if (isStopwords) {
                String s;
                while ((s = in.readLine()) != null) {
                    stringList.add(s);
                }
                in.close();
            } else {
                // Read input
            }

        } catch (FileNotFoundException e) {
            exitProgram("\nThat file does not exist.");

        } catch (Exception e){
            printLine(e.getMessage());
            exitProgram("\nAn error has occured.");
        }

        return stringList;
    }

    public static String[] deleteStopwords(String input, ArrayList<String> stopwords) {
        // Removing all punctuation regex : https://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java
        String formattedInput = input.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        for (String stopword : stopwords) {
            formattedInput.replaceAll(stopword, "");
        }

        String[] inputWords = formattedInput.split(" ");

        return inputWords;
    }

    public static void main(String[] args){
        ArrayList<String> stopwords = readFile("stopwords.txt", true);
        System.out.println(stopwords);
        String[] testInput = deleteStopwords("hello, i am a developer", stopwords);
        System.out.println(Arrays.toString(testInput));
    }
}
