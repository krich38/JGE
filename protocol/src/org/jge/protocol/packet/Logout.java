package org.jge.protocol.packet;

import org.jge.model.server.PlayerEncap;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Logout extends Packet {
    public Logout() {
        super(PacketType.LOGOUT);
    }

    private PlayerEncap encap;

    public PlayerEncap getEncap() {
        return encap;
    }

    public void setEncap(PlayerEncap encap) {
        this.encap = encap;
    }
}
