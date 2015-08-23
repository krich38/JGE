package org.jge.server.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kyle Richards
 * @version 1.0
 */
public class DiagnosticCollection {
    private static final List<String> EXCEPTIONS = new ArrayList<>();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/mm/yyyy h:m:s");

    public static void addException(Exception e) {
        EXCEPTIONS.add("Thrown at " + SIMPLE_DATE_FORMAT.format(new Date()) + ": " + e.toString());
    }

    public static String getExceptions() {
        StringBuilder sb = new StringBuilder();
        for (String s : EXCEPTIONS) {
            sb.append(s).append("\n");
        }
        return sb.toString();
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
