package org.jge.server;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        try {
            Server server = Server.getInstance();server.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
