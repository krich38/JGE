package org.jge.protocol.game;

import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Logout extends Packet {
    public Logout() {
        super(PacketType.LOGOUT);
    }


}
