package org.jge.server.actor;

import akka.actor.UntypedActor;
import org.jge.model.Id;
import org.jge.model.User;
import org.jge.model.world.Entity;
import org.jge.protocol.common.Connect;
import org.jge.protocol.common.ConnectResponse;
import org.jge.protocol.Packet;
import org.jge.server.Server;
import org.jge.server.StatusServer;
import org.jge.server.io.PlayerLoader;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusConnectionManager extends UntypedActor {


    private final PlayerLoader loader;
    private final StatusServer server;

    public StatusConnectionManager() {
loader = Server.getInstance().getPlayerLoader();
        server = StatusServer.getInstance();
}
    @Override
    public void onReceive(Object message) throws Exception {
        Packet p = (Packet) message;
System.out.println(p.getPacketType());
        switch(p.getPacketType()) {
            case CONNECT:
                Connect connect = (Connect) p;
                User user = connect.getUser();
                ConnectResponse response = new ConnectResponse();
                Id<Entity> id = loader.loadId(user);
                if (id != null) {

                    User.AccessRights rights = loader.getRightsFor(id);
                    if(User.AccessRights.hasAccess(rights, User.AccessRights.ADMINISTRATOR)) {
                        response.setResponse(ConnectResponse.Response.OK);
                        response.setAttachment(id);
                        server.register(id, p.getConnection());

                    } else {
                        response.setResponse(ConnectResponse.Response.SERVER_CLOSED);
                    }


                } else {
                  response.setResponse(ConnectResponse.Response.INCORRECT_DETAILS);
                }
                server.send(p.getConnection(), response);
                break;
        }
    }
}
