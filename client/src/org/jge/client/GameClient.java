package org.jge.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.model.User;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.Connect;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClient {
    private static final int TIMEOUT = 5000;
    private final Client client;

    public GameClient() {
        client = new Client();
        Protocol.register(client.getKryo());
        client.start();
        client.addListener(new ClientListener());
    }

    public void connect(User user) throws IOException {
        if (!client.isConnected()) {

            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);
            Connect connect = new Connect();
            connect.setUser(user);
            client.sendTCP(connect);
        }

    }

    private class ClientListener extends Listener {
        public void received(Connection connection, Object object) {

        }
    }
}
