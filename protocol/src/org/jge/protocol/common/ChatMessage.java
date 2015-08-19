package org.jge.protocol.common;

import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ChatMessage extends Packet {
    private String message;

    public ChatMessage() {
        super(PacketType.CHAT);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getUser().getUsername() + ": " + message;
    }
}
