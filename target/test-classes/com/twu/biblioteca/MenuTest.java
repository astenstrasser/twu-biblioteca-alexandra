package com.twu.biblioteca;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuTest {

  private Library library;
  private Book bookOne;
  private Book bookTwo;

  @Before
  public void TestSetup() {
    this.library = new Library();
    this.bookOne = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
    this.bookTwo = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);

    library.addLibraryItem(bookOne);
    library.addLibraryItem(bookTwo);
  }

  @Test
  public void shouldGetAllMenuOptions() {
    // given
    byte[] userInput = "".getBytes();
    InputStream in = new ByteArrayInputStream(userInput);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    String gotOptions = menu.getOptions();

    // then
    Assertions.assertThat(gotOptions).contains("Menu of options");
    Assertions.assertThat(gotOptions).contains("1: View the list of all books");
    Assertions.assertThat(gotOptions).contains("2: Checkout book");
    Assertions.assertThat(gotOptions).contains("3: Return book");
    Assertions.assertThat(gotOptions).contains("0: Exit Biblioteca");
  }

  @Test
  public void shouldShowListOfBooks() {
    // given
    byte[] userInput = "0".getBytes();
    InputStream in = new ByteArrayInputStream(userInput);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(1);

    // then
    Assertions.assertThat(outContent.toString()).contains("Kurt Vonnegut");
    Assertions.assertThat(outContent.toString()).contains("Ray Bradbury");
  }

  @Test
  public void shouldAskWhichBookUserWantsToCheckoutWhenUserPicksCheckoutOptionOnMenu() {
    // given
    String userInput = bookOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThat(outContent.toString())
        .contains("Please write the ID of the book you want to checkout");
  }

  @Test
  public void shouldAskForIdWhenReturningABook() {
    // given
    String userInput = bookTwo.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    library.checkoutItem(bookTwo.getId().toString());
    menu.handleInput(3);

    // then
    Assertions.assertThat(outContent.toString())
        .contains("Please write the ID of the book you want to return");
  }

  @Test
  public void shouldReturnInvalidOptionWhenInputIsNotOnMenu() {
    // given
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    byte[] input = "0".getBytes();
    InputStream in = new ByteArrayInputStream(input);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(400);

    // then
    Assertions.assertThat(outContent.toString()).contains("Please select a valid option");
  }

  @Test
  public void shouldExitWhenSelectedExitOptionOnMenu() {
    // given
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(System.in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(0);

    // then
    Assertions.assertThat(outContent.toString()).isEmpty();
  }

  @Test
  public void shouldReturnToMenuWhenInputGivenIsNotInteger() {
    // given
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    byte[] input = "text\n0".getBytes();
    InputStream in = new ByteArrayInputStream(input);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(console.askInt());

    // then
    Assertions.assertThat(outContent.toString()).contains("Please select a valid option");
  }

  @Test
  public void shouldChangeBookStatusWhenCheckingOutABook() {
    // given
    String input = bookOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(input.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThat(bookOne.isAvailable()).isEqualTo(false);
  }

  @Test
  public void shouldChangeBookStatusWhenReturningABook() {
    // given
    String input = bookOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(input.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    library.checkoutItem(bookTwo.getId().toString());
    menu.handleInput(3);

    // then
    Assertions.assertThat(bookOne.isAvailable()).isEqualTo(true);
  }

  @Test
  public void shouldShowSuccessMessageOnCheckoutOfABook() {
    // given
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    String input = bookOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(input.getBytes());

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThat(outContent.toString()).contains("Thank you! Enjoy the book");
  }

  @Test
  public void shouldThrowExceptionOnCheckoutOfABookWhenGivenBookIdIsNotValid() {
    // given
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    String input = "thisIsNotValidId\n0";
    InputStream in = new ByteArrayInputStream(input.getBytes());

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThatExceptionOfType(Exception.class).isThrownBy(() -> menu.handleInput(2));
  }
}
