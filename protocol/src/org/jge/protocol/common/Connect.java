package org.jge.protocol.common;

import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Connect extends Packet {
    public Connect() {
        super(Packet.PacketType.CONNECT);
    }


}
