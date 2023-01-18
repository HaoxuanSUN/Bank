package service;

import data.UserDao;
import model.users.User;

public class SignIn {
    private static UserDao userDao = new UserDao();
    private String username;
    private String password;
    public SignIn(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User signIn() {
        if (userDao.getCustomerByName(username) != null && userDao.getCustomerByName(username).getPassword().equals(password)) {
            return userDao.getCustomerByName(username);
        }
        return null;
    }
}
