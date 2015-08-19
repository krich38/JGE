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
import org.jge.protocol.common.ChatMessage;
import org.jge.protocol.Packet;
import org.jge.protocol.game.Ping;
import org.jge.protocol.game.PlayerLoad;
import org.jge.server.Server;
import org.jge.server.StatusServer;
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
    private final StatusServer status;

    public ServerListener() {
        server = Server.getInstance();
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(ConnectionManagerActor.class));
        playerLoader = new PlayerLoader();
        status = StatusServer.getInstance();
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
                    status.onMessage(msgProcessed);


                    server.send(msg.getConnection(), msgProcessed);
                    break;
                case UPDATE:
                    break;
                case PLAYER_LOAD:
                    PlayerLoad loadRequest = (PlayerLoad) p;
                    PlayerLoad loadSend = new PlayerLoad();
                    PlayerEncap player = playerLoader.load(loadRequest.getId(), loadSend);
                    player.setUser(loadRequest.getUser());
                    loadSend.setAttachment(player);
                    server.getPlayers().put(loadRequest.getId(), player);
                    server.onConnect(loadRequest.getId(), loadRequest.getConnection());
                    server.send(loadRequest.getConnection(), loadSend);
                    System.out.println("Player connected: " + loadRequest.getId());
                    break;
                case PING:

                    server.getEngine().getPinger().addReply((Ping.Pong) p);
                    break;
                case LOGOUT:
                    PlayerEncap pe = (PlayerEncap) p.getAttachment();
                    if (playerLoader.savePlayer(pe)) {
                        System.out.println("Player saved: " + pe.getUser().getUsername());
                    }
                    break;
            }
        }
    }

    @Override
    public void disconnected(Connection c) {
        //if(c.isConnected())
        Id<Entity> id = server.getIdByConnection(c);
        if(id != null) {
            server.disconnect(id, null);
        }
        //super.disconnected(c);
    }

    @Override
    public void connected(Connection c) {

        super.connected(c);
    }
}
