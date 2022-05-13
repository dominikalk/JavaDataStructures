import java.util.ArrayList;
import java.util.Collections;

public class Program {
    /**
     * Takes in two parameters and removes all the stopwords from the first
     * @param input text to be filtered of stopwords
     * @param stopwords words to be removed from the input
     * @return the words given as a parameter with the stopwords removed
     */
    public static ArrayList<String> deleteStopwords(ArrayList<String> input, ArrayList<String> stopwords) {
        // Remove all function : https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
        for (String stopword: stopwords) {
            for (int i = 0; i < input.size(); i++) {
                // Get raw string without punctuation
                String formattedInput = input.get(i).replaceAll("[^a-zA-Z]", "");

                // Check if raw string is equal to stopword
                if (formattedInput.equals(stopword)) {
                    // Replace with empty string and add string with stopword removed
                    input.add(input.get(i).replaceAll(stopword, ""));
                    input.set(i, "");
                }
            }
        }
        // Remove all empty strings
        input.removeAll(Collections.singleton(""));
        input.removeAll(Collections.singleton("\t"));
        
        return input;
    }

    /**
     * Performs an insertion sort on the provided list of words and returns them in alphabetical order
     * @param listofWords The ArrayList of words to be sortecd
     * @return listofWords sorted alphabetically
     */
    public static ArrayList<String> insertionSort(ArrayList<String> listofWords) {
        // Insertion Sort Algorithm : https://learningcentral.cf.ac.uk/ultra/courses/_406132_1/cl/outline
        
        // Loop throught the listofWords, starting with the second element
        int swaps = 0;
        int checks = 0;
        for (int i = 1; i < listofWords.size(); i++){
            // Algorithm Timing Code
            if (i == 100 || i == 200 || i == 500) {
                AlgorithmTimer.printTime("Insertion Sort first " + i + " sorted");
            }

            String item = listofWords.get(i);
            int j = i -1;
            checks += 1;
            // Compare the previous element to item till everything before it is in order.
            while (j >= 0 && listofWords.get(j).compareTo(item) > 0) {
                swaps += 1;
                checks += 1;
                listofWords.set(j + 1, listofWords.get(j));
                j -= 1;
            }
            listofWords.set(j + 1, item);
        }

        Helpers.printLine("\nInsersion Sort Swaps : " + swaps);
        Helpers.printLine("Insersion Sort Checks : " + checks);
        return listofWords;
    }

    /**
     * Function to check if listSize is intended one and print the sorted time
     * @param listSize size of the list
     */
    public static void checkMergeTime(int listSize) {
        // Checks if listSize is 500 || 200 || 100 and the time hasn't been printed
        if (listSize == 500 && !AlgorithmTimer.checks.get("500")) {
            // Set time printed to true
            AlgorithmTimer.checks.put("500", true);
            // Print the time
            AlgorithmTimer.printTime("Merge Sort first 500 sorted");
        } else if (listSize == 200 && !AlgorithmTimer.checks.get("200")) {
            AlgorithmTimer.checks.put("200", true);
            AlgorithmTimer.printTime("Merge Sort first 200 sorted");
        } else if (listSize == 100 && !AlgorithmTimer.checks.get("100")) {
            AlgorithmTimer.checks.put("100", true);
            AlgorithmTimer.printTime("Merge Sort first 100 sorted");
        }
    }

    /**
     * Takes two sublists and merges them whilst keeping the order alphabetical
     * @param s1 Sublist one
     * @param s2 Sublist two
     * @return The two sublists merged together in alphabetical order
     */
    public static ArrayList<String> merge(ArrayList<String> s1, ArrayList<String> s2){
        ArrayList<String> mergedList = new ArrayList<String>();
        // Move elements from sublists to merged list depending on the alphabetical order
        int j = 0;
        int i = 0;
        int s1Length = s1.size();
        while (s1.size() != 0 && s2.size() != 0){
            // If s1 first element is less, add to mergedList and remove from s1
            if (s1.get(0).compareTo(s2.get(0)) <= 0) {
                mergedList.add(s1.get(0));
                s1.remove(0);
                AlgorithmTimer.mergeInverstions += j;
                i += 1;
            } 
            // If s2 first element is less, add to mergedList and remove from s2
            else {
                mergedList.add(s2.get(0));
                s2.remove(0);
                j += 1;
            }

            // Algorithm timer (mod function to reduce function calls)
            if (mergedList.size() % 100 == 0) {
                checkMergeTime(mergedList.size());
            }
        }
        // Move remaining elements in s1 to mergedList
        while (s1.size() != 0) {
            mergedList.add(s1.get(0));
            s1.remove(0);

            // Algorithm timer (mod function to reduce function calls)
            if (mergedList.size() % 100 == 0) {
                checkMergeTime(mergedList.size());
            }
        }
        // Move remaining elements in s2 to mergedList
        while (s2.size() != 0) {
            mergedList.add(s2.get(0));
            s2.remove(0);

            // Algorithm timer (mod function to reduce function calls)
            if (mergedList.size() % 100 == 0) {
                checkMergeTime(mergedList.size());
            }
        }

        AlgorithmTimer.mergeInverstions += j * (s1Length - i);

        return mergedList;
    }

    /**
     * Performs a merge sort on the provided list of words and returns them in alphabetical order
     * @param listofWords The ArrayList of words to be sortecd
     * @param p The start index of the sublist from the root one
     * @param r The end index of the sublist from the root one
     * @return ArrayList of the sublist provided that's in alphabetical order
     */
    public static ArrayList<String> mergeSort(ArrayList<String> listofWords, int p, int r) {
        // Merge Sort Algorithm : https://learningcentral.cf.ac.uk/ultra/courses/_406132_1/cl/outline
        ArrayList<String> mergedList = new ArrayList<String>();
        if (p < r) {
            // Find the midpoint of the list
            int q = (int)Math.floor((p + r) / 2);
            // Create merged two sublists that are in alphabetical order
            ArrayList<String> s1 = mergeSort(listofWords, p, q);
            ArrayList<String> s2 = mergeSort(listofWords, q + 1, r);
            // Merge the two sublists just created
            mergedList = merge(s1, s2);
        } else {
            // If the sublist contains one element, append it to the mergedList
            mergedList.add(listofWords.get(p));
        }

        return mergedList;
    }

    public static void main(String[] args){
        // Get user directory input
        Helpers.printLine("What directory are stopwords.txt and input.txt in? (Press enter if it's in the same one): ");
        String directory = Helpers.readLine();
        // Remove trailing / if exists
        if (directory.length() > 0 && directory.substring(directory.length() - 1, directory.length()).equals("/")) {
            directory = directory.substring(0, directory.length() - 1);
        }
        // Read Files
        // Even if the user starts the directory string with ./ or ../, adding a ./ before still works
        ArrayList<String> stopwords = Helpers.readFile("./" + directory + "/stopwords.txt", true);
        ArrayList<String> input = Helpers.readFile("./" + directory + "/input.txt", false);
        // Remove Stopwords
        Helpers.printLine("\nInput with stopwords removed:\n");
        ArrayList<String> filteredInput = deleteStopwords(input, stopwords);
        System.out.println(filteredInput);
        // Sort with Insersion Sort
        AlgorithmTimer.resetStartTime();
        Helpers.printLine("\nInput with stopwords removed insertion sorted:\n");
        ArrayList<String> insertionSortedInput = insertionSort(new ArrayList<String>(filteredInput));
        Helpers.printLine("");
        System.out.println(insertionSortedInput);
        // Sort with Merge Sort
        AlgorithmTimer.checks.put("100", false);
        AlgorithmTimer.checks.put("200", false);
        AlgorithmTimer.checks.put("500", false);
        AlgorithmTimer.resetStartTime();
        Helpers.printLine("\nInput with stopwords removed merge sorted:\n");
        ArrayList<String> mergeSortedInput = mergeSort(new ArrayList<String>(filteredInput), 0, filteredInput.size() - 1);
        Helpers.printLine("\nMerge Sort Inversions : " + AlgorithmTimer.mergeInverstions);
        Helpers.printLine("");
        System.out.println(mergeSortedInput);
        // Test MyArrayQueue
        Helpers.printLine("\nTest MyArrayQueue:\n");
        MyArrayQueue.main(null);
    }
}