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
public class PanelServerActor extends UntypedActor {
    private final StatusServer statusServer;
    private final Server server;

    public PanelServerActor() {
        statusServer = StatusServer.getInstance();
        server = Server.getInstance();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        AdminEvent ae = (AdminEvent) message;
        boolean needReply = false;
        switch (ae.getType()) {

            case KICK_ALL:
server.logoutAll();
                ae.setAttachment(true);
                needReply = true;
                break;
            case CLOSE_SERVER:
                if(server.isOpen()) {
                    server.setOpen(false);
                    ae.setAttachment(true);
                } else {
                    ae.setAttachment(false);
                }
                needReply = true;
                break;
            case SHUT_DOWN:
                server.destroy();
                statusServer.setRunning(false);
                ae.setAttachment(true);
                needReply = true;
                break;
            case OPEN_SERVER:
                if(!server.isOpen()) {
                    server.setOpen(true);
                    ae.setAttachment(true);
                }
                else {
                    ae.setAttachment(false);
                }
                needReply = true;
                break;
            case SAVE_ALL:
                server.saveAll();
                break;
            case DIAGNOSTICS:
                DiagnosticCollection.addException(new Exception("LOL"));
                statusServer.sendDiagnostics(ae.getConnection());
                break;

            case BROADCAST:
                Broadcast b = (Broadcast) ae;
                System.out.println(b.getText() + " : " + b.isAnonymous());
                ae.setAttachment(true);
                needReply = true;
                break;
            case REQUEST_REFRESH:
                statusServer.sendRefresh(true);
                break;



        }

        if(needReply) {
            statusServer.send(ae.getConnection(), ae);
        }

    }
}
