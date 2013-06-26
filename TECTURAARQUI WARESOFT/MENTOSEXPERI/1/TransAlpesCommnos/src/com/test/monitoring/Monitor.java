package com.test.monitoring;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		String path = getPathName();
		PrintWriter writer = null;
		try {
			long init = traces.get(0).getTraces().get(0);
			JSONObject jsonMonitor = new JSONObject();
			JSONArray jsonTraces = new JSONArray();
			writer = new PrintWriter(path);
			creaeTraces(init, jsonMonitor, jsonTraces);
			writer.append(jsonMonitor.toString());
			traces.clear();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * @param init
	 * @param jsonMonitor
	 * @param jsonTraces
	 * @throws JSONException
	 */
	private void creaeTraces(long init, JSONObject jsonMonitor,
			JSONArray jsonTraces) throws JSONException {
		int counter = 0;
		long total = 0;
		for (Trace trace : traces) {
			JSONObject jsonTrace = trace.getJsonTrace();
			total += jsonTrace.getLong("total");
			jsonTraces.put(jsonTrace);
			counter++;
		}
		addTraceValues(init, jsonMonitor, jsonTraces, counter, total);
	}

	/**
	 * @return
	 */
	private String getPathName() {
		String path = ConfigurationManager.getInstance().getProperty(
				Properties.LOGS_PATH.name());
		String actualDate = new SimpleDateFormat("-dd-MM-yyyy-HH-mm-ss").format(new Date());
		path = path + actualDate + ".log";
		return path;
	}

	/**
	 * @param init
	 * @param jsonMonitor
	 * @param jsonTraces
	 * @param counter
	 * @param total
	 * @throws JSONException
	 */
	private void addTraceValues(long init, JSONObject jsonMonitor,
			JSONArray jsonTraces, int counter, long total) throws JSONException {
		long endTime = getEndTime();
		jsonMonitor.put("startTime", init);
		jsonMonitor.put("endTime", endTime);
		jsonMonitor.put("totalTime", total);
		jsonMonitor.put("averageTime", total / counter);
		jsonMonitor.put("size", counter);
		jsonMonitor.put("traces", jsonTraces);
	}

	/**
	 * @return
	 */
	private long getEndTime() {
		Trace lastTrace = traces.get(traces.size() - 1);
		int lastTime = lastTrace.getTraces().size() -1;
		long endTime = lastTrace.getTraces().get(lastTime);
		return endTime;
	}
}
