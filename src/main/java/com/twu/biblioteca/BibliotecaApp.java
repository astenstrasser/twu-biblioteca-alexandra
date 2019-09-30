package com.twu.biblioteca;

public class BibliotecaApp {

  public static void main(String[] args) {

    Library library = new Library();
    Console console = new Console(System.in, System.out);
    Menu menu = new Menu(library, console);

    library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
    library.addBook(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));

    menu.displayMenu();
  }
}
