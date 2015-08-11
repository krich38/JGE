package org.jge.server;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.Packet;
import org.jge.server.actor.ConnectionManagerActor;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Server {
    private static Server INSTANCE;
    private final Inbox INBOX;
    private final ActorRef CONMANAGER;

    public Server() throws IOException {
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(ConnectionManagerActor.class));
        kryoServer.bind(3744, 3476);
        kryoServer.addListener(new ServerListener());
    }

    public static Server getInstance() throws IOException {
        if (INSTANCE == null) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }


    public void open() {

    }

    private class ServerListener extends Listener {
        @Override
        public void received(Connection conn, Object o) {
            super.received(conn, o);
            System.out.println(o);
            if (o instanceof Packet) {
                Packet p = (Packet) o;
                switch (p.getPacketType()) {

                    case CONNECT:

                    case DISCONNECT:


                    case REGISTER:
                        INBOX.send(CONMANAGER, p);
                        break;
                }
            }
        }

        @Override
        public void disconnected(Connection c) {
            super.disconnected(c);
        }

        @Override
        public void connected(Connection c) {
            super.connected(c);
        }
    }
}
