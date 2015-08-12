package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.model.world.Player;
import org.jge.protocol.packet.Packet;

import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClientListener extends NetworkListener {

    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if(object instanceof Packet) {
            Packet p = (Packet)object;
            switch(p.getPacketType()) {


                case UPDATE:
                    List<Player> playerList = (List<Player>) p.getAttachment();

                    break;
            }
        }
    }

}
