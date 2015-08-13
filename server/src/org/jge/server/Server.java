package org.jge.server;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.ChatMessage;
import org.jge.protocol.packet.Packet;
import org.jge.server.actor.ConnectionManagerActor;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Server {
    private static Server INSTANCE;
    private Inbox INBOX;
    private ActorRef CONMANAGER;
    private boolean open;

    public static Server getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }

    public void init() {
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(ConnectionManagerActor.class));
        try {
            kryoServer.bind(3744, 3476);
            kryoServer.addListener(new ServerListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(Connection connection, Packet packet) {

        System.out.println(packet);
        connection.sendTCP(packet);

    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    private class ServerListener extends Listener {
        @Override
        public void received(Connection conn, Object o) {
            super.received(conn, o);
            System.out.println(o);
            if (o instanceof Packet) {
                Packet p = (Packet) o;
                p.setConnection(conn);
                switch (p.getPacketType()) {

                    case CONNECT:

                    case DISCONNECT:


                    case REGISTER:
                        INBOX.send(CONMANAGER, p);
                        break;
                    case CHAT:
                        ChatMessage msg = (ChatMessage) p;
                        System.out.println(msg.getMessage());
                        break;
                    case UPDATE:
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
