package org.jge.server;

import java.io.IOException;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {


        Server server = Server.getInstance();
        server.setOpen(true);
        server.init();


    }
}
