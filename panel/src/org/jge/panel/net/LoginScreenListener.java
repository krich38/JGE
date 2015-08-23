package org.jge.panel.net;

import com.esotericsoftware.kryonet.Connection;
import org.jge.panel.Panel;
import org.jge.panel.screen.LoginScreen;
import org.jge.panel.screen.PanelScreen;
import org.jge.protocol.common.ConnectResponse;
import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginScreenListener extends NetworkListener {
    private final Panel panel;
    private LoginScreen screen;

    public LoginScreenListener(LoginScreen screen) {
        this.screen = screen;
        panel = Panel.getInstance();
    }

    @Override
    protected void onReceived(Connection connection, Packet packet) {
        ConnectResponse response = (ConnectResponse) packet;
        switch (response.getResponse()) {
            case OK:
                panel.changeScreen(new PanelScreen());
                panel.getClient().setConnected(true);
                break;
            case SERVER_CLOSED:
                screen.updateStatus("Insufficient access!");
                panel.getClient().disconnect();
                break;
            case INCORRECT_DETAILS:
                screen.updateStatus("Incorrect details!");
                panel.getClient().disconnect();
                break;
        }
    }
}
