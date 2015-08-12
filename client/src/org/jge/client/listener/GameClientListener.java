package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class GameClientListener extends NetworkListener {

    public void received(Connection connection, Object object) {
        super.received(connection, object);
        System.out.println(object);
    }

}
