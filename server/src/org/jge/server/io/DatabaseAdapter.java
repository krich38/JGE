package org.jge.server.io;

import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.model.world.Waypoint;

import java.sql.*;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class DatabaseAdapter {


    private static Connection connection;
    private static Statement statement;

    public static boolean establishConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:jge.db");
statement = connection.createStatement();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static Id<Entity> getId(User user) {
        String result = "select id,password from players where players.username=\""+user.getUsername()+"\"";
        Id<Entity> id = null;
        try {
            statement.execute(result);
ResultSet rs = statement.getResultSet();
            if(rs.next()) {

                if(rs.getString("password").equals(user.getPassword())) {
                    id = Id.asOf(rs.getString("id"));
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    public static void fillPlayerEncap(PlayerEncap pe) {
        String query = "select * from players where players.id=\""+pe.getId().getValue()+"\"";
        try {
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            if(rs.next()) {
                int x = rs.getInt("pos_x");
                int y = rs.getInt("pos_y");
                Waypoint w = new Waypoint(x,y);
                pe.setWaypoint(w);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updatePlayer(PlayerEncap pe) throws SQLException {
        String query = "update players SET pos_x = " + pe.getWaypoint().getX() + ", pos_y = " + pe.getWaypoint().getY() + " WHERE id=\"" + pe.getId().getValue() + "\"";
        statement.execute(query);

    }
}
