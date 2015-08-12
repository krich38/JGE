package org.jge.protocol.packet;

import com.esotericsoftware.kryonet.Connection;
import org.jge.model.User;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Packet {
    private PacketType PacketType;
    private Object attachment;
    private User user;
    private Connection connection;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PacketType getPacketType() {
        return PacketType;
    }

    public Packet(PacketType PacketType) {

        this.PacketType = PacketType;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public enum PacketType {
        CONNECT,
        DISCONNECT,
        REGISTER, UPDATE
    }
}
