package com.twu.biblioteca;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryTest {
  private static final Book BOOK_ONE = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
  private static final Book BOOK_TWO = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);

  @Test
  public void AddBookToLibrary() {
    // given
    Library library = new Library();

    // when
    library.addBook(BOOK_ONE);

    // then
    assertThat(library.getBooks()).containsOnly(BOOK_ONE);
  }

  @Test
  public void shouldReturnListOfAllBooks() {
    // given
    Library library = new Library();

    // when
    library.addBook(BOOK_ONE);
    library.addBook(BOOK_TWO);
    List<Book> books = library.getBooks();

    // then
    assertThat(books.get(0).getAuthor()).isEqualTo(BOOK_ONE.getAuthor());
    assertThat(books.get(1).getAuthor()).isEqualTo(BOOK_TWO.getAuthor());
    assertThat(books.size()).isEqualTo(2);
  }
}
