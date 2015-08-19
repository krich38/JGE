package org.jge.protocol.serverstatus;

import org.jge.protocol.Packet;

import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class ServerDiagnostics extends AdminEvent {
    private final long totalMemory;
    private final long freeMemory;
    private final long usedMemory;
    private long upTime;
    private List<String> exceptionsList;

    public ServerDiagnostics() {
        setType(EventType.SERVER_DIAGNOSTICS);


        Runtime runtime = Runtime.getRuntime();
        totalMemory = runtime.totalMemory();
        freeMemory = runtime.freeMemory();
        usedMemory = totalMemory - freeMemory;

    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }


    public long getUpTime() {
        return upTime;
    }


    public long getTotalMemory() {
        return totalMemory;
    }

    public long getUsedMemory() {
        return usedMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }
}
