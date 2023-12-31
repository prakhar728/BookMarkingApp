package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import com.semanticsquare.thrillio.manager.UserManager;
import com.semanticsquare.thrillio.util.IOUtil;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static final int USER_BOOKMARK_LIMIT = 5;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int TOTAL_USER_COUNT = 5;

    private static List<User> users = new ArrayList<>();
    public static List<User> getUsers() {
        return users;
    }

    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    public static List<List<Bookmark>> getBookmarks() {
        return bookmarks;
    }

    private static UserBookmark[] userBookmarks = new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];
    private static int bookmarkIndex;

    public static void loadData() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false","root","passworD123@");
            Statement stmt = conn.createStatement();
        ){
                    loadUsers(stmt);
        loadWebLinks(stmt);
        loadMovies(stmt);
        loadBooks(stmt);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private static void loadUsers(Statement smt) throws SQLException {
        String query = "Select * from User;";
        ResultSet rs = smt.executeQuery(query);
       while (rs.next()) {
           long id =rs.getLong("id");
           String email = rs.getString("email");
           String password = rs.getString("password");
           String firstName = rs.getString("first_name");
           String lastName = rs.getString("last_name");
           int gender_id  = rs.getInt("gender_id");
           Gender gender = Gender.values()[gender_id];
           int user_type_id  = rs.getInt("user_type_id");
           UserType userType  = UserType.values()[user_type_id];

            User user = UserManager.getInstance().createUser(id,email,password,firstName,lastName, gender,userType);
            users.add(user);
        }
    }

    private static void loadWebLinks(Statement stmt) throws SQLException {
        String query = "SELECT * FROM WebLink";
        ResultSet rs = stmt.executeQuery(query);

        List<Bookmark> bookmarkList = new ArrayList<>();
        while (rs.next()) {
            long id =rs.getLong("id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            String host = rs.getString("host");
            Bookmark bookmark = BookMarkManager.getInstance().createWebLink(id,title,url,host/*, values[4]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadMovies(Statement stmt) throws SQLException {
        String query = "Select m.id, title, release_year, GROUP_CONCAT(DISTINCT a.name SEPARATOR ',') AS cast, GROUP_CONCAT(DISTINCT d.name SEPARATOR ',') AS directors, movie_genre_id, imdb_rating from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md where m.id = ma.movie_id and ma.actor_id = a.id and m.id = md.movie_id and md.director_id = d.id group by m.id";
        ResultSet rs = stmt.executeQuery(query);
        List<Bookmark> bookmarkList = new ArrayList<>();

        while (rs.next()) {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            int releaseYear = rs.getInt("release_year");
            String[] cast = rs.getString("cast").split(",");
            String[] directors = rs.getString("directors").split(",");
            int genre_id = rs.getInt("movie_genre_id");
            MovieGenre genre = MovieGenre.values()[genre_id];
            double imdbRating = rs.getDouble("imdb_rating");
            Bookmark bookmark = BookMarkManager.getInstance().createMovie(id, title,releaseYear,cast, directors, genre ,imdbRating/*, values[7]*/);
            bookmarkList.add(bookmark);

        }
        bookmarks.add(bookmarkList);
    }

    private static void loadBooks(Statement stmt) throws SQLException {
//        List<String> data = new ArrayList<>();
//
//        IOUtil.read(data, "Book");
        String query = "Select b.id, title,publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors,book_genre_id, amazon_rating, created_date from Book b, Publisher p, Author a, Book_Author ba where b.publisher_id = p.id and b.id = ba.book_id and ba.author_id = a.id group by b.id;";
        ResultSet rs = stmt.executeQuery(query);
        List<Bookmark> bookmarkList = new ArrayList<>();

        while (rs.next()) {
            long id =rs.getLong("id");
            String title = rs.getString("title");
            int publicationYear  = rs.getInt("publication_year");
            String publisher = rs.getString("name");
            String[] authors = rs.getString("authors").split(",");
            int genre_id  = rs.getInt("book_genre_id");
            BookGenre genre = BookGenre.values()[genre_id];
            double amazonRating = rs.getDouble("amazon_rating");

            Date createdDate = rs.getDate("created_date");
            System.out.println("CreatedDate:" + createdDate);
            Timestamp timestamp = rs.getTimestamp(8);
            System.out.println("Timestamp :" + timestamp);
            System.out.println("LocalDateTime:" + timestamp.toLocalDateTime());

            System.out.println("id: " + id +",title" + title + ", publication year");
            Bookmark bookmark  = BookMarkManager.getInstance().createBook(id,title, publicationYear, publisher,authors, genre,amazonRating/*, values[7]*/);
            bookmarkList.add(bookmark);

        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}