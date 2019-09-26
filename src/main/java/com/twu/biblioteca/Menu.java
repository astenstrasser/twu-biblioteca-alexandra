package com.twu.biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Library library;

    public Menu(Library library) {
        this.library = library;
    }

    public void displayMenu(){
        showOptions();
        int option = askUserOption();
        boolean isOptionValid = redirectToUsersOption(option);
        if (! isOptionValid ) {
            displayMenu();
        }
    }

    public void showOptions() {
        System.out.println("Menu of options:");
        System.out.println("    1: View the list of all books");
        System.out.println("    0: Exit biblioteca");
    }

    public int askUserOption() {
        System.out.println("Select option: ");
        Scanner scanner = new Scanner(System.in);
        int option;
        try{
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            option = -1;
        }
        return option;
    }

    public boolean redirectToUsersOption(int option) {
        boolean isOptionValid = true;
        switch (option) {
            case 0:
                System.exit(0);
            case 1:
                this.library.showAllBooks();
                break;
            default:
                System.out.println("Invalid Option. Please select a valid option");
                isOptionValid = false;
                break;
        }
        return isOptionValid;
    }


}
