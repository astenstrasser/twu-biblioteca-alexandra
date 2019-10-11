package com.twu.biblioteca;

import java.util.UUID;

public class Movie implements LibraryItem {

  String title;
  String director;
  int publicationYear;
  Double rating;
  boolean isAvailable;
  UUID id;

  public Movie(String title, String director, int publicationYear, Double rating) {
    this.title = title;
    this.director = director;
    this.rating = rating;
    this.publicationYear = publicationYear;
    this.isAvailable = true;
    this.id = UUID.randomUUID();
  }

    @Override
    public String toString() {
        return String.format(
                "[ID: %s]      %s - %s (%s)", this.id, this.director, this.title, this.publicationYear);
    }

    @Override
  public String getTitle() {
    return null;
  }

  @Override
  public int getPublicationYear() {
    return this.publicationYear;
  }

  @Override
  public String getAuthor() {
    return this.director;
  }

  @Override
  public UUID getId() {
    return this.id;
  }

  @Override
  public boolean isAvailable() {
    return this.isAvailable;
  }

  @Override
  public void checkout() {
    this.isAvailable = false;
  }

  @Override
  public void returnToLibrary() {
    this.isAvailable = true;
  }
}
