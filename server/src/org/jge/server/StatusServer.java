package org.jge.server;

import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.protocol.Protocol;
import org.jge.protocol.Packet;
import org.jge.protocol.common.ChatMessage;
import org.jge.protocol.serverstatus.Refresh;
import org.jge.protocol.serverstatus.ServerDiagnostics;
import org.jge.server.net.StatusServerListener;
import org.jge.server.util.DiagnosticCollection;

import java.io.IOException;
import java.util.*;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServer {
    private static StatusServer INSTANCE;
    private final Server server;
    private int probePort;
    private int port;
    private List<ChatMessage> msgs;
    private Map<Id<Entity>, Connection> connections;
    private boolean running;

    public StatusServer(int probePort, int port) {
        this.probePort = probePort;
        this.port = port;
        System.out.println("Remote admin server opened");
        server = Server.getInstance();
    }


    public static StatusServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StatusServer(3740, 3741);
        }
        return INSTANCE;
    }

    public void init() {
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.registerStatusServer(kryoServer.getKryo());
        msgs = new ArrayList<>(2);
        try {
            kryoServer.bind(port);
            System.out.println(port);
            StatusServerListener status = new StatusServerListener(probePort);
            new Thread(status).start();
            kryoServer.addListener(status);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Connection connection, Packet packet) {
        connection.sendTCP(packet);
        System.out.println("Sending: " + packet);
    }

    public void onMessage(ChatMessage msg) {
        msgs.add(msg);
        if (msgs.size() == 2) {
            sendRefresh(false);
            msgs.clear();
        }
    }

    public void sendRefresh(boolean full) {
        Refresh refresh;
        if (full) {
            refresh = new Refresh(true);
            refresh.setMessages(msgs);
            msgs.clear();
            refresh.setPlayerList(new ArrayList<PlayerEncap>(server.getPlayers().values()));
            refresh.setServerStatus(server.isOpen());
        } else {
            refresh = new Refresh(false);
            refresh.setMessages(msgs);

        }
        sendAll(refresh);
    }

    public void sendDiagnostics(Connection con) {
        ServerDiagnostics diagnostics = new ServerDiagnostics();
        diagnostics.setUpTime(DiagnosticCollection.getRunningTime());
        if (DiagnosticCollection.exceptions()) {
            diagnostics.setExceptions(DiagnosticCollection.getExceptions());
        }
        send(con, diagnostics);
    }

    private void sendAll(Packet packet) {
        for (Connection c : connections.values()) {
            send(c, packet);
        }
    }

    public void register(Id<Entity> id, Connection connection) {
        if (connections == null) {
            connections = new HashMap<>();
        }
        connections.put(id, connection);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void destroy() {
        setRunning(false);
    }
}
