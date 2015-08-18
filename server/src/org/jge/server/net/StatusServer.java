package org.jge.server.net;

import org.jge.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class StatusServer implements Runnable {
    private final ServerSocket socket;
    private final Server server;

    public StatusServer(int port) throws IOException {
        socket = new ServerSocket(port);
        System.out.println("Server status and remote admin server opened");
        server = Server.getInstance();
    }

    @Override
    public void run() {
        while (true) {
            try {

                Socket incoming = socket.accept();

                InputStreamReader reader = new InputStreamReader(incoming.getInputStream());
                boolean awaiting = true;
                int checkCode = -1;
                while (awaiting) {
                    if (reader.ready()) {
                        checkCode = reader.read();
                        awaiting = false;
                    }
                }

                if (checkCode == -1) {
                    incoming.close();
                    return;
                }
                PrintWriter out = new PrintWriter(incoming.getOutputStream());
                if (checkCode == 1) {
                    if (server.isOpen()) {
                        out.write(22);
                    } else {
                        out.write(23);
                    }
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
