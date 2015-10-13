package org.jge.protocol.game;

import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Update extends Packet {
    public Update() {
        super(PacketType.UPDATE);
    }

    public Update(PacketType type) {
        super(type);
    }
}
