package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import com.semanticsquare.thrillio.manager.UserManager;
import com.semanticsquare.thrillio.util.IOUtil;

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
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    private static void loadUsers() {
        List<String> data = new ArrayList<>();
        IOUtil.read(data, "User");
        for (String row : data) {
            String[] values = row.split("\t");

            Gender gender = Gender.MALE;
            if (values[5].equals("f")) {
                gender = Gender.FEMALE;
            } else if (values[5].equals("t")) {
                gender = Gender.TRANSGENDER;
            }
            User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3], values[4], gender, values[6]);
            users.add(user);
        }
    }

    private static void loadWebLinks() {
        List<String> data = new ArrayList<>();

        IOUtil.read(data, "WebLink");
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] values = row.split("\t");
            Bookmark bookmark = BookMarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1], values[2], values[3]/*, values[4]*/);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    private static void loadMovies() {
        List<String> data = new ArrayList<>();

        IOUtil.read(data, "Movie");
        List<Bookmark> bookmarkList = new ArrayList<>();

        for (String row : data) {
            String[] values = row.split("\t");
            String[] cast = values[3].split(",");
            String[] directors = values[4].split(",");
            Bookmark bookmark = BookMarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1],  Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5].toUpperCase()) , Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);

        }
        bookmarks.add(bookmarkList);
    }

    private static void loadBooks() {
        List<String> data = new ArrayList<>();

        IOUtil.read(data, "Book");
        List<Bookmark> bookmarkList = new ArrayList<>();

        for (String row : data) {
            String[] values = row.split("\t");
            String[] authors = values[4].split(",");
            Bookmark bookmark  = BookMarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1], Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5].toUpperCase()), Double.parseDouble(values[6])/*, values[7]*/);
            bookmarkList.add(bookmark);

        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}