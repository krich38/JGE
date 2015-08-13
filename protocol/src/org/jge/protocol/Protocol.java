package org.jge.protocol;

import com.esotericsoftware.kryo.Kryo;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.Waypoint;
import org.jge.protocol.packet.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Protocol {
    public static void register(Kryo kryo) {


        kryo.register(User.class);
        kryo.register(Id.class);
        kryo.register(Connect.class);
        kryo.register(Packet.class);
        kryo.register(org.jge.protocol.packet.Packet.PacketType.class);
        kryo.register(Register.class);
        kryo.register(ConnectResponse.class);
        kryo.register(ConnectResponse.Response.class);
        kryo.register(PlayerLoad.class);
        kryo.register(Waypoint.class);
        kryo.register(Id.class);
        kryo.register(ChatMessage.class);

    }


}
