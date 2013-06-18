package com.test.monitoring;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class Monitor.java
 * @author Felipe
 * @Date Jun 17, 2013
 * @since 1.0
 */
public class Monitor {

	private static Monitor instance = new Monitor();
	private List<Trace> traces;

	private Monitor() {
		traces = new ArrayList<Trace>();
	}

	public static Monitor getInstance() {
		return instance;
	}

	public void addTrace(Trace trace) {
		traces.add(trace);
	}

	public void printTraces() {
		String path = ConfigurationManager.getInstance().getProperty(
				Properties.LOGS_PATH.name());
		PrintWriter writer = null;
		try {
			long init = traces.get(0).getTraces()[0];
			writer = new PrintWriter(path);
			int counter = 0;
			long testTime = Long.parseLong(ConfigurationManager.getInstance()
					.getProperty(Properties.TEST_TIME.name()));
			for (Trace trace : traces) {
				if ((trace.getTraces()[1] - init) >= testTime) {
					break;
				}
				trace.printTrace(writer);
				counter++;
			}
			endTrace(writer, counter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private void endTrace(PrintWriter writer, int counter) {
		writer.println("size: " + counter);
		writer.println("TotalSize: " + traces.size());
		writer.flush();
		traces.clear();
	}
}
