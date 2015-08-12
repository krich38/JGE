package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.client.jfx.Game;

import org.jge.client.jfx.screen.GameOptionScreen;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginScreenListener extends NetworkListener {
    private LoginScreen screen;

    public LoginScreenListener(Game main, LoginScreen screen) {

        this.screen = screen;
    }

    @Override
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if(object instanceof Connect) {
            Connect response = (Connect) object;
            switch((Connect.ConnectResponse) response.getAttachment()) {

                case OK:
                    System.out.println("lal");
                    screen.changeScreen(new GameOptionScreen());
                    break;
                case INCORRECT_DETAILS:
                    screen.updateStatus("Incorrect details. Please try again!");
                    break;
                case SERVER_CLOSED:
                    break;
                case BANNED:
                    break;
                case REGISTER_SUCCESS:
                    break;
                case REGISTER_FAIL:
                    break;
            }

        }
    }
}
