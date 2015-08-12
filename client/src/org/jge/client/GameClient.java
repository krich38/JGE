package org.jge.client;

import com.esotericsoftware.kryonet.Client;
import org.jge.client.listener.LoginScreenListener;
import org.jge.client.listener.NetworkListener;
import org.jge.model.User;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Register;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClient {
    private static final int TIMEOUT = 5000;
    private final Client client;
    private NetworkListener listener;

    public GameClient() {
        client = new Client();
        Protocol.register(client.getKryo());
        client.start();
    }

    public void connect(User user) throws IOException {
        if (!client.isConnected()) {
            // are we reconnecting?


            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);

            Connect connect = new Connect();
            connect.setUser(user);
            client.sendTCP(connect);
        }

    }

    public void register(User user) throws IOException {
        if (!client.isConnected()) {

            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);
            Register register = new Register();
            register.setUser(user);
            client.sendTCP(register);
        }
    }

    public void setListener(NetworkListener listener) {
        // registering a new listener?

        if(this.listener != null) {
            client.removeListener(this.listener);
        }
        client.addListener(listener);
        this.listener = listener;
    }


}
