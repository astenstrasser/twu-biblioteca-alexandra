package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {

  private List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    this.books.add(book);
  }

  public List<Book> getBooks() {
    return this.books;
  }

  public List<Book> getAvailableBooks() {

    List<Book> availableBooks = new ArrayList<Book>();

    for (Book book : this.books) {
      if (book.isAvailable() == true) {
        availableBooks.add(book);
      }
    }

    return availableBooks;
  }

  public void checkoutBook(String givenBookId) {

    boolean isBookOnLibrary = false;
    Book book = null;
    for (Book b : this.books){
      if ((b.getId().toString()).equals(givenBookId)){
        isBookOnLibrary = true;
        book = b;
      }

    }
    if (isBookOnLibrary == true){
      book.checkout();
    } else {
      throw new NoSuchElementException();
    }



  }
}
