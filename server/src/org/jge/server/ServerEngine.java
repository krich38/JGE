package org.jge.server;

import javafx.util.Duration;
import org.jge.server.util.PingTimer;


import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ServerEngine {
    private final Timer ticker;
    private final PingTimer pinger;

    public ServerEngine() {
        pinger = new PingTimer();
        ticker = new Timer();

        ticker
                .schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pinger.pingAll();
                    }
                }, 0L, 5000L);
        ticker.schedule(new TimerTask() {
            @Override
            public void run() {
                pinger.timeout();
            }
        }, 100L, 5000L);

    }

    public PingTimer getPinger() {
        return pinger;
    }

    public void stop() {
        ticker.cancel();

    }
}
