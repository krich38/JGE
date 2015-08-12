package org.jge.protocol.packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Register extends Packet {
    public Register() {
        super(Packet.PacketType.REGISTER);
    }
}
