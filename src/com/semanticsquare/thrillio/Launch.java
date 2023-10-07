package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.bgjobs.WebPageDownloaderTask;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import com.semanticsquare.thrillio.manager.UserManager;

import java.util.List;

public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;
    private static void loadData(){
        System.out.println("1. Loading Data ...");
        DataStore.loadData();

        users= UserManager.getInstance().getUsers();
        bookmarks = BookMarkManager.getInstance().getBookmarks();
        System.out.println("Printing Data ..");

        printUserData();
        printBookMarkData();
    }

    private static void printUserData(){
        for(User user:users) {
            System.out.println(user);
        }
    }

    private static void printBookMarkData(){
        for(List<Bookmark> bookmarklist:bookmarks){
            for (Bookmark bk:bookmarklist
                 ) {
                System.out.println(bk);
            }
        }
    }
    private static void startBookMarking(){
        System.out.println("\n 2. Bookmarking ..");
        for(User user:users){
            View. browse(user,bookmarks);
        }
    }

    public static void main(String [] args){
        loadData();
        startBookMarking();

        runDownloaderJob();
    }

    private static void runDownloaderJob(){
        WebPageDownloaderTask task = new WebPageDownloaderTask(true);
        (new Thread(task)).start();

    }

}
