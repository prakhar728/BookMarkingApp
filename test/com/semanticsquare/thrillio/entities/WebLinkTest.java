package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.manager.BookMarkManager;

import static com.semanticsquare.thrillio.DataStore.getBookmarks;
import static org.junit.jupiter.api.Assertions.*;

class WebLinkTest {

    @org.junit.jupiter.api.Test
    void isKidFriendlyEligible() {

        //TEST 1 TESTING KEYWORDS IN URL --FALSE
        WebLink webLink = BookMarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",	"http://www.javaworld.com");
        boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible,"For porn in URL, must return false");
        //TEST 2 TESTING KEYWORDS IN TITLE --FALSE
         webLink = BookMarkManager.getInstance().createWebLink(2000,	"Taming porn Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");
         isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible,"For porn in title, must return false");

        //TEST 3 TESTING KEYWORDS IN host --FALSE
        webLink = BookMarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworldadult.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible,"For adult in host, must return false");
        //TEST 4 TESTING KEYWORDS IN URL, but not in host --TRUE
        webLink = BookMarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-adult--part-2.html",	"http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertTrue(isKidFriendlyEligible,"For adult in url but not host, must return true");
        //TEST 5 TESTING KEYWORDS IN title only --TRUE
        webLink = BookMarkManager.getInstance().createWebLink(2000,	"Taming adult Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");
        isKidFriendlyEligible = webLink.isKidFriendlyEligible();

        assertTrue(isKidFriendlyEligible,"For adult in title, must return false");
    }
}