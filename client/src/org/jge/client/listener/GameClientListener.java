package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;
import org.jge.model.world.Player;
import org.jge.protocol.packet.*;

import java.util.List;

/**
 * @author Kyle James Richards
 * @version 1.0
 *          Department of Computer Science and Electrical Engineering
 *          Google Inc.
 */
public class GameClientListener extends NetworkListener {


    private final GameClient client;

    public GameClientListener() {
        game.connected();
        client = game.getClient();
    }

    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if (object instanceof Packet) {
            Packet p = (Packet) object;
            switch (p.getPacketType()) {
                case UPDATE:
                    List<Player> playerList = (List<Player>) p.getAttachment();

                    break;
                case PING:
                    Ping ping = (Ping) p;
                    Packet reply = new Ping.Pong();
                    reply.setAttachment(ping.getAttachment());
                    client.send(reply);
                    System.out.println("Pinged, ponged with: " + ping.getAttachment());
                    break;
                case CHAT:
                    ChatMessage msg = (ChatMessage) p;
                    game.addChatMessage(msg);


            }
        }
    }

}
