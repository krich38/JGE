package org.jge.protocol.packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Ping extends Packet {
    public Ping() {
        super(PacketType.PING);
    }

    public static class Pong extends Packet {
        public Pong() {
            super(PacketType.PING);
        }

    }
}
