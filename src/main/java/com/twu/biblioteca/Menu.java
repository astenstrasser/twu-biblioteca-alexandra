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
    handleInput(this.console.askInt());
  }

  public String getOptions() {
    String options = "Menu of options:\n";
    options += "    1: View the list of all books\n";
    options += "    2: Checkout book\n";
    options += "    3: Return book\n";
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
        handleCheckout();
        displayMenu();
        break;

      case 3:
        handleReturn();
        displayMenu();
        break;

      default:
        console.write("Invalid Option. Please select a valid option");
        displayMenu();
        break;
    }
  }

  private void handleReturn() {
    console.write("Please write the ID of the book you want to return");
    try {
      library.returnBook(console.askText());
      console.write("Thank you for returning the book\n");
    } catch (Exception e) {
      console.write("That is not a valid book to return\n");
    }
  }

  private void handleCheckout() {
    console.write("Please write the ID of the book you want to checkout");
    try {
      library.checkoutBook(console.askText());
      console.write("Thank you! Enjoy the book\n");
    } catch (Exception e) {
      console.write("Sorry, that book is not available\n");
    }
    return;
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
