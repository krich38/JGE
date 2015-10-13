package org.jge.server;


import akka.actor.ActorRef;
import akka.actor.Inbox;
import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.Protocol;
import org.jge.protocol.Packet;
import org.jge.protocol.common.Connect;
import org.jge.protocol.game.Ping;
import org.jge.protocol.game.Update;
import org.jge.server.io.DatabaseAdapter;
import org.jge.server.io.PlayerLoader;
import org.jge.server.net.ServerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Kyle Richards
 * @version 1.0
 *          <p>
 *          TODO: Perhaps consider implementing a cleaner player storage rather than using a bunch of maps.
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
    private com.esotericsoftware.kryonet.Server kryoServer;


    public static Server getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Server();
        }
        return INSTANCE;
    }

    public void init() {
        if (DatabaseAdapter.establishConnect()) {
            players = new ConcurrentHashMap<>();
            connectionsEntityMap = new ConcurrentHashMap<>();
            connectionIdMap = new ConcurrentHashMap<>();
            playerLoader = new PlayerLoader();

            kryoServer = new com.esotericsoftware.kryonet.Server();
            kryoServer.start();
            Protocol.registerClientServer(kryoServer.getKryo());

            engine = new ServerEngine();

            try {
                kryoServer.bind(3744, 3476);
                kryoServer.addListener(new ServerListener());
                System.out.println("Game server opened.");
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        //System.out.println("wassup" + id);
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
        Connection c = connectionsEntityMap.remove(id);
        Packet disconnect = new Update(Packet.PacketType.DISCONNECT);
        send(c, disconnect);

        connectionsEntityMap.remove(id);
        connectionIdMap.remove(c);
        c.close();
        players.get(id).setPonged(true); // have we since pinged the connection while disconnecting?
        players.remove(id);

        if (reason != null) {
            System.out.println("Disconnected " + id + ": " + reason);
        }
    }

    public ServerEngine getEngine() {
        return engine;
    }


    public User getUserById(Id<Entity> id) {
        return players.get(id).getUser();
    }

    public void destroy() {

        engine.stop();
        saveAll();
            logoutAll();

            kryoServer.close();
            System.exit(0); // perhaps maybe a better way, but we've saved all players etc



    }

    public void logoutAll() {
        for (Id<Entity> id : players.keySet()) {

            disconnect(id, "");
        }
    }


    public void saveAll() {
        for (PlayerEncap pe : players.values()) {
            playerLoader.savePlayer(pe);
        }
    }
}
