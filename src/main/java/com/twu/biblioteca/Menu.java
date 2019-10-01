package com.twu.biblioteca;

public class Menu {

  private Library library;
  private Console console;

  public Menu(Library library, Console console) {
    this.library = library;
    this.console = console;
  }

  public void displayWelcomeMessage() {
    this.console.write(
        "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
  }

  public void displayMenu() {
    this.console.write(this.getOptions());
    this.console.write("Select option: ");
    handleInput(this.console.ask());
  }

  public String getOptions() {
    String options = "Menu of options:\n";
    options += "    1: View the list of all books\n";
    options += "    2: Checkout book\n";
    options += "    0: Exit Biblioteca";

    return options;
  }

  public void handleInput(int option) {

    switch (option) {
      case 0:
        return;
      case 1:
        showBooks();
        console.write("\n-----------\n");
        displayMenu();
        break;
      case 2:
        console.write("Please write the ID of the book you want to checkout");
        try {
          library.checkoutBook(console.askBookId());
          console.write("Thank you! Enjoy the book\n");
        } catch (Exception e) {
          console.write("No book found with given ID");
        }
        displayMenu();
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
        .getAvailableBooks()
        .forEach(
            book -> {
              this.console.write(book.toString());
            });
  }
}
