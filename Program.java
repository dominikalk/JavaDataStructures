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
        // Remove all function : https://howtodoinjava.com/java/collections/arraylist/arraylist-remove-example/
        for (String stopword: stopwords) {
            input.removeAll(Collections.singleton(stopword));
        }

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
        for (int i = 1; i < listofWords.size(); i++){
            String item = listofWords.get(i);
            int j = i -1;
            // Compare the previous element to item till everything before it is in order.
            while (j >= 0 && listofWords.get(j).compareTo(item) > 0) {
                listofWords.set(j + 1, listofWords.get(j));
                j -= 1;
            }
            listofWords.set(j + 1, item);
        }

        return listofWords;
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
        while (s1.size() != 0 && s2.size() != 0){
            if (s1.get(0).compareTo(s2.get(0)) <= 0) {
                mergedList.add(s1.get(0));
                s1.remove(0);
            } else {
                mergedList.add(s2.get(0));
                s2.remove(0);
            }
        }
        // Move remaining elements in s1 to mergedList
        while (s1.size() != 0) {
            mergedList.add(s1.get(0));
            s1.remove(0);
        }
        // Move remaining elements in s2 to mergedList
        while (s2.size() != 0) {
            mergedList.add(s2.get(0));
            s2.remove(0);
        }

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
        // Read Files
        ArrayList<String> stopwords = Helpers.readFile("stopwords.txt", true);
        ArrayList<String> input = Helpers.readFile("input.txt", false);
        // Remove Stopwords
        ArrayList<String> filteredInput = deleteStopwords(input, stopwords);
        Helpers.printLine("\nInput with stopwords removed:");
        System.out.println(filteredInput);
        // Sort with Insersion Sort
        ArrayList<String> insertionSortedInput = insertionSort(filteredInput);
        Helpers.printLine("\nInput with stopwords removed insertion sorted:");
        System.out.println(insertionSortedInput);
        // Sort with Merge Sort
        ArrayList<String> mergeSortedInput = mergeSort(filteredInput, 0, filteredInput.size() - 1);
        Helpers.printLine("\nInput with stopwords removed merge sorted:");
        System.out.println(mergeSortedInput);
    }
}