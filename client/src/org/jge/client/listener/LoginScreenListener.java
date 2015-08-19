package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import org.jge.client.GameClient;

import org.jge.client.jfx.screen.GameOptionScreen;
import org.jge.client.jfx.screen.GameScreen;
import org.jge.client.jfx.screen.LoginScreen;
import org.jge.model.Id;
import org.jge.model.server.PlayerEncap;
import org.jge.model.world.Entity;
import org.jge.model.world.Player;
import org.jge.model.world.Waypoint;
import org.jge.protocol.packet.ConnectResponse;
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
        client = game.getClient();
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

                    game.setPlayer(player);
                    game.getClient().setPlayer(player);

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

            //todo: clean this up
            PlayerLoad load = (PlayerLoad) object;
            Player player = game.getPlayer();
            System.out.println("loll: " + player.getId());
            PlayerEncap pe = (PlayerEncap) load.getAttachment();Waypoint waypoint = pe.getWaypoint();
            //player.setTranslateX(waypoint.getX()* 32);
            //player.setTranslateY(waypoint.getY() * 32);
            player.loadSprites(pe.getPlayerType(), GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);

            System.out.println("HMM: " + pe.getWaypoint());

            player.setWaypoint(waypoint);

            client.setListener(new GameClientListener());
            game.getEngine().getWorld().setPlayer(player);
        }
    }
}
