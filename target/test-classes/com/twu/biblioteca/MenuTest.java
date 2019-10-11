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
  private Book bookOne = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
  private Book bookTwo = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);
  private Movie movieOne = new Movie("Fight Club", "David Fincher", 1999, 8.8);
  private Movie movieTwo = new Movie("The Matrix", "Lana Wachowski and Lilly Wachowski", 1999, 8.7);

  @Before
  public void TestSetup() {
    this.library = new Library();

    library.addLibraryItem(bookOne);
    library.addLibraryItem(bookTwo);
    library.addLibraryItem(movieOne);
    library.addLibraryItem(movieTwo);
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
    Assertions.assertThat(gotOptions).contains("2: View the list of all movies");
    Assertions.assertThat(gotOptions).contains("3: Checkout item");
    Assertions.assertThat(gotOptions).contains("4: Return item");
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
  public void shouldAskForIdWhenCheckingOutABook() {
    // given
    String userInput = bookOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(3);

    // then
    Assertions.assertThat(outContent.toString())
        .contains("Please write the ID of the item you want to checkout");
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
    menu.handleInput(4);

    // then
    Assertions.assertThat(outContent.toString())
        .contains("Please write the ID of the item you want to return");
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
    menu.handleInput(3);

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
    library.checkoutItem(bookOne.getId().toString());
    menu.handleInput(4);

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
    menu.handleInput(3);

    // then
    Assertions.assertThat(outContent.toString()).contains("Thank you! Enjoy!");
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
    menu.handleInput(3);

    // then
    Assertions.assertThatExceptionOfType(Exception.class).isThrownBy(() -> menu.handleInput(2));
  }

  @Test
  public void shouldShowTheListOfAllMovies() {
    // given
    byte[] userInput = "0".getBytes();
    InputStream in = new ByteArrayInputStream(userInput);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThat(outContent.toString()).contains("Fight Club");
    Assertions.assertThat(outContent.toString()).contains("The Matrix");
  }

  @Test
  public void shouldNotShowAnyBooksWhenShowListOfMovies() {
    // given
    byte[] userInput = "0".getBytes();
    InputStream in = new ByteArrayInputStream(userInput);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(2);

    // then
    Assertions.assertThat(outContent.toString()).doesNotContain("Kurt Vonnegut");
    Assertions.assertThat(outContent.toString()).doesNotContain("Ray Bradbury");
  }

  @Test
  public void shouldCheckOutMovie() {
    // given
    String userInput = movieOne.getId().toString() + "\n0";
    InputStream in = new ByteArrayInputStream(userInput.getBytes());

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outContent);

    Console console = new Console(in, out);
    Menu menu = new Menu(library, console);

    // when
    menu.handleInput(3);

    // then
    Assertions.assertThat(outContent.toString())
        .contains("Please write the ID of the item you want to checkout");
    Assertions.assertThat(outContent.toString()).contains("Thank you! Enjoy!");
  }
}
