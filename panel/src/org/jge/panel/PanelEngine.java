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
        //ticker = new Timer();
        PanelClient client = Panel.getInstance().getClient();
        ticker.schedule(new TimerTask() {
            @Override
            public void run() {
                Panel.getInstance().getClient().sendRefreshRequest(true);
            }
        }, 500, 30 * 1000L);

        ticker.schedule(new TimerTask() {

            @Override
            public void run() {
                Panel.getInstance().getClient().sendRefreshRequest(false);
            }
        }, 500, 15 * 1000L);
    }
}
