package com.semanticsquare.thrillio.controllers;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.manager.BookMarkManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();
    private BookmarkController(){}

    public static BookmarkController getInstance(){
        return  instance;
    }

    public void saveUserBookmark(User user, Bookmark bookmark) {
        BookMarkManager.getInstance().saveUserBookmark(user,bookmark);
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus isKidFriendlyStatus, Bookmark bookmark) {
        BookMarkManager.getInstance().setKidFriendlyStatus(user,isKidFriendlyStatus,bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        BookMarkManager.getInstance().share(user,bookmark);
    }
}
