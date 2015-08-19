package org.jge.panel.net;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.jge.protocol.Packet;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public abstract class NetworkListener extends Listener {
    public final void received(Connection connection, Object object) {
        super.received(connection, object);
        if (object instanceof Packet) {
            onReceived(connection, (Packet) object);
        }
    }

    protected abstract void onReceived(Connection connection, Packet packet);
}
