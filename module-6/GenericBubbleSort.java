// JOSE VELAZQUEZ
// MODULE 6.2 ASSIGNMENT 
// DATE: 04/23/2026
// This program demonstrates a generic bubble sort implementation using 
// both Comparable and Comparator interfaces.


import java.util.Comparator;

public class GenericBubbleSort {
    
    // Method 1: Using Comparable
    public static <T extends Comparable<T>> void bubbleSortComparable(T[] array) {
        T temp;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                // Compare using compareTo()
                if (array[j].compareTo(array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Method 2: Using Comparator
    public static <T> void bubbleSortComparator(T[] array, Comparator<T> comp) {
        T temp;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                // Compare using Comparator
                if (comp.compare(array[j], array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Utility method to print arrays
    public static <T> void printArray(T[] array) {
        System.out.print("Array: ");
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // TEST CODE (Main Method)
    public static void main(String[] args) {

        // Test 1: Integer
        Integer[] numbers = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};

        System.out.println("Before Comparable Sort (Integers):");
        printArray(numbers);

        bubbleSortComparable(numbers);

        System.out.println("After Comparable Sort (Integers):");
        printArray(numbers);


        // Test 2: String
        String[] words = {"Banana", "Apple", "Orange", "Mango"};

        System.out.println("\nBefore Comparable Sort (Strings):");
        printArray(words);

        bubbleSortComparable(words);

        System.out.println("After Comparable Sort (Strings):");
        printArray(words);


        // Test 3: Comparator 
        Integer[] numbersDesc = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};

        System.out.println("\nBefore Comparator Sort (Descending):");
        printArray(numbersDesc);

        bubbleSortComparator(numbersDesc, (a, b) -> b - a);

        System.out.println("After Comparator Sort (Descending):");
        printArray(numbersDesc);


        // Test 4: Comparator 
        String[] wordsByLength = {"Banana", "Kiwi", "Apple", "Fig"};

        System.out.println("\nBefore Comparator Sort (By Length):");
        printArray(wordsByLength);

        bubbleSortComparator(wordsByLength, (a, b) -> a.length() - b.length());

        System.out.println("After Comparator Sort (By Length):");
        printArray(wordsByLength);
    }
}
