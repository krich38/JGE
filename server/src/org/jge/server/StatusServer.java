package org.jge.server;

import com.esotericsoftware.kryonet.Connection;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.ConnectResponse;
import org.jge.protocol.packet.Packet;
import org.jge.server.net.ServerListener;
import org.jge.server.net.StatusServerListener;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServer {
    private static StatusServer INSTANCE;
    private final Server server;
    private int port;

    public StatusServer(int port) {
        this.port = port;

        System.out.println("Server status and remote admin server opened");
        server = Server.getInstance();
    }


    public static StatusServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StatusServer(3741);
        }
        return INSTANCE;
        }

    public void init() {
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());

        try {
            kryoServer.bind(port);
            kryoServer.addListener(new StatusServerListener());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(Connection connection, Packet packet) {
        connection.sendTCP(packet);
        System.out.println("Sending: " + packet);
    }
}
