package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.partner.Shareable;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Book extends  Bookmark implements Shareable {
    private int PublicationYear;
    private String Publisher;
    private String [] authors;
    private BookGenre genre;
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

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
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

    @Override
    public String getItemData() {
        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
        builder.append("<type>Book</type>");
        builder.append("<title>").append(getTitle()).append("</title>");
        builder.append("<authors>").append(StringUtils.join(authors,",")).append("</authors>");
        builder.append("<publisher>").append(getPublisher()).append("</publisher>");
        builder.append("<publicationYear>").append(getPublicationYear()).append("</publicationYear>");
        builder.append("<genre>").append(getGenre()).append("</genre>");
        builder.append("<amazonRating>").append(getAmazonRating()).append("</amazonRating>");
        builder.append("</item>");
        return builder.toString();
    }
}
