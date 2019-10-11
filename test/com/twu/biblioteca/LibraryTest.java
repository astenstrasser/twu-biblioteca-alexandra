package com.twu.biblioteca;

import org.assertj.core.api.Assertions;
import org.junit.Before;
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

  private static  Book bookOne = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
  private static  Book bookTwo = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);

  private static  Movie movieOne = new Movie("Fight Club", "David Fincher", 1999,  8.8);
  private static  Movie movieTwo =  new Movie("The Matrix",  "Lana Wachowski and Lilly Wachowski", 1999,  8.7);

  private Library library;

  @Before
  public void setUp() {
    library = new Library();
  }

  @Test
  public void AddBookToLibrary() {
    // given

    // when
    library.addLibraryItem(bookOne);

    // then
    assertThat(library.getBooks()).containsOnly(bookOne);
  }

  @Test
  public void shouldReturnListOfAllBooksWhenGetBooks() {
    // given

    // when
    library.addLibraryItem(bookOne);
    library.addLibraryItem(bookTwo);
    List<Book> books = library.getBooks();

    // then
    assertThat(books.get(0).getAuthor()).isEqualTo(bookOne.getAuthor());
    assertThat(books.get(1).getAuthor()).isEqualTo(bookTwo.getAuthor());
    assertThat(books.size()).isEqualTo(2);
  }

  @Test
  public void shouldReturnJustAvailableBooksWhenGetAvailableBooks() {
    // given
    library.addLibraryItem(bookOne);
    library.addLibraryItem(bookTwo);

    // when
    List<Book> got = library.getAvailableBooks();

    // then
    got.forEach(book -> Assertions.assertThat(book.isAvailable()).isEqualTo(true));
  }

  @Spy private Book mockBook;

  @Test
  public void shouldCheckoutBookWhenBookIsAvailableOnLibrary() {
    // given
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
    String nonExistentId = "NonExistentId012345";

    // when
    Book got = (Book) library.searchItemById(nonExistentId);

    // then
    Assertions.assertThat(got).isEqualTo(null);
  }

  @Test
  public void shouldReturnBookWhenSearchedBookIsOnLibrary() {
    // given
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
    String bookId = UUID.randomUUID().toString();

    // when
    Boolean got = library.isItemOnLibrary(bookId);

    // then
    Assertions.assertThat(got).isEqualTo(false);
  }

  @Test
  public void shouldBeAbleToAddMoviesToLibrary() {
    // given

    // when
    library.addLibraryItem(movieOne);

    // then
    Assertions.assertThat(library.getMovies().size()).isEqualTo(1);
    Assertions.assertThat(library.getLibraryItems().size()).isEqualTo(1);
  }

  @Test
  public void shouldCheckoutMovie(){
    // given

    // when
    library.addLibraryItem(movieOne);
    String id = movieOne.getId().toString();
    library.checkoutItem(id);

    // then
    Assertions.assertThat(movieOne.isAvailable()).isEqualTo(false);
  }
}
