package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Library {

  private List<LibraryItem> libraryItems = new ArrayList<>();

  public void addLibraryItem(LibraryItem libraryItem) {
    this.libraryItems.add(libraryItem);
  }

  public List<LibraryItem> getLibraryItems() {
    return this.libraryItems;
  }

  public List<Book> getBooks() {
    List<Book> books = null;

    books =
        this.libraryItems.stream()
            .filter(item -> item instanceof Book)
            .map(item -> (Book) item)
            .collect(Collectors.toList());
    return books;
  }

  public List<Movie> getMovies() {
    List<Movie> movies = null;

    movies =
        this.libraryItems.stream()
            .filter(item -> item instanceof Movie)
            .map(item -> (Movie) item)
            .collect(Collectors.toList());

    return movies;
  }

  public List<Book> getAvailableBooks() {
    return getBooks().stream().filter(Book::isAvailable).collect(toList());
  }

  public List<Movie> getAvailableMovies() {
    return getMovies().stream().filter(Movie::isAvailable).collect(toList());
  }

  public void checkoutItem(String id) {
    LibraryItem libraryItem = null;
    if (isItemOnLibrary(id)) {
      libraryItem = searchItemById(id);
    }

    if (isItemOnLibrary(id) && libraryItem.isAvailable()) {
      libraryItem.checkout();
    } else {
      throw new NoSuchElementException();
    }
  }

  public void returnItem(String id) {
    LibraryItem item = null;
    if (isItemOnLibrary(id)) {
      item = searchItemById(id);
    }

    if (item != null && !item.isAvailable()) {
      item.returnToLibrary();
    } else {
      throw new NoSuchElementException();
    }
  }

  public LibraryItem searchItemById(String itemId) {
    return this.libraryItems.stream()
        .filter(b -> b.getId().toString().equals(itemId))
        .findAny()
        .orElse(null);
  }

  public boolean isItemOnLibrary(String id) {
    boolean isItemOnLibrary =
        !(this.libraryItems.stream().filter(b -> b.getId().toString().equals(id)).count() == 0);

    if (isItemOnLibrary) {
      return true;
    } else {
      return false;
    }
  }

}
