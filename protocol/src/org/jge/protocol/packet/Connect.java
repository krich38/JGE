package org.jge.protocol.packet;

import org.jge.model.User;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Connect extends Packet {
    public Connect() {
        super(Packet.PacketType.CONNECT);
    }


}
