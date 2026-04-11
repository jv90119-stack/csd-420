// JOSE VELAZQUEZ
// MODULE 3.2 ASSIGNMENT 
// DATE: 04/11/2026
// This program defines a generic method to remove duplicates from 
// an ArrayList and tests it with random integers. 


import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesTest {

    // Generic method
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();

        for (E element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }

        return newList;
    }

    public static void main(String[] args) {

        // Original ArrayList
        ArrayList<Integer> originalList = new ArrayList<>();
        Random rand = new Random();

        // Fill with 50 random values
        for (int i = 0; i < 50; i++) {
            originalList.add(rand.nextInt(20) + 1);
        }

        // Display original list
        System.out.println("Original List:");
        System.out.println(originalList);

        // Call method to remove duplicates
        ArrayList<Integer> noDuplicates = removeDuplicates(originalList);

        // Display new list
        System.out.println("\nList Without Duplicates:");
        System.out.println(noDuplicates);
    }
}