package org.jge.server.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class DiagnosticCollection {
    private static final List<Exception> EXCEPTIONS = new ArrayList<>();


    public static void addException(Exception e) {
        EXCEPTIONS.add(e);
    }

    public static List<Exception> getExceptions() {
        List<Exception> copy = new ArrayList<>(EXCEPTIONS);
        EXCEPTIONS.clear();
        return copy;
    }

    public static long getRunningTime() {
        RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
        final long millis = mxBean.getUptime();
        return millis;
    }

    public static boolean exceptions() {
        return !EXCEPTIONS.isEmpty();
    }

}
