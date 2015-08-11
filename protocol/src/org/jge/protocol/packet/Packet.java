package org.jge.protocol.packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class Packet {
    private PacketType PacketType;
    private Object attachment;

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

    public enum PacketType {
        CONNECT,
        DISCONNECT,
        REGISTER
    }
}
