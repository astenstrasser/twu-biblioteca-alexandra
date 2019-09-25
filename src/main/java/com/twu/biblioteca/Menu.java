package com.twu.biblioteca;

import java.util.Scanner;

public class Menu {

    public Menu() {
        showMenu();
        int option = askUserOption();
        boolean isOptionValid = redirectToUsersOption(option);
        if (! isOptionValid ) {
            Menu menu = new Menu();
        }
    }

    public static void showMenu() {
        System.out.println("Menu of options:");
        System.out.println("    1: View the list of all books");
        System.out.println("    0: Exit biblioteca");
    }

    public static int askUserOption() {
        System.out.println("Select option: ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        return option;
    }

    public static boolean redirectToUsersOption(int option) {
        boolean isOptionValid = true;
        switch (option) {
            case 0:
                System.exit(0);
            case 1:
                Library.showAllBooks();
                break;
            default:
                System.out.println("Invalid Option. Please select a valid option");
                isOptionValid = false;
                break;
        }
        return isOptionValid;
    }


}
