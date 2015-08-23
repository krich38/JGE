package org.jge.server.net;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.Packet;
import org.jge.server.Server;
import org.jge.server.StatusServer;
import org.jge.server.actor.PanelServerActor;
import org.jge.server.actor.StatusConnectionManager;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServerListener extends Listener implements Runnable {
    private final ActorRef CONMANAGER;
    private final Inbox INBOX;
    private final ActorRef statusEventActor;
    private final ServerSocket probeServer;
    private final Server server;
    private final StatusServer statusServer;

    public StatusServerListener(int probePort) throws IOException {
        ActorSystem system = ActorSystem.create("decoworld");
        INBOX = Inbox.create(system);
        CONMANAGER = system.actorOf(Props.create(StatusConnectionManager.class));
        statusEventActor = system.actorOf(Props.create(PanelServerActor.class));
        probeServer = new ServerSocket(probePort);

        server = Server.getInstance();
        statusServer = StatusServer.getInstance();

    }

    @Override
    public void received(Connection conn, Object o) {
        super.received(conn, o);

        if (o instanceof Packet) {
            Packet p = (Packet) o;
            p.setConnection(conn);
            switch (p.getPacketType()) {
                case CONNECT:

                case DISCONNECT:

                    INBOX.send(CONMANAGER, p);
                    break;
                case ADMIN_EVENT:
                    INBOX.send(statusEventActor, p);
                    break;
            }
        }
    }

    @Override
    public void disconnected(Connection c) {
        //if(c.isConnected())

        //super.disconnected(c);
    }

    @Override
    public void connected(Connection c) {

        super.connected(c);
    }

    @Override
    public void run() {
        try {
            while (statusServer.isRunning()) {

                Socket probe = probeServer.accept();
                InputStreamReader in = new InputStreamReader(probe.getInputStream());
                PrintWriter out = new PrintWriter(probe.getOutputStream());
                boolean awaiting = true;

                while (awaiting) {
                    int request = in.read();
                    if (request != -1) {
                        if (request == 63) {
                            awaiting = false;
                            if (server.isOpen()) {
                                out.write(21);
                            } else {
                                out.write(20);
                            }
                            out.flush();
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
