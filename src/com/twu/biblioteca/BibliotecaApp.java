package com.twu.biblioteca;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        welcomeMessage();
        menu(0);
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static void menu(int option){
        System.out.println("Menu of options:\n   1: View the list of all books");
        System.out.println("Select option: ");
        Scanner scanner = new Scanner(System.in);
        if (option == 0){
            option = scanner.nextInt();
        }

        switch (option){
            case 1:
                Book.viewAllBooks();
        }
    }

}
