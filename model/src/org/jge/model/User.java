package org.jge.model;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this(null, username, password);
    }

    public User(Id<User> id, String username, String password) {
        this.username = username;
        this.password = User.encrypt(password);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof User) {
            User user = (User)o;

            return user.password.equals(this.password) && user.username.equals(this.username);
        }
        return false;
    }
    public static String encrypt(String pw) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pw, salt);
    }
}
