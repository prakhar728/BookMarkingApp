package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.BookGenre;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.MovieGenre;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.manager.BookMarkManager;
import com.semanticsquare.thrillio.manager.UserManager;

public class DataStore {
    public static final int TOTAL_USER_COUNT = 5;

    public static final int BOOKMARK_TYPES_CONSTANT = 3;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;
    private static int bookmarkIndex=0;
    private static User [] users = new User[TOTAL_USER_COUNT];


    private static Bookmark [][] bookmarks = new Bookmark[BOOKMARK_TYPES_CONSTANT][BOOKMARK_COUNT_PER_TYPE];
    public static final int USER_BOOKMARK_LIMIT = 5;
    private static UserBookmark[] userBookmarks= new UserBookmark[TOTAL_USER_COUNT * USER_BOOKMARK_LIMIT];

    public static void loadData() {
    loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }
    private static void loadUsers(){
        getUsers()[0] = UserManager.getInstance().createUser(1000,	"user0@semanticsquare.com",	"test",	"John",	"M", Gender.MALE,UserType.USER);
        getUsers()[1] = UserManager.getInstance().createUser(1001,	"user1@semanticsquare.com",	"test",	"Sam",	"M",	Gender.MALE	,UserType.USER);
        getUsers()[2] = UserManager.getInstance().createUser(1002,	"user2@semanticsquare.com",	"test"	,"Anita",	"M",Gender.MALE, UserType.EDITOR);
        getUsers()[3] = UserManager.getInstance().createUser(1003,	"user3@semanticsquare.com",	"test",	"Sara",	"M",	Gender.FEMALE,UserType.EDITOR);
        getUsers()[4] = UserManager.getInstance().createUser(1004,	"user4@semanticsquare.com",	"test",	"Dheeru",	"M",Gender.MALE,UserType.CHIEF_EDITOR);
    }
    private static void loadWebLinks(){
        getBookmarks()[0][0]= BookMarkManager.getInstance().createWebLink(2000,	"Taming Tiger, Part 2",	"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",	"http://www.javaworld.com");
        getBookmarks()[0][1]= BookMarkManager.getInstance().createWebLink(2001,"	How do I import a pre-existing Java project into Eclipse and get up and running?","	http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",	"http://www.stackoverflow.com");
        getBookmarks()[0][2]= BookMarkManager.getInstance().createWebLink(2002,	"Interface vs Abstract Class",	"http://mindprod.com/jgloss/interfacevsabstract.html",	"http://mindprod.com"
);
        getBookmarks()[0][3]= BookMarkManager.getInstance().createWebLink(2003,	"NIO tutorial by Greg Travis",	"http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf",	"http://cs.brown.edu");
        getBookmarks()[0][4]= BookMarkManager.getInstance().createWebLink(2004,"	Virtual Hosting and Tomcat",	"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html"	,"http://tomcat.apache.org	");
    }

    private static void loadMovies() {
    getBookmarks()[1][0]=BookMarkManager.getInstance().createMovie(
            3000	,",Citizen Kane",	1941, new String[]{
                " Orson Welles","Joseph Cotten"},new String[]{"Orson Welles"
            }, MovieGenre.CLASSICS,	8.5	 );
        getBookmarks()[1][2]=BookMarkManager.getInstance().createMovie(
                3002,	"A Touch of Greatness",	2004,new String[]{"Albert Cullum"}, new String[]{"Leslie Sullivan"}, MovieGenre.DOCUMENTARIES,	7.3 );
        getBookmarks()[1][1]=BookMarkManager.getInstance().createMovie(
                3001	,"The Grapes of Wrath",	1940, new String[]{
                        " Henry Fonda","Jan e Darwell"},new String[]{"John Ford"
                }, MovieGenre.CLASSICS,	8.2		 );
        getBookmarks()[1][3]=BookMarkManager.getInstance().createMovie(
                3003,	"The Big Bang Theory",	2007,	new String[]{"Kaley Cuoco","Jim Parsons"}, new String[]{"Chuck Lorre","Bill Prady"}, MovieGenre.TV_SHOWS,	8.7 );
        getBookmarks()[1][4]=BookMarkManager.getInstance().createMovie(
                3004	,"Ikiru"	,1952, new String[]{"Takashi Shimura","Minoru Chiaki"}, new String[]{"Akira Kurosawa"}, MovieGenre.FOREIGN_MOVIES,	8.4
 );
    }

    private static void loadBooks(){
        getBookmarks()[2][0] = BookMarkManager.getInstance().createBook(
                4000,	"Walden",	1854,"Wilder Publications",new String[]{"Henry David Thoreau"}, BookGenre.PHILOSOPHY, 4.3
        );
        getBookmarks()[2][1] = BookMarkManager.getInstance().createBook(
                4001,	"Self-Reliance and Other Essays",	1993,"Dover Publications",new String[]{"Ralph Waldo Emerson"},BookGenre.PHILOSOPHY,	4.5

        );
        getBookmarks()[2][2] = BookMarkManager.getInstance().createBook(
                4002, "Light From Many Lamps",	1988,"Touchstone	Lillian", 	new String[]{"Eichler Watson"},BookGenre.PHILOSOPHY,	5.0
        );
        getBookmarks()[2][3] = BookMarkManager.getInstance().createBook(
                4003	,"Head First Design Patterns",	2004,	"O'Reilly Media" , new String[]{"Eric Freeman","Bert Bates","Kathy Sierra","Elisabeth Robson"},BookGenre.TECHNICAL,	4.5

        );
        getBookmarks()[2][4] = BookMarkManager.getInstance().createBook(
                4004	,"Effective Java Programming Language Guide",	2007,"Prentice Hall", new String[]{
                        "Joshua Bloch"
                }, BookGenre.TECHNICAL,	4.9

        );
    }

    public static User[] getUsers() {
        return users;
    }

    public static Bookmark[][] getBookmarks() {
        return bookmarks;
    }

    public static void add(UserBookmark userBookmark) {
        userBookmarks[bookmarkIndex] = userBookmark;
        bookmarkIndex++;
    }
}
