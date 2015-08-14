package org.jge.server;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.model.Id;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.ChatMessage;
import org.jge.protocol.packet.Packet;
import org.jge.protocol.packet.PlayerLoad;
import org.jge.server.actor.ConnectionManagerActor;
import org.jge.server.io.PlayerLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Server {
    private static Server INSTANCE;
    private Inbox INBOX;
    private ActorRef CONMANAGER;
    private boolean open;
    private PlayerLoader playerLoader;
    private Map<Id<Entity>, PlayerEncap> players;


    public static Server getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }

    public void init() {players = new HashMap<>();
        playerLoader = new PlayerLoader();
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(ConnectionManagerActor.class));playerLoader = new PlayerLoader();

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
        if(open) {


        }
    }

    public boolean isOpen() {
        return open;
    }

    public PlayerLoader getPlayerLoader() {
        return playerLoader;
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
                        System.out.println(msg.getAttachment() + ": " +msg.getMessage());
                        break;
                    case UPDATE:
                        break;
                    case PLAYER_LOAD:
                        PlayerLoad loadRequest = (PlayerLoad) p;
                        PlayerLoad loadSend = new PlayerLoad();
                        playerLoader.load(loadSend);
                        PlayerEncap player = new PlayerEncap(loadRequest.getId(), loadSend.getPlayerType(), loadSend.getWaypoint(), loadRequest.getUser());
                        players.put(loadSend.getId(), player);
                        send(loadRequest.getConnection(), loadSend);
                        System.out.println("Player connected: " + player.getId());
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
