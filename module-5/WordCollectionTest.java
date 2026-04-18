// JOSE VELAZQUEZ
// MODULE 5.2 ASSIGNMENT 
// DATE: 04/17/2026
// This program reads a collection of words from a file, removes duplicates, 
// and displays them in both ascending and descending order. 


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class WordCollectionTest {

    public static void main(String[] args) {

        // File is referenced
        File file = new File("collection_of_words.txt");

        // TreeSet automatically:
        // 1. Removes duplicates
        // 2. Stores in ascending order
        Set<String> words = new TreeSet<>();

        try {
            Scanner input = new Scanner(file);

            // Read words from file
            while (input.hasNext()) {
                String word = input.next().toLowerCase(); 
                words.add(word);
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            return;
        }

        // Test: Ensure file was read correctly
        if (words.isEmpty()) {
            System.out.println("Test Failed: No words were loaded.");
            return;
        } else {
            System.out.println("Test Passed: Words successfully loaded.\n");
        }

        // Display ascending order
        System.out.println("Words in Ascending Order:");
        for (String word : words) {
            System.out.print(word + " ");
        }

        // Display descending order
        System.out.println("\n\nWords in Descending Order:");
        for (String word : ((TreeSet<String>) words).descendingSet()) {
            System.out.print(word + " ");
        }
    }
}