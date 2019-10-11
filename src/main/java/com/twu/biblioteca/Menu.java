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
    options += "    2: View the list of all movies\n";
    options += "    3: Checkout item\n";
    options += "    4: Return item\n";
    options += "    0: Exit Biblioteca";

    return options;
  }

  public void handleInput(int option) {

    switch (option) {
      case 0:
        return;

      case 1:
        showBooks();
        console.addSeparator();
        displayMenu();
        break;

      case 2:
        showMovies();
        console.addSeparator();
        displayMenu();
        break;

      case 3:
        handleCheckout();
        displayMenu();
        break;

      case 4:
        handleReturn();
        displayMenu();
        break;

      default:
        console.write("Invalid Option. Please select a valid option");
        displayMenu();
        break;
    }
  }

  private void showMovies() {
    this.console.write("Movies:\n");
    library
        .getAvailableMovies()
        .forEach(
            movie -> {
              this.console.write(movie.toString());
            });
  }

  private void handleReturn() {
    console.write("Please write the ID of the item you want to return");
    try {
      library.returnItem(console.askText());
      console.write("Thank you for returning the item\n");
    } catch (Exception e) {
      console.write("That is not a valid item to return\n");
    }
  }

  private void handleCheckout() {
    console.write("Please write the ID of the item you want to checkout");
    try {
      library.checkoutItem(console.askText());
      console.write("Thank you! Enjoy!\n");
    } catch (Exception e) {
      console.write("Sorry, that item is not available\n");
    }
    return;
  }

  private void showBooks() {
    this.console.write("Books:\n");
    library
        .getAvailableBooks()
        .forEach(
            book -> {
              this.console.write(book.toString());
            });
  }
}
