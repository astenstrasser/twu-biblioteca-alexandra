package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Menu {

  private Library library;
  private Console console;

  private static final List<Integer> VALID_OPTIONS =
      new ArrayList<Integer>() {
        {
          add(1);
          add(0);
        }
      };

  public Menu(Library library, Console console) {
    this.library = library;
    this.console = console;
  }

  public void displayMenu() {
    this.console.write(
        "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    this.console.write(this.getOptions());
    this.console.write("Select option: ");
    handleInput();
  }

  public String getOptions() {
    String options = "Menu of options:\n";
    options += "    1: View the list of all books\n";
    options += "    0: Exit Biblioteca";

    return options;
  }

  public void handleInput() {

    int option = this.console.ask();

    switch (option) {
      case 0:
        return;
      case 1:
        showBooks();
        break;
      case Console.INVALID_INPUT:
        console.write("Invalid Option. Please select a valid option");
        displayMenu();
        break;
      default:
        console.write("Invalid Option. Please select a valid option");
        displayMenu();
        break;
    }
  }

  private void showBooks() {
    library
        .getBooks()
        .forEach(
            book -> {
              this.console.write(book.toString());
            });
  }
}
