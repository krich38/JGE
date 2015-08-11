package org.jge.server;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.Protocol;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Server {
    private static Server INSTANCE;

    public Server() throws IOException {
        com.esotericsoftware.kryonet.Server kryoServer = new com.esotericsoftware.kryonet.Server();
        kryoServer.start();
        Protocol.register(kryoServer.getKryo());
        kryoServer.bind(3744, 3476);
        kryoServer.addListener(new ServerListener());
    }

    public static Server getInstance() throws IOException {
        if(INSTANCE == null) {
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
