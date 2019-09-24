package com.twu.biblioteca;

import java.util.ArrayList;

public class Book {

        private String title;
        private String author;
        private int publicationYear;

        public Book(String title, String author, int publicationYear){
            this.title = title;
            this.author = author;
            this.publicationYear = publicationYear;
        }

        public static ArrayList<Book> getAllBooks(){
            ArrayList<Book> books = new ArrayList<Book>();
            books.add(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
            books.add(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
            return books;
        }

        public static void viewAllBooks(){
            ArrayList<Book> books = getAllBooks();
            books.forEach(book -> System.out.println(book.author + " - " + book.title + " (" + book.publicationYear + ")"));
        }


}
