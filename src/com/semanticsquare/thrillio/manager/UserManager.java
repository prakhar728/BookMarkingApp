package com.semanticsquare.thrillio.manager;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.constants.Gender;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.dao.UserDao;
import com.semanticsquare.thrillio.entities.User;

import java.util.List;

public class UserManager {
    private UserManager (){}
    private static UserManager instance = new UserManager();
    private static UserDao dao = new UserDao();
    public static UserManager getInstance(){
        return instance;
    }

    public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender, UserType userType){
User user = new User();
user.setId(id);
user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setGender(gender);
        user.setUserType(userType);
        return user;
    }

    public List<User> getUsers(){
        return dao.getUsers();
    }

}
