package org.jge.server.actor;

import akka.actor.UntypedActor;
import com.esotericsoftware.kryonet.Connection;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.Entity;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.ConnectResponse;
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
        loader = server.getPlayerLoader();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        Packet packet = (Packet) message;
        switch (packet.getPacketType()) {
            case DISCONNECT:
                break;
            case CONNECT:
                Connect connect = (Connect) packet;
                ConnectResponse response = new ConnectResponse();

                if (server.isOpen()) {

                    User user = connect.getUser();

                    Id<Entity> id = loader.loadId(user);
                    if(id != null) {
                        response.setResponse(ConnectResponse.Response.OK);
                        response.setAttachment(loader.loadId(user));server.send(packet.getConnection(), response);
                    } else {
                        response.setResponse(ConnectResponse.Response.INCORRECT_DETAILS);
                        server.send(packet.getConnection(), response);
                        connect.getConnection().close();
                    }


                } else {
                    response.setResponse(ConnectResponse.Response.SERVER_CLOSED);

                }
                server.send(connect.getConnection(), response);
                break;

            case REGISTER:
                break;
        }
    }
}
