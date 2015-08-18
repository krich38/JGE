package org.jge.panel;

import akka.actor.Actor;
import com.esotericsoftware.kryonet.Client;
import org.jge.model.User;
import org.jge.panel.net.LoginScreenListener;
import org.jge.panel.net.NetworkListener;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Packet;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelClient {
    private static final int TIMEOUT = 5000;
    private final Client client;
    private User user;
    private NetworkListener listener;

    public PanelClient() {
        client = new Client();
        client.start();
        Protocol.register(client.getKryo());
    }
    public void connect(User user) throws IOException {
        if(
                !client.isConnected()) {
            this.user = user;

            client.connect(TIMEOUT, "127.0.0.1", 3741);
            Connect connect = new Connect();
            send(connect);
        }
    }

    public void send(Packet p) {
        p.setUser(user);
        client.sendTCP(p);
    }

    public void setListener(NetworkListener listener) {
        if (this.listener != null) {
            client.removeListener(this.listener);
        }
        client.addListener(listener);
        this.listener = listener;
    }

    public void disconnect() {
        client.close();
    }
}