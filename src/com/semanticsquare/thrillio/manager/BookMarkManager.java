package com.semanticsquare.thrillio.manager;

import com.semanticsquare.thrillio.dao.BookMarkDao;
import com.semanticsquare.thrillio.entities.*;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public class BookMarkManager {
    private static BookMarkManager instance = new BookMarkManager();
    private static BookMarkDao dao = new BookMarkDao();
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
    public Book createBook(long id,String title,int publicationYear,String publisher,String[] authors,String genre,double amazonRating){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setAmazonRating(amazonRating);
        book.setGenre(genre);
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
    public List<List<Bookmark>> getBookmarks(){
        return dao.getBookmarks();
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setUser(user);
        userBookmark.setBookmark(bookmark);
        if(bookmark instanceof WebLink){
            try{
                String url = ((WebLink)bookmark).getUrl();
                if(!url.endsWith(".pdf")){
                    String webPage = HttpConnect.download(((WebLink)bookmark).getUrl());
                    System.out.println("Not writing?");

                    if(webPage !=null){
                        System.out.println("writing?");
                        IOUtil.write(webPage,bookmark.getId());
                    }
                }
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (URISyntaxException e){
                e.printStackTrace();
            }
        }

        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, String isKidFriendlyStatus, Bookmark bookmark) {
        bookmark.setKidFriendlyStatus(isKidFriendlyStatus);
        bookmark.setKidFriendlyMarkedBy(user);
        System.out.println("Marked By:" + user.getEmail());
        System.out.println("Kid Friendly Status:" + isKidFriendlyStatus+ ","+bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        bookmark.setSharedBy(user);
        System.out.println("Data to be shared :");
        if(bookmark instanceof Book){
            System.out.println(((Book)bookmark).getItemData());
        }
        else if( bookmark instanceof WebLink){
            System.out.println(((WebLink)bookmark).getItemData());
        }
    }
}
