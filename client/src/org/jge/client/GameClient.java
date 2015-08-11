package org.jge.client;

import com.esotericsoftware.kryonet.Client;
import org.jge.model.User;

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
    }

    public void connect(User user) throws IOException {
        if(!client.isConnected()) {

            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);
        }

    }
}
