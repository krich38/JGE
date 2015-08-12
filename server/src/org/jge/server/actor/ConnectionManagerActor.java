package org.jge.server.actor;

import akka.actor.UntypedActor;
import com.esotericsoftware.kryonet.Connection;
import org.jge.model.User;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Packet;
import org.jge.server.Server;
import org.jge.server.io.PlayerLoader;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ConnectionManagerActor extends UntypedActor {
    private final Server server;
    private final PlayerLoader loader;

    public ConnectionManagerActor() {
        server = Server.getInstance();
        loader = new PlayerLoader();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        Packet packet = (Packet) message;
        switch (packet.getPacketType()) {
            case DISCONNECT:
                break;
            case CONNECT:
                Connect connect = (Connect) packet;
                Packet response;

                if (server.isOpen()) {

                    User user = connect.getUser();
                    response = new Connect(Connect.ConnectResponse.OK);
                    loader.loadPlayer(user);
                    //response = new Connect(Connect.ConnectResponse.INCORRECT_DETAILS);
                    // packet.getConnection().close();

                } else {
                    response = new Connect(Connect.ConnectResponse.SERVER_CLOSED);

                }
                server.send(connect.getConnection(), response);
                break;

            case REGISTER:
                break;
        }
    }
}
