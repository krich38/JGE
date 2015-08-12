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

    public Connect(ConnectResponse response) {
        this();
        super.setAttachment(response);
    }

    public enum ConnectResponse {
        OK,
        INCORRECT_DETAILS,
        SERVER_CLOSED, BANNED, REGISTER_SUCCESS, REGISTER_FAIL
    }

}
