package com.test.monitoring;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @class TransAlpesMonitor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class Trace {

	private Monitor monitor = Monitor.getInstance();
	private List<Long> traces;
	private byte[] data;
	private String id;
	private boolean error;

	private Trace() {
		traces = new LinkedList<Long>();
		monitor.addTrace(this);
	}

	public static Trace getInstance() {
		return new Trace();
	}

	public void addTime(long time) {
		traces.add(time);
	}

	public void printTrace() {
		JSONObject log = getJsonTrace();
		System.out.println(log);
	}

	public JSONObject getJsonTrace() {
		JSONObject jsonTrace = new JSONObject();
		JSONArray array = new JSONArray();
		for (Long time : traces) {
			array.put(time);
		}
		try {
			jsonTrace.put("id", getId());
			jsonTrace.put("traces", array);
			jsonTrace.put("total", getLastTrace() - getFirstTrace());
			jsonTrace.put("error", error);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonTrace;
	}

	/**
	 * @return
	 */
	private Long getLastTrace() {
		return traces.get(traces.size() - 1);
	}

	/**
	 * @return
	 */
	private Long getFirstTrace() {
		return traces.get(0);
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	public List<Long> getTraces() {
		return traces;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

}
