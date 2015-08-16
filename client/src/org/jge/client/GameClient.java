package org.jge.client;

import com.esotericsoftware.kryonet.Client;
import org.jge.client.jfx.Game;
import org.jge.client.listener.LoginScreenListener;
import org.jge.client.listener.NetworkListener;
import org.jge.model.User;
import org.jge.model.world.Player;
import org.jge.protocol.Protocol;
import org.jge.protocol.packet.ChatMessage;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Packet;
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
    private Player player;
    private User user;

    public GameClient() {
        client = new Client();
        Protocol.register(client.getKryo());
        client.start();
    }

    public void connect(User user) throws IOException {
        if (!client.isConnected()) {
            // are we reconnecting?
this.user = user;

            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);

            Connect connect = new Connect();
            connect.setUser(user);
            client.sendTCP(connect);
        }

    }

    public void register(User user) throws IOException {
        if (!client.isConnected()) {
            this.user = user;
            client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);
            Register register = new Register();
            register.setUser(user);
            client.sendTCP(register);
        }
    }

    public void setListener(NetworkListener listener) {
        // registering a new listener?

        if (this.listener != null) {
            client.removeListener(this.listener);
        }
        client.addListener(listener);
        this.listener = listener;
    }


    public void sendChatMessage(String chatMessage) {
        ChatMessage msg = new ChatMessage();
        msg.setMessage(chatMessage);
        send(msg);
    }

    public void send(Packet packet) {
        packet.setUser(user);
        client.sendTCP(packet);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
