package org.jge.server.net;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.packet.Packet;
import org.jge.server.actor.StatusConnectionManager;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServerListener extends Listener {
    private final ActorRef CONMANAGER ;
    private final Inbox INBOX;

    public StatusServerListener() {
    ActorSystem system = ActorSystem.create("decoworld");
    INBOX = Inbox.create(system);
    CONMANAGER = system.actorOf(Props.create(StatusConnectionManager.class));
}
    @Override
    public void received(Connection conn, Object o) {
        super.received(conn, o);
        System.out.println(o);
        if (o instanceof Packet) {
            Packet p = (Packet) o;
            p.setConnection(conn);
            switch(p.getPacketType()) {
                case CONNECT:

                case DISCONNECT:

                    INBOX.send(CONMANAGER, p);
                    break;
                case ADMIN_EVENT:
                    System.out.println(p.getAttachment());
            }
        }
    }

    @Override
    public void disconnected(Connection c) {
        //if(c.isConnected())

        //super.disconnected(c);
    }

    @Override
    public void connected(Connection c) {

        super.connected(c);
    }
}
