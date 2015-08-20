package org.jge.server.actor;

import akka.actor.UntypedActor;
import org.jge.protocol.Packet;
import org.jge.protocol.common.Broadcast;
import org.jge.protocol.serverstatus.AdminEvent;
import org.jge.protocol.serverstatus.ServerDiagnostics;
import org.jge.server.Server;
import org.jge.server.StatusServer;
import org.jge.server.util.DiagnosticCollection;


/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServerActor extends UntypedActor {
    private final StatusServer server;

    public StatusServerActor() {
        server = StatusServer.getInstance();
    }
    @Override
    public void onReceive(Object message) throws Exception {
        AdminEvent ae = (AdminEvent) message;
        switch(ae.getType()) {

            case KICK_ALL:
                break;
            case CLOSE_SERVER:
                break;
            case SHUT_DOWN:
                break;
            case OPEN_SERVER:
                break;
            case SAVE_ALL:
                break;
            case DIAGNOSTICS:
                DiagnosticCollection.addException(new Exception("LOL"));
                server.sendDiagnostics(ae.getConnection());
                break;
            case SERVER_DIAGNOSTICS:
                break;
            case BROADCAST:
                Broadcast b = (Broadcast) ae;
                System.out.println(b.getText() +" : " + b.isAnonymous());
                break;
            case REQUEST_REFRESH:
                server.sendRefresh(true);
                break;
        }

    }
}
