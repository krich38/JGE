package org.jge.protocol.serverstatus;

import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class AdminEvent extends Packet {
    public enum EventType {
        KICK_ALL, CLOSE_SERVER, SHUT_DOWN, OPEN_SERVER, SAVE_ALL, DIAGNOSTICS, BROADCAST, REQUEST_REFRESH

    }

    public AdminEvent() {
        super(PacketType.ADMIN_EVENT);
    }

    public void setType(EventType type) {
        setAttachment(type);
    }

    public EventType getType() {
        return (EventType) getAttachment();
    }
}
