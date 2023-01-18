package service;

import data.UserDao;
import model.users.Customer;
import model.users.User;

import java.io.IOException;

public class Register {
    private static UserDao userDao = new UserDao();
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String birth;
    private String cardID;

    public Register(String username, String firstName, String lastName, String password, String birth, String cardID) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.cardID = cardID;
    }

    public User register() throws IOException {
        User u = new Customer(username, firstName, lastName, password, birth, cardID);
        userDao.save(u);
        return u;
    }

    /**
     * @return valid length of password should > 8
     */
    public boolean passwordLengthOk() {
        return password.length() >= 8;
    }
    public boolean containNum() {
        for (char c : password.toCharArray()) {
            if (c <= '9' && c >= '0') {
                return true;
            }
        }
        return false;
    }
    public boolean containLetter() {
        for (char c : password.toCharArray()) {
            if (c <= 'z' && c >= 'a') {
                return true;
            }
        }
        return false;
    }
    public boolean containSpecial() {
        for (char c : password.toCharArray()) {
            if (helper(c)) {
                return true;
            }
        }
        return false;
    }
    private boolean helper(char c) {
        char[] array= new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '=', '_', '~', '`', '[', ']', '\\', '{', '}', '|', ':', ';', '"', '<', '>', ',', '.', '?', '/'};
        for (char cc : array) {
            if (cc == c) {
                return true;
            }
        }
        return false;
    }
}
