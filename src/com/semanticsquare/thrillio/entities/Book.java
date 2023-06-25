package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.BookGenre;

import java.util.Arrays;

public class Book extends  Bookmark{
    private int PublicationYear;
    private String Publisher;
    private String [] authors;
    private String genre;
    private double amazonRating;

    public int getPublicationYear() {
        return PublicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        PublicationYear = publicationYear;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getAmazonRating() {
        return amazonRating;
    }

    public void setAmazonRating(double amazonRating) {
        this.amazonRating = amazonRating;
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if(genre.equals(BookGenre.PHILOSOPHY) || genre.equals(BookGenre.SELF_HELP))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "PublicationYear=" + PublicationYear +
                ", Publisher='" + Publisher + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", genre='" + genre + '\'' +
                ", amazonRating=" + amazonRating +
                '}';
    }
}
