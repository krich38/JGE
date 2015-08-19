package org.jge.panel.net;

import com.esotericsoftware.kryonet.Connection;
import org.jge.panel.screen.PanelScreen;
import org.jge.protocol.Packet;
import org.jge.protocol.serverstatus.AdminEvent;
import org.jge.protocol.serverstatus.Refresh;
import org.jge.protocol.serverstatus.ServerDiagnostics;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelScreenListener extends NetworkListener {
    private PanelScreen screen;

    public PanelScreenListener(PanelScreen screen) {

        this.screen = screen;
    }

    @Override
    protected void onReceived(Connection connection, Packet packet) {
        AdminEvent ae = (AdminEvent) packet;
        System.out.println(ae.getType());
        switch (ae.getType()) {


            case SERVER_DIAGNOSTICS:
                System.out.println("wassup");
                ServerDiagnostics sd = (ServerDiagnostics) ae;

                screen.showDiagnostics(sd);
                break;
            case REQUEST_REFRESH:
                Refresh refresh = (Refresh) ae;
                if (refresh.isFull()) {
                    screen.updatePlayerList(refresh.getPlayerList());
                    screen.updateStatus(refresh.isOpen());
                }
                screen.appendMessages(refresh.getMessages());
                break;
        }

    }
}
