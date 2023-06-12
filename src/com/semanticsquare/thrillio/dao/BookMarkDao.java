package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;

public class BookMarkDao {
    public Bookmark[][] getBookmarks(){
        return DataStore.getBookmarks();
    }
}
