package org.jge.server;

import com.esotericsoftware.minlog.Log;

import java.util.logging.Level;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        Log.set(Log.LEVEL_ERROR);
        Server server = Server.getInstance();
        server.setOpen(true);
        server.init();

        StatusServer status = StatusServer.getInstance();
        status.setRunning(true);
        status.init();

    }
}
