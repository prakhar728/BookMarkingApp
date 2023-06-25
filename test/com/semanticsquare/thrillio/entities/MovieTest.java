package com.semanticsquare.thrillio.entities;

import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void isKidFriendlyEligible() {
        Movie movie =BookMarkManager.getInstance().createMovie(
                3000	,",Citizen Kane",	1941, new String[]{
                        " Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"
                }, MovieGenre.HORROR,	8.5	 );
        boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible,"For Horror Genre - Should return false");

         movie =BookMarkManager.getInstance().createMovie(
                3000	,",Citizen Kane",	1941, new String[]{
                        " Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"
                }, MovieGenre.THRILLERS,	8.5	 );
         isKidFriendlyEligible = movie.isKidFriendlyEligible();

        assertFalse(isKidFriendlyEligible,"For Thrillers Genre - Should return false");


    }
}