package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;

import java.util.List;

public class BookMarkDao {
    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }
}
