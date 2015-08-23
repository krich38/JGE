package org.jge.protocol.common;

import org.jge.protocol.serverstatus.AdminEvent;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Broadcast extends AdminEvent {
    private String text;
    private boolean anonymous;

    public Broadcast() {
        setType(EventType.BROADCAST);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getText() {
        return text;
    }

    public boolean isAnonymous() {
        return anonymous;
    }
}
