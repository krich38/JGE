package org.jge.server.actor;

import akka.actor.UntypedActor;
import org.jge.model.User;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ConnectionManagerActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        Packet packet = (Packet) message;
        switch (packet.getPacketType()) {

            case CONNECT:
                Connect connect = (Connect) packet;
                User user = connect.getUser();
                break;
            case DISCONNECT:
                break;
            case REGISTER:
                break;
        }
    }
}
