package org.jge.client;

import com.esotericsoftware.kryonet.Client;
import org.jge.client.jfx.Game;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.client.listener.NetworkListener;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.model.world.Player;
import org.jge.model.world.RenderableEntity;
import org.jge.protocol.common.ChatMessage;
import org.jge.protocol.common.Connect;
import org.jge.protocol.Packet;
import org.jge.protocol.Protocol;
import org.jge.protocol.game.Logout;
import org.jge.protocol.game.Register;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClient {
    private static final int TIMEOUT = 5000;
    private static final String IP_ADDRESS = "127.0.0.1";
    private final Client client;
    private NetworkListener listener;
    private Player player;
    private User user;
    private InetAddress address;
    private boolean connected;

    public GameClient() {
        try {
            address = InetAddress.getByName(IP_ADDRESS);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        client = new Client();
        Protocol.registerClientServer(client.getKryo());
        client.start();

    }

    public void connect(User user) throws IOException {
        if (!client.isConnected()) {
            // are we reconnecting?
            if (address.isReachable(2000)) {
                this.user = user;

                client.connect(TIMEOUT, "127.0.0.1", 3744, 3476);

                Connect connect = new Connect();
                connect.setUser(user);
                client.sendTCP(connect);
            } else {
                ((LoginScreen) Game.getGame().getScreen()).updateStatus("Server is offline!");
            }
        }

    }

    public void register(User user) throws IOException {
        if (!client.isConnected()) {
            this.user = user;
            client.connect(TIMEOUT, IP_ADDRESS, 3744, 3476);
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
        this.player = player
        ;
        player.setUser(user);
    }

    public void logout() {
        Logout logout = new Logout();
        //(Id<Entity> id, int playerType, Waypoint waypoint, User user)
        System.out.println(player.getUser());
        int directionFlag;
        RenderableEntity.FacingDirection status = player.getDirectionFlag();
        directionFlag = status.ordinal() + 1;
        PlayerEncap pe = new PlayerEncap(player.getId(), player.getPlayerType(), player.getWaypoint(), player.getUser(), directionFlag);
        logout.setAttachment(pe);
        send(logout);
    }


    public int probeResponse() {
        try {
            Socket s = new Socket("127.0.0.1", 3740);
            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.write(23123);
            out.flush();
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            int response = 0;
            boolean awaiting = true;

            while (awaiting) {
                response = in.read();
                if (response != -1) {
                    awaiting = false;

                }

            }
            s.close();
            return response;


        } catch (IOException ignored) {
            return 0;
        }

    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
