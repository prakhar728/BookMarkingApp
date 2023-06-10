package com.semanticsquare.thrillio.manager;

import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Movie;
import com.semanticsquare.thrillio.entities.WebLink;

public class BookMarkManager {
    private static BookMarkManager instance = new BookMarkManager();

    private BookMarkManager(){}

    public static BookMarkManager getInstance(){
        return instance;
    }

    public WebLink createWebLink(long id, String title,String url,String host){
        WebLink weblink = new WebLink();
        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setHost(host);
        weblink.setUrl(url);
        return weblink;
    }
    public Book createBook(long id,String title,int publicationYear,String publisher,String[] authors,double amazonRating){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setAmazonRating(amazonRating);

        return book;
    }
    public Movie createMovie(long id, String title, int releaseYear,String[]cast,String[] directors,String genre,double imdbRating){
        Movie movie= new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }
}
