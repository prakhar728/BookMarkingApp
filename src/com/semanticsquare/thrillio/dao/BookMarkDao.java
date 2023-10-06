package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

import java.util.ArrayList;
import java.util.List;

public class BookMarkDao {
    public List<List<Bookmark>> getBookmarks(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark) {
        DataStore.add(userBookmark);
    }

    public List<WebLink> getAllWebLinks(){
        List<WebLink > result = new ArrayList<>();
        List<List<Bookmark>> bookmarks = DataStore.getBookmarks();

        List<Bookmark> allWebLinks = bookmarks.get(0);

        for(Bookmark bookmark:allWebLinks){
            result.add((WebLink) bookmark);
        }

        return result;
    }
    public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus){
        List<WebLink> result = new ArrayList<>();
        List<WebLink> allWebLinks = getAllWebLinks();

        for(WebLink webLink:allWebLinks){
            if(webLink.getDownloadStatus().equals(downloadStatus)){
                result.add(webLink);
            }
        }
        return result;
    }
}
