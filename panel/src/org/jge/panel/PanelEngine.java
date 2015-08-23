package org.jge.panel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class PanelEngine {
    private final Timer ticker;


    public PanelEngine() {
        ticker = new Timer();

    }

    public void start() {

        ticker.schedule(new TimerTask() {
            @Override
            public void run() {
                PanelClient client = Panel.getInstance().getClient();
                if (client.isConnected()) {
                    client.sendRefreshRequest(true);
                }
            }
        }, 500, 30 * 1000L);

        ticker.schedule(new TimerTask() {

            @Override
            public void run() {
                PanelClient client = Panel.getInstance().getClient();
                if (client.isConnected()) {
                    client.sendRefreshRequest(false);
                }
            }
        }, 500, 15 * 1000L);
    }

    public void stop() {
        ticker.cancel();
    }
}
