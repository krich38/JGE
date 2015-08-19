package org.jge.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Waypoint;
import org.jge.protocol.packet.*;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Protocol {
    public static void registerClientServer(Kryo kryo) {
        registerCommons(kryo);


        kryo.register(Register.class);

        kryo.register(PlayerLoad.class);
        kryo.register(Waypoint.class);

        kryo.register(ChatMessage.class);

        kryo.register(Ping.class);
        kryo.register(Ping.Pong.class);
        kryo.register(PlayerEncap.class);
        kryo.register(Logout.class);


    }

    private static void registerCommons(Kryo kryo) {
        kryo.register(User.class);
        kryo.register(Id.class);
        kryo.register(Connect.class);
        kryo.register(Packet.class);
        kryo.register(Packet.PacketType.class);        kryo.register(ConnectResponse.class);
        kryo.register(ConnectResponse.Response.class);kryo.register(Connection.class);kryo.register(User.AccessRights.class);

    }

    public static void registerStatusServer(Kryo kryo) {
        registerCommons(kryo);
        kryo.register(AdminEvent.class);
        kryo.register(AdminEvent.EventType.class);
    }


}
