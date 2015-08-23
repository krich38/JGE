package org.jge.protocol;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.model.world.Waypoint;
import org.jge.protocol.common.Broadcast;
import org.jge.protocol.common.ChatMessage;
import org.jge.protocol.common.Connect;
import org.jge.protocol.common.ConnectResponse;
import org.jge.protocol.game.Logout;
import org.jge.protocol.game.Ping;
import org.jge.protocol.game.PlayerLoad;
import org.jge.protocol.game.Register;
import org.jge.protocol.serverstatus.AdminEvent;
import org.jge.protocol.serverstatus.Refresh;
import org.jge.protocol.serverstatus.ServerDiagnostics;

import java.util.ArrayList;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Protocol {
    public static void registerClientServer(Kryo kryo) {
        registerCommons(kryo);


        kryo.register(Register.class);

        kryo.register(PlayerLoad.class);


        kryo.register(Ping.class);
        kryo.register(Ping.Pong.class);

        kryo.register(Logout.class);


    }

    private static void registerCommons(Kryo kryo) {
        kryo.register(ChatMessage.class);
        kryo.register(User.class);
        kryo.register(Id.class);
        kryo.register(PlayerEncap.class);
        kryo.register(Connect.class);
        kryo.register(Waypoint.class);
        kryo.register(Packet.class);
        kryo.register(Packet.PacketType.class);
        kryo.register(ConnectResponse.class);
        kryo.register(ConnectResponse.Response.class);
        kryo.register(Connection.class);
        kryo.register(User.AccessRights.class);

    }

    public static void registerStatusServer(Kryo kryo) {
        registerCommons(kryo);
        kryo.register(Refresh.class);
        kryo.register(AdminEvent.class);
        kryo.register(AdminEvent.EventType.class);
        kryo.register(ArrayList.class);
        kryo.register(ServerDiagnostics.class);
        kryo.register(Exception.class);
        kryo.register(Broadcast.class);

    }


}
