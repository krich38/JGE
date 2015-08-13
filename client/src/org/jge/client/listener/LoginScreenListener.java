package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.client.GameClient;
import org.jge.client.jfx.Game;

import org.jge.client.jfx.screen.GameOptionScreen;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.model.Id;
import org.jge.model.world.Entity;
import org.jge.model.world.Player;
import org.jge.protocol.packet.Connect;
import org.jge.protocol.packet.ConnectResponse;
import org.jge.protocol.packet.Packet;
import org.jge.protocol.packet.PlayerLoad;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class LoginScreenListener extends NetworkListener {
    private final GameClient client;
    private LoginScreen screen;


    public LoginScreenListener(LoginScreen screen) {


        this.screen = screen;
        client = Game.getGame().getClient();
    }

    @Override
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        if (object instanceof ConnectResponse) {
            ConnectResponse response = (ConnectResponse) object;
            switch (response.getResponse()) {

                case OK:
                    screen.changeScreen(new GameOptionScreen());
                    Player player = new Player((Id<Entity>) response.getAttachment());
                    client.setPlayer(player);

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

        } else if (object instanceof PlayerLoad) {
            PlayerLoad load = (PlayerLoad) object;
            Player player = client.getPlayer();
            player.loadSprites(load.getPlayerType());
            player.setWaypoint(load.getWaypoint());
            client.setListener(new GameClientListener());
            System.out.println("loaddd");
        }
    }
}
