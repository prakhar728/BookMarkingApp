package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import com.semanticsquare.thrillio.manager.UserManager;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;
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
        for(Bookmark[] bookmarklist:bookmarks){
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
    }

}
