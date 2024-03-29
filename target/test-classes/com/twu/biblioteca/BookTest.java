package com.twu.biblioteca;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

  Book book;

  @Before
  public void setUp() {
    book = new Book("The Catcher in the Rye", "J.D. Salinger", 1951);
  }

  @Test
  public void shouldReturnTitleAuthorIDAndPublicationYearWhenBookToStringIsCalled() {
    // given
    String year = String.valueOf(this.book.getPublicationYear());

    // when
    String got = book.toString();

    // then
    Assertions.assertThat(got).contains(this.book.getAuthor());
    Assertions.assertThat(got).contains(this.book.getTitle());
    Assertions.assertThat(got).contains(this.book.getId().toString());
    Assertions.assertThat(got).contains(year);
  }

  @Test
  public void bookShouldHaveAnUniqueId() {
    Assertions.assertThat(book.getId()).isNotNull();
  }

  @Test
  public void shouldSetBookStatusToAvailableWhenBookIsReturned(){
    // given
    book.checkout();

    // when
    book.returnToLibrary();

    // then
    Assertions.assertThat(book.isAvailable()).isEqualTo(true);
  }

}
