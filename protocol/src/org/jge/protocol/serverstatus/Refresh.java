package org.jge.protocol.serverstatus;

import org.jge.model.server.PlayerEncap;
import org.jge.protocol.Packet;
import org.jge.protocol.common.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class Refresh extends AdminEvent {
    private boolean full;
    private List<ChatMessage> messages;

    private List<PlayerEncap> playerList;
    private boolean isOpen = true;
    private long upTime;
    private List<Exception> exceptionsList;

    public Refresh(boolean full) {
        this.full = full;
        setType(EventType.REQUEST_REFRESH);

    }

    public Refresh() {

    }

    public boolean isFull() {
        return full;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }


    public void setPlayerList(List<PlayerEncap> playerList) {
        this.playerList = playerList;
    }

    public List<PlayerEncap> getPlayerList() {
        return playerList;
    }

    public void setServerStatus(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public void setExceptionsList(List<Exception> exceptionsList) {
        this.exceptionsList = exceptionsList;
    }
}
