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
        ticker.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 500, TimeUnit.SECONDS.toSeconds(20));
    }
}
