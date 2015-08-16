package org.jge.server.net;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.model.Id;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.packet.ChatMessage;
import org.jge.protocol.packet.Packet;
import org.jge.protocol.packet.Ping;
import org.jge.protocol.packet.PlayerLoad;
import org.jge.server.Server;
import org.jge.server.actor.ConnectionManagerActor;
import org.jge.server.io.PlayerLoader;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ServerListener extends Listener {
    private final ActorRef CONMANAGER;
    private final Inbox INBOX;
    private final PlayerLoader playerLoader;
    private final Server server;

    public ServerListener() {
        server = Server.getInstance();
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(ConnectionManagerActor.class));
        playerLoader = new PlayerLoader();

    }
    @Override
    public void received(Connection conn, Object o) {
        super.received(conn, o);
        //System.out.println(o);
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
                    System.out.println(msg.getUser());
                    // todo process chat here
                    String text = msg.getMessage();
                    ChatMessage msgProcessed = new ChatMessage();
                    msgProcessed.setMessage(text);
                    msgProcessed.setUser(msg.getUser());


                    server.send(msg.getConnection(), msgProcessed);
                    break;
                case UPDATE:
                    break;
                case PLAYER_LOAD:
                    PlayerLoad loadRequest = (PlayerLoad) p;
                    PlayerLoad loadSend = new PlayerLoad();
                    playerLoader.load(loadSend);
                    PlayerEncap player = new PlayerEncap(loadRequest.getId(), loadSend.getPlayerType(), loadSend.getWaypoint(), loadRequest.getUser());
                    server.getPlayers().put(loadRequest.getId(), player);
                    server.onConnect(loadRequest.getId(), loadRequest.getConnection());
                    server.send(loadRequest.getConnection(), loadSend);
                    System.out.println("Player connected: " + player.getId());
                    break;
                case PING:

                    server.getEngine().getPinger().addReply((Ping.Pong)p);
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
