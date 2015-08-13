package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;
import org.jge.model.world.Player;
import org.jge.protocol.packet.Packet;
import org.jge.protocol.packet.PlayerLoad;
import org.jge.protocol.packet.Update;

import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClientListener extends NetworkListener {

    private final GameClient client;

    public GameClientListener() {
        client = Game.getGame().getClient();
    }

    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if (object instanceof Packet) {
            Packet p = (Packet) object;
            switch (p.getPacketType()) {


                case UPDATE:
                    List<Player> playerList = (List<Player>) p.getAttachment();

                    break;

            }
        }
    }

}
