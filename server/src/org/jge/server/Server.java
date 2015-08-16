package org.jge.server;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.ChatMessage;
import org.jge.protocol.packet.Packet;
import org.jge.protocol.packet.Ping;
import org.jge.protocol.packet.PlayerLoad;
import org.jge.server.actor.ConnectionManagerActor;
import org.jge.server.io.PlayerLoader;
import org.jge.server.net.ServerListener;
import org.jge.server.util.PingTimer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

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
    private ServerEngine engine;
    private Map<Id<Entity>, Connection> connectionsEntityMap;
    private Map<Connection, Id<Entity>> connectionIdMap;


    public static Server getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }

    public void init() {
        players = new ConcurrentHashMap<>();
        connectionsEntityMap = new ConcurrentHashMap<>();
        connectionIdMap = new ConcurrentHashMap<>();
        playerLoader = new PlayerLoader();
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());

        engine = new ServerEngine();
        try {
            kryoServer.bind(3744, 3476);
            kryoServer.addListener(new ServerListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(Connection connection, Packet packet) {

        //System.out.println(packet);
        connection.sendTCP(packet);

    }

    public void setOpen(boolean open) {
        this.open = open;
        if (open) {


        }
    }

    public boolean isOpen() {
        return open;
    }

    public PlayerLoader getPlayerLoader() {
        return playerLoader;
    }

    public Map<Id<Entity>, PlayerEncap> getPlayers() {
        return players;
    }


    public Connection getConnectionById(Id<Entity> id) {
        return connectionsEntityMap.get(id);
    }

    public void ping(Connection connection, long time) {
        Ping ping = new Ping();
        ping.setAttachment(time);
        send(connection, ping);
    }

    public Id<Entity> getIdByConnection(Connection connection) {
        return connectionIdMap.get(connection);
    }

    public void onConnect(Id<Entity> id, Connection connection) {
        connectionIdMap.put(connection, id);
        connectionsEntityMap.put(id, connection);
    }

    public void disconnect(Id<Entity> id, String reason) {
        connectionsEntityMap.get(id).close();

        connectionIdMap.remove(connectionsEntityMap.get(id));
        connectionsEntityMap.remove(id);
        players.remove(id);
        System.out.println("Disconnected " + id + ": " + reason);
    }

    public ServerEngine getEngine() {
        return engine;
    }


    public User getUserById(Id<Entity> id) {
        return players.get(id).getUser();
    }
}
