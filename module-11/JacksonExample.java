// JOSE VELAZQUEZ
// MODULE 11.2 ASSIGNMENT 
// DATE: 05/24/2026
// This code demonstrates how to use the Jackson library to convert a Java 
// object to JSON and back to a Java object.


import com.fasterxml.jackson.databind.ObjectMapper;

class Book {
    public String title;
    public String author;
    public int year;

    public Book() {
        // Default constructor needed by Jackson
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}

public class JacksonExample {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Book book = new Book("The Alchemist", "Paulo Coelho", 1988);

            // Convert Java object to JSON
            String json = mapper.writeValueAsString(book);
            System.out.println("Java object converted to JSON:");
            System.out.println(json);

            // Convert JSON back to Java object
            Book convertedBook = mapper.readValue(json, Book.class);
            System.out.println("\nJSON converted back to Java object:");
            System.out.println("Title: " + convertedBook.title);
            System.out.println("Author: " + convertedBook.author);
            System.out.println("Year: " + convertedBook.year);

        } catch (Exception e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }
    }
}