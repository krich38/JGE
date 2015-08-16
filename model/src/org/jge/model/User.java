package org.jge.model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class User {
    private Id<User> id;
    private String username;
    private String password;

    public User(String username, String password) {
        this(null, username, password);
    }

    public User() {

    }

    public User(Id<User> id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = User.encrypt(password);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User) o;

            return user.password.equals(this.password) && user.username.equals(this.username);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + id + "," + username + "] ";
    }

    public static String encrypt(String pw) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pw, salt);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
