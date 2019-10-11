package com.twu.biblioteca;

public class BibliotecaApp {

  public static void main(String[] args) {

    Library library = new Library();
    Console console = new Console(System.in, System.out);
    Menu menu = new Menu(library, console);

    library.addLibraryItem(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
    library.addLibraryItem(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
    library.addLibraryItem(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));

    library.addLibraryItem(new Movie("Fight Club", "David Fincher", 1999, 8.8));
    library.addLibraryItem(new Movie("The Matrix", "Lana Wachowski and Lilly Wachowski", 1999, 8.7));

    menu.displayWelcomeMessage();
    menu.displayMenu();
  }
}
