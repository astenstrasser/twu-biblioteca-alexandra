package com.twu.biblioteca;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {
  private static final Book BOOK_ONE = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
  private static final Book BOOK_TWO = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);

  @Test
  public void AddBookToLibrary() {
    // given
    Library library = new Library();

    // when
    library.addLibraryItem(BOOK_ONE);

    // then
    assertThat(library.getBooks()).containsOnly(BOOK_ONE);
  }

  @Test
  public void shouldReturnListOfAllBooksWhenGetBooks() {
    // given
    Library library = new Library();

    // when
    library.addLibraryItem(BOOK_ONE);
    library.addLibraryItem(BOOK_TWO);
    List<Book> books = library.getBooks();

    // then
    assertThat(books.get(0).getAuthor()).isEqualTo(BOOK_ONE.getAuthor());
    assertThat(books.get(1).getAuthor()).isEqualTo(BOOK_TWO.getAuthor());
    assertThat(books.size()).isEqualTo(2);
  }

  @Test
  public void shouldReturnJustAvailableBooksWhenGetAvailableBooks() {
    // given
    Library library = new Library();
    library.addLibraryItem(BOOK_ONE);
    library.addLibraryItem(BOOK_TWO);

    // when
    List<Book> got = library.getAvailableBooks();

    // then
    got.forEach(book -> Assertions.assertThat(book.isAvailable()).isEqualTo(true));
  }

  @Spy private Book mockBook;

  @Test
  public void shouldCheckoutBookWhenBookIsAvailableOnLibrary() {
    // given
    Library library = new Library();
    UUID id = UUID.randomUUID();
    Mockito.when(mockBook.getId()).thenReturn(id);

    // when
    library.addLibraryItem(mockBook);
    library.checkoutItem(id.toString());

    // then
    Mockito.verify(mockBook).checkout();
  }

  @Test
  public void shouldReturnBookWhenBookIsNotAvailableOnLibrary() {
    // given
    Library library = new Library();
    UUID id = UUID.randomUUID();
    Mockito.when(mockBook.getId()).thenReturn(id);

    // when
    library.addLibraryItem(mockBook);
    mockBook.checkout();
    library.returnItem(id.toString());

    // then
    Assertions.assertThat(mockBook.isAvailable()).isEqualTo(true);
    Mockito.verify(mockBook).returnToLibrary();
  }

  @Test
  public void shouldReturnNoneWhenSearchBookByNonExistentId() {
    // given
    Library library = new Library();
    String nonExistentId = "NonExistentId012345";

    // when
    Book got = (Book) library.searchItemById(nonExistentId);

    // then
    Assertions.assertThat(got).isEqualTo(null);
  }

  @Test
  public void shouldReturnBookWhenSearchedBookIsOnLibrary() {
    // given
    Library library = new Library();
    Book book = new Book();
    UUID bookId = book.getId();

    // when
    library.addLibraryItem(book);
    Book got = (Book) library.searchItemById(bookId.toString());

    // then
    Assertions.assertThat(got).isEqualTo(book);
  }

  @Test
  public void shouldFindBookIfBookIsOnLibrary() {
    // given
    Library library = new Library();
    Book book = new Book();
    String bookId = book.getId().toString();

    // when
    library.addLibraryItem(book);

    // then
    Assertions.assertThat(library.isItemOnLibrary(bookId)).isEqualTo(true);
  }

  @Test
  public void shouldNotFindBookIfBookIsNotLibrary() {
    // given
    Library library = new Library();
    String bookId = UUID.randomUUID().toString();

    // when
    Boolean got = library.isItemOnLibrary(bookId);

    // then
    Assertions.assertThat(got).isEqualTo(false);
  }
}
