package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    public Library(){
        welcomeMessage();
        menu(-1);
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void menu(int option){
        System.out.println("Menu of options:\n   1: View the list of all books");
        System.out.println("Select option: ");
        Scanner scanner = new Scanner(System.in);
        if (option == -1){
            option = scanner.nextInt();
        }

        switch (option){
            case 1:
                showAllBooks();
        }
    }

    public static ArrayList<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
        return books;
    }

    public static void showAllBooks(){
        ArrayList<Book> books = getAllBooks();
        books.forEach(book -> System.out.println(book.getAuthor() + " - " + book.getTitle() + " (" + book.getPublicationYear() + ")"));
    }
}

