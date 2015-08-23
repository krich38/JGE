package org.jge.model;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class User {
    private Id<User> id;
    private String username;
    private String password;
    private AccessRights rights;

    public AccessRights getRights() {
        return rights;
    }

    public void setRights(AccessRights rights) {
        this.rights = rights;
    }

    public enum AccessRights {
        ADMINISTRATOR,
        MODERATOR,
        PLAYER;

        public static boolean hasAccess(AccessRights rights, AccessRights required) {
            return rights.ordinal() >= required.ordinal();
        }
    }

    public User(String username, String password) {
        this(null, username, password);
    }

    public User() {

    }

    public User(Id<User> id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = User.encrypt(password);
        this.rights = AccessRights.PLAYER;
        //System.out.println(User.encrypt("lol"));
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
        md.reset();
        md.update(pw.getBytes(), 0, pw.length());
        String md5 = new BigInteger(1, md.digest()).toString(16);
        return md5;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    private static MessageDigest md;

    static {
        try {
            User.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
