package com.test.monitoring;

import java.io.PrintWriter;

/**
 * @class TransAlpesMonitor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class Trace {

    private Monitor monitor = Monitor.getInstance();
    private long[] traces;
    private byte[] data;

    private Trace() {
        traces = new long[2];
        monitor.addTrace(this);
    }

    public static Trace getInstance() {
        return new Trace();
    }

    public void addInitTrace(long init) {
        traces[0] = init;
    }

    public void addEndTrace(long end) {
        traces[1] = end;
    }

    public void printTrace(PrintWriter writer) {
        StringBuilder log = getLog();
        writeLogToOutputStream(writer, log);
    }

    public void printTrace() {
        StringBuilder log = getLog();
        System.out.println(log);
    }

    /**
     * @param os
     * @param log
     */
    private void writeLogToOutputStream(PrintWriter writer, StringBuilder log) {
        writer.append(getLog());
    }

    /**
     * @return
     */
    private StringBuilder getLog() {
        StringBuilder log = new StringBuilder();
        log.append("inicio: ");
        log.append(traces[0]);
        log.append("\n");
        log.append("fin: ");
        log.append(traces[1]);
        log.append("\n");
        log.append("total: ");
        log.append(traces[1] - traces[0]);
        log.append("\n");
        return log;
    }

    /**
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data
     *        the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    public long[] getTraces() {
        return traces;
    }

    public void setTraces(long[] traces) {
        this.traces = traces;
    }

}
