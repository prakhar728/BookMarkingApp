package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Book;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.partner.Shareable;

import javax.xml.crypto.Data;
import java.util.List;

public class View {
    public static void browse(User user, List<List<Bookmark>> bookmarks) {
        System.out.println("\n" + user.getEmail() + "is browsing items ...");

        int bookmarkCount = 0;
        for (List<Bookmark> bookmarklist : bookmarks) {
            for (Bookmark bookmark : bookmarklist) {
                // BOOKMARKING
                if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if (isBookmarked) {
                        bookmarkCount++;
                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);
                        System.out.println("New Item Bookmarked --" + bookmark);
                    }

                }
                if (user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {

                    //MARKING AS KID FRIENDLY
                    if (bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
                        KidFriendlyStatus isKidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                        if(!isKidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){
                            BookmarkController.getInstance().setKidFriendlyStatus(user,isKidFriendlyStatus,bookmark);
                        }
                    }

                    //SHARING FUNCTIONALITY
                    if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable){
                        boolean isShared = getShareDecision();
                        if(isShared){
                            BookmarkController.getInstance().share(user,bookmark);
                        }
                    }

                }
            }

        }

    }

    //TODO: BELOW METHODS SIMULATE USER INPUT. AFTER IO, WE TAKE INPUT VIA CONSOLE
    private static boolean getShareDecision() {
        return Math.random() < 0.5;

    }

    private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
        double randomVal = Math.random();
        return randomVal < 0.4 ? KidFriendlyStatus.APPROVED : (randomVal>= 0.4 && randomVal < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5;
    }
    /*public static void bookmark(User user, Bookmark [][] bookmarks){
        System.out.println("\n"+user.getEmail()+"is bookmarking");
        for(int i=0;i<DataStore.USER_BOOKMARK_LIMIT;i++){
            int typeOffSet = (int)(Math.random() *DataStore.BOOKMARK_TYPES_CONSTANT);
            int bookmarkOffSet = (int)(Math.random() *DataStore.BOOKMARK_COUNT_PER_TYPE);

            Bookmark bookmark = bookmarks[typeOffSet][bookmarkOffSet];
            BookmarkController.getInstance().saveUserBookmark(user,bookmark);

            System.out.println(bookmark);
        }
    }*/
}
