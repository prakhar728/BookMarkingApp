package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void isKidFriendlyEligible() {
        Book book = BookMarkManager.getInstance().createBook(
                4000,	"Walden",	1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3
        );
        boolean isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible,"For philosophy genre should return false");


         book = BookMarkManager.getInstance().createBook(
                4000,	"Walden",	1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.SELF_HELP, 4.3
        );
         isKidFriendlyEligible = book.isKidFriendlyEligible();
        assertFalse(isKidFriendlyEligible,"For Self Help genre should return false");
    }
}