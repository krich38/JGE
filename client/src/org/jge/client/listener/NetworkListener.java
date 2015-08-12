package org.jge.client.listener;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class NetworkListener extends Listener {
    public void received(Connection connection, Object object) {
        super.received(connection, object);
        System.out.println(object);
    }
}
